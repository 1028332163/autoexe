package neu.lab.autotest.neighbor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import neu.lab.autotest.FileUtil;

public class NeiborClassTester {

	public void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("D:\\ws_testcase\\image\\OneDisEntry.txt"));
		String line = reader.readLine();
		while (line != null) {
			if (!line.equals("")) {
				String[] pom_neibor_jar = line.split(",");
				try {
					String mvnCmd = getMvnCmd(pom_neibor_jar[0], pom_neibor_jar[1], pom_neibor_jar[2]);
					System.out.println("exe mvnCMd:" + mvnCmd);
					exeMvn(mvnCmd);
					FileUtil.delFolder(new File(pom_neibor_jar[0]).getParent() + "\\evosuite-report");
				} catch (Exception e) {
					System.out.println("exe mvn error");
					e.printStackTrace();
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}

	private void exeMvn(String mvnCmd) throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(mvnCmd);
		DefaultExecutor executor = new DefaultExecutor();
		executor.execute(cmdLine);
	}

	private String getMvnCmd(String pomPath, String nieborClass, String jarPath) {
		// mvn -f=D:\cWS\eclipse1\testcase.top\pom.xml
		// org.evosuite.plugins:evosuite-maven-plugin:8.15:generate
		// -Dclass=neu.lab.testcase.middle.ClassMiddle
		// -Dtarget=D:\cEnvironment\repository\neu\lab\testcase.middle\1.0\testcase.middle-1.0.jar
		// -Dcriterion=LINE
		String line = "cmd.exe /C ";
		line = line + "mvn -Dmaven.test.skip=true org.evosuite.plugins:evosuite-maven-plugin:8.15:generate -f="
				+ pomPath + " -Dclass=" + nieborClass;
		line = line + " -Dtarget=" + jarPath + " -Dcriterion=LINE -e";
		return line;
	}
}
