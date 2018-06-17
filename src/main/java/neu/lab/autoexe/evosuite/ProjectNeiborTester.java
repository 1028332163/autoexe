package neu.lab.autoexe.evosuite;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import neu.lab.autoexe.util.ExecUtil;
import neu.lab.autoexe.util.FileSyn;
import neu.lab.autotest.FileUtil;

public class ProjectNeiborTester {
	private String pomPath;
	private Map<String, String> neibor2jarPath;

	public ProjectNeiborTester(String pomPath) {
		this.pomPath = pomPath;
		neibor2jarPath = new HashMap<String, String>();
	}

	public void addNeibor(String neribor, String jarPath) {
		neibor2jarPath.put(neribor, jarPath);
	}

	public void runTest(int projectOrder) throws Exception {
		FileSyn doneNeibor = new FileSyn("D:\\ws_testcase\\image\\state_neiborTest\\"
				+ pomPath.hashCode()+".txt");
		for (String neibor : neibor2jarPath.keySet()) {
			if (!doneNeibor.contains(neibor)) {
				try {
					String mvnCmd = getMvnCmd(pomPath, neibor, neibor2jarPath.get(neibor));
					ExecUtil.exeMvn(mvnCmd);
					FileUtil.delFolder(new File(pomPath).getParent() + "\\evosuite-report");
				} catch (Exception e) {
					System.out.println("exe mvn error");
					e.printStackTrace();
				}
			}
			doneNeibor.add(neibor);
			System.out.println("projectOrder:" + projectOrder);
			System.out.println("doneProject/all:" + doneNeibor.recordNum() + "/" + neibor2jarPath.keySet().size());
		}
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
