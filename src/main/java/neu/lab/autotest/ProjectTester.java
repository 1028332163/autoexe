package neu.lab.autotest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import neu.lab.autoexe.util.FileSyn;

public class ProjectTester {
	private FileSyn doneCls;
	private int exedNum = 0;
	public  void runOneProject(String pomPath, String distanceFile, String risktype) throws Exception {
		NodeDistance md;
		doneCls = new FileSyn(AutoTestEn.stateDir,distanceFile.substring(distanceFile.lastIndexOf("\\")+1));
		if ("cls".equals(risktype)) {
			md = new ClassDistance(distanceFile);
		} else {
			md = new MethodDistances(distanceFile);
		}
		Set<String> pickedClses = new HashSet<String>();
		String nextCls = md.getNextExe(pickedClses);
		while (nextCls != null) {
			pickedClses.add(nextCls);
			// && exedNum < exeNum
			if(!doneCls.contains(nextCls)) {
				System.out.println("to generate for:" + nextCls);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("start time：" + sdf.format(new Date()));
				System.out.println("exed/all:" + exedNum + "/" + md.getEntryClsNum());
				String mvnCmd = getMvnCmd(nextCls, pomPath, distanceFile, "cls");
				try {
					System.out.println("mvnCmd:" + mvnCmd);
					exeMvn(mvnCmd);
					FileUtil.delFolder(new File(pomPath).getParent() + "\\evosuite-report");
				} catch (Exception e) {
					System.out.println("exe mvn error");
					e.printStackTrace();
				}
				doneCls.add(nextCls);
				exedNum++;
			}
			nextCls = md.getNextExe(pickedClses);
		}
		System.out.println(exedNum);
	}

	private  String getMvnCmd(String cut, String pomPath, String distanceFile, String riskType) {
		String line = "cmd.exe /C ";
		line = line + "mvn -Dmaven.test.skip=true org.evosuite.plugins:evosuite-maven-plugin:8.15:generate -f="
				+ pomPath + " -Dclass=" + cut;
		if ("cls".equals(riskType)) {
			line = line + " -Dcls_distance_file=" + distanceFile + " -Dcriterion=CLS_RISK -e";
		} else {
			line = line + " -Dmthd_distance_file=" + distanceFile + " -Dcriterion=RISK -e";
		}
		return line;
	}

	private  void exeMvn(String mvnCmd) throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(mvnCmd);
		DefaultExecutor executor = new DefaultExecutor();
		executor.execute(cmdLine);
	}
}
