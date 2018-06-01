package neu.lab.autoexe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.dom4j.DocumentException;

import neu.lab.autoexe.util.FileSyn;
import neu.lab.autoexe.util.PomReader;

public abstract class AutoExe {
	public FileSyn donePjct;// project has done;
	public FileSyn mvnExpPjt;// project that throws exception when executes maven command
	public FileSyn notJarPjct;// record project that hasn't conflict
	public FileSyn successPjt;// record project that build success(but may be has exception caught by Maven)
	public int allTask;
	public int completeSize;

	protected void readState() throws IOException {
		donePjct = new FileSyn(getStateDir(), "Project_done.txt");
		mvnExpPjt = new FileSyn(getStateDir(), "Project_throw_error.txt");
		notJarPjct = new FileSyn(getStateDir(), "Project_not_jar.txt");
		successPjt = new FileSyn(getStateDir(), "Project_build_success.txt");
	}

	protected abstract String getStateDir();

	private void writeState() {
		donePjct.closeOut();
		mvnExpPjt.closeOut();
		notJarPjct.closeOut();
		successPjt.closeOut();
	}

	public void autoExe(List<String> pomDirs, boolean exeByOrder) throws IOException {
		readState();
		allTask = pomDirs.size();
		completeSize = 0;
		List<String> leftProjects = new ArrayList<String>();
		for (String pomPath : pomDirs) {
			if (!donePjct.contains(path2name(pomPath))) {
				leftProjects.add(pomPath);
			} else {
				completeSize++;
			}
		}
		System.out.println("left/all " + leftProjects.size() + "/" + allTask);

		if (exeByOrder) {
			for (String pomPath : leftProjects) {
				handleOnePom(pomPath);
			}
		} else {
			while (leftProjects.size() != 0) {
				int exeIndex = (int) (leftProjects.size() * Math.random());
				String pomPath = leftProjects.get(exeIndex);
				handleOnePom(pomPath);
				donePjct.add(path2name(pomPath));
				completeSize++;
				leftProjects.remove(exeIndex);
			}
		}

		writeState();
	}

	public void autoExe(boolean exeByOrder) throws IOException {
		List<String> pomDirs = getPomDirs();
		autoExe(pomDirs, exeByOrder);
	}

	protected void handleOnePom(String pomPath) {
		System.out.println("complete/all: " + completeSize + "/" + allTask);
		System.out.println("handle pom for:" + pomPath);
		if (pomPath.startsWith("D:\\ws\\gitHub_old\\hadoop-release-3.0.0-alpha1-RC0")
				|| pomPath.startsWith("D:\\ws\\gitHub_old\\hadoop-common-release-2.5.0-rc0")
				|| pomPath.startsWith("D:\\ws\\gitHub_old\\flink-release-1.4.0-rc2\\")) {
			System.out.println("skip long time project:"+pomPath);
			return;
		}
		if (pomPath.contains("example")) {
			System.out.println("skip example project:"+pomPath);
			return;
		}

		String projectName = path2name(pomPath);
		StringBuilder outResult = new StringBuilder("exeResult for ");
		long startTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("start time：" + sdf.format(new Date()));
		try {
			PomReader reader = new PomReader(pomPath + "\\pom.xml");
			outResult.append(reader.getCoordinate() + " ");
			if ("jar".equals(reader.getPckType())) {
				if (!donePjct.contains(projectName) && !mvnExpPjt.contains(projectName)
						&& !notJarPjct.contains(projectName)) {
					try {
						mvnOnePom(pomPath);
						// success
						successPjt.add(path2name(pomPath));
						outResult.append("success");
					} catch (Exception e) {//failed
						e.printStackTrace();
						mvnExpPjt.add(path2name(pomPath));
						outResult.append("failed");
					}
				} else {// executed
					outResult.append("executed");
				}
			} else {// not-jar
				notJarPjct.add(path2name(pomPath));
				outResult.append("not-jar");
			}
		} catch (DocumentException e) {// can't read pom
			outResult.append(pomPath);
			outResult.append("pom-error");
		}
		System.out.println("end time：" + sdf.format(new Date()));
		long runtime = (System.currentTimeMillis() - startTime) / 1000;
		outResult.append(" "+runtime);
		System.out.println(outResult.toString());
	}

	protected abstract String getProjectDir();

	private void mvnOnePom(String pomPath) throws Exception {
		// try {
		System.out.println("exec mvn for:" + pomPath);
		writeBat(pomPath);
		String line = "cmd.exe /C " + getBatPath();
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		executor.execute(cmdLine);
		// } catch (Exception e) {
		// e.printStackTrace();
		// mvnExpPjt.add(path2name(pomPath));
		// }
	}

	private String path2name(String path) {
		// D:\test_apache\simple\commons-logging-1.2-src
		return path.replace(this.getProjectDir(), "");
	}

	private boolean isSingle(File pomFile) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pomFile));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("<modules>")) {
					reader.close();
					return false;
				}
				line = reader.readLine();
			}
			reader.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected List<String> getPomDirs() {
		// return AutoExeEntrance.findPomPaths(new File(getProjectDir()));
		return findPomPaths(new File(getProjectDir()));
	}

	private List<String> findPomPaths(File father) {
		File[] children = father.listFiles();
		List<String> pomPaths = new ArrayList<String>();
		if (!father.getAbsolutePath().contains("\\target\\")
				&& !father.getAbsolutePath().contains("\\src\\main\\resources\\")
				&& !father.getAbsolutePath().contains("\\src\\test\\")) {
			for (File child : children) {
				if (child.getName().equals("pom.xml")) {
					pomPaths.add(father.getAbsolutePath());
				}
				if (child.isDirectory()) {
					pomPaths.addAll(findPomPaths(child));
				}
			}
		}
		return pomPaths;
	}

	protected void writeBat(String pomPath) throws IOException {
		PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(getBatPath())));
		printer.println("cd " + pomPath);
		// printer.println("mvn -Dmaven.test.skip=true package
		// neu.lab:conflict:1.0:detect -e");
		printer.println(getCommand());
		printer.close();
	}

	public abstract String getBatPath();

	public abstract String getCommand();
}
