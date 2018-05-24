package neu.lab.autotest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class AutoTestEn {
	static String distanceFile = "D:\\ws_testcase\\distance\\org.apache.accumulo+accumulo-core+1.7.2.txt";
	static String pomPath = "D:\\ws\\gitHub_old\\accumulo-rel-1.7.2\\core";
	static int exeNum = 1000;
	static int exedNum = 0;

	public static void main(String[] args) throws Exception {

		MethodDistance md = new MethodDistance(distanceFile);
		Set<String> exedClses = new HashSet<String>();
		String nextCls = md.getNextExe(exedClses);
		while (nextCls != null && exedNum < exeNum) {
			exedClses.add(nextCls);
			System.out.println("to generate for:" + nextCls);
			System.out.println("exed/all:" +exedNum+"/" +md.getEntryClsNum());
			String mvnCmd = getMvnCmd(nextCls);
			try {
				System.out.println("mvnCmd:" + mvnCmd);
				exeMvn(mvnCmd);
			} catch (Exception e) {
				System.out.println("exe mvn error");
				e.printStackTrace();
			}
			exedNum++;
			nextCls = md.getNextExe(exedClses);
		}
		System.out.println(exedNum);
	}

	private static String getMvnCmd(String cut) {
		String line = "cmd.exe /C ";
		line = line + "mvn -Dmaven.test.skip=true org.evosuite.plugins:evosuite-maven-plugin:8.15:generate -f="
				+ pomPath + " -Dclass=" + cut + " -Ddistance_file=" + distanceFile + " -Dcriterion=RISK -e ";
		return line;
	}

	private static void exeMvn(String mvnCmd) throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(mvnCmd);
		DefaultExecutor executor = new DefaultExecutor();
		executor.execute(cmdLine);
	}
}
