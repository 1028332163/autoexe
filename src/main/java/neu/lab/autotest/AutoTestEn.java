package neu.lab.autotest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import neu.lab.autoexe.util.FileSyn;
import neu.lab.autoexe.util.PomFinder;

public class AutoTestEn {
	// static String distanceFile =
	// "D:\\ws_testcase\\distance\\commons-jxpath+commons-jxpath+1.3.txt";
	// static String pomPath = "D:\\ws_testcase\\projects\\commons-jxpath-1.3-src";
	// static int exeNum = 1000;
	// static int exedNum = 0;
	static String cls_type = "cls";
	static String pomRoot = "D:\\ws\\gitHub_old";
	static String distanceRoot = "D:\\ws_testcase\\distance_cls\\";
	static String stateDir = "D:\\ws_testcase\\image\\state_autoEvo\\";
	static FileSyn doneProject;// record project-mvn-coordinate
	static Set<String> skipProjects;
	static {
		skipProjects = new HashSet<String>();
		skipProjects.add(distanceRoot + "level_3_eagle+eagle-embed-hbase+0.3.0-incubating.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.atlas+hive-bridge+0.7.1-incubating.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.camel+camel-spark-starter+2.19.4.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.carbondata+carbondata-spark2+1.1.1.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.crunch+crunch-hbase+0.11.0-cdh5.12.2.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.cxf.samples+spring-boot-sample-rs-scan-eureka+3.1.14.txt");
	}

	public static void main(String[] args) throws Exception {
//		autoTest();
		readId2path("");
	}

	private static Map<String, String> readId2path(String filePath) throws Exception {
		Map<String, String> id2path = new PomFinder().getId2path(new File("D:\\ws\\bugreport\\atlas-release-0.8.2-rc0"));

		for (String id : id2path.keySet()) {
			System.out.println(id + "," + id2path.get(id));
		}

		// Map<String, String> id2path = new HashMap<String, String>();
		// BufferedReader reader = new BufferedReader(new FileReader(new
		// File(filePath)));
		// String line = reader.readLine();
		// while (line != null) {
		// if (!line.equals("")) {
		// String[] id_path = line.split(",");
		// if (id_path.length == 2)
		// id2path.put(id_path[0], id_path[1]);
		// }
		// line = reader.readLine();
		// }
		// reader.close();
		return id2path;
	}

	private static void autoTest() throws Exception {
		File stateDirFile = new File(stateDir);
		if (!stateDirFile.exists()) {
			stateDirFile.mkdirs();
		}
		doneProject = new FileSyn(stateDir, "1exed.txt");
		// Map<String, String> id2path = new PomFinder().getId2path(new File(pomRoot));
		Map<String, String> id2path = readId2path("projectFile\\projectId2path.txt");
		List<File> highLevelFiles = getHighLevelFiles(new File(distanceRoot));
		System.out.println("all highLevelProject is " + highLevelFiles.size() + " already done in last autoEvo is "
				+ doneProject.recordNum());
		int doneProjectNum = 0;
		for (File highLevelFile : highLevelFiles) {
			System.out.println("==========highLevelProject:" + highLevelFile);
			if (skipProjects.contains(highLevelFile.getAbsolutePath())) {
				System.out.println("skip project:" + highLevelFile.getAbsolutePath());
				continue;
			}
			String mvnId = name2id(highLevelFile.getName());
			if (!doneProject.contains(mvnId)) {// have not done
				String pomPath = id2path.get(mvnId);
				if (pomPath != null) {
					String distanceFile = highLevelFile.getAbsolutePath();
					System.out.println("dis:" + distanceFile);
					System.out.println("pom:" + pomPath);
					new ProjectTester().runOneProject(pomPath, distanceFile, cls_type);
				} else {
					System.out.println("can't find pomFile for " + highLevelFile.getName());
				}
				doneProject.add(mvnId);
			}
			doneProjectNum++;
			System.out.println("doneProject/all:" + doneProjectNum + "/" + highLevelFiles.size());
		}
	}

	private static List<File> getHighLevelFiles(File distanceRoot) {
		List<File> highLevelFiles = new ArrayList<File>();
		for (File distanceFile : distanceRoot.listFiles()) {
			if (distanceFile.getName().startsWith("level_3")) {
				highLevelFiles.add(distanceFile);
			}
		}
		return highLevelFiles;
	}

	/**
	 * file name instead of path
	 * 
	 * @param highLevelName
	 * @return
	 */
	private static String name2id(String highLevelName) {
		return highLevelName.substring(8).replace(".txt", "").replace("+", ":");
	}

}
