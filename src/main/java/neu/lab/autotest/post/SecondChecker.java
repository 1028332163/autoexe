package neu.lab.autotest.post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Because Util.getCoveredCls() in first version of evosuite has bug that can't
 * standard all thrown-exception,we should check log.
 * 
 * @author asus
 *
 */
public class SecondChecker {
	private static String testLogPath = "D:\\log\\all\\TestLog.txt";

	public static void main(String[] args) {
		readTestLiog(testLogPath);
	}

	private static void readTestLiog(String filePath) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			Project currentPro = null;
			String currentTestedCls = null;
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("mvnCmd:cmd.exe")) {//
					String pomPath = line.substring(line.indexOf("-f=") + 3, line.indexOf(" -Dclass"));
					currentTestedCls = line.substring(line.indexOf("-Dclass=") + 8,
							line.indexOf(" -Dcls_distance_file="));
					currentPro = Projects.i().getProject(pomPath);
//					System.out.println("current tested"+pomPath + "|" + currentTestedCls);

					if (currentPro == null) {// first class in project,initial risk-classes
						currentPro = new Project(pomPath);
						// System.out.println(line);
						// System.out.println(line.indexOf("-Dcls_distance_file="));
						// System.out.println(line.indexOf(" -Dclass"));
						String distanceFile = line.substring(line.indexOf("-Dcls_distance_file=") + 20,
								line.indexOf("-Dcriterion"));
						currentPro.setRiskClses(readDistanceFile(distanceFile));
						Projects.i().addProject(currentPro);
					}
				} else if (line.contains("lzw no this class")) {// add exception-class to current-class.
					currentPro.addExpCls(currentTestedCls,
							extraExpCls(line.substring(line.indexOf("lzw no this class:") + 18)));
				}
				line = reader.readLine();
			}
			reader.close();
			System.out.println(Projects.i().getNeededExpClses());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.Could not initialize class io.netty.util.concurrent.DefaultPromise
	 * 2.org/apache/log/Hierarchy 3.io.netty.util.concurrent.DefaultPromise 4.Class
	 * general.vfs.context.classpath..delegation not found 5., key= 6.Class
	 * 'partition-filter:int.class' should be in target project, but could not be
	 * found!
	 * 
	 * @param expMessage
	 * @return
	 */
	private static String extraExpCls(String expMessage) {
		String expCls;
		if (expMessage.startsWith("Could not initialize class")) {
			expCls = expMessage.substring(27);
		} else if (expMessage.startsWith("Class ") && expMessage.endsWith(" not found")) {
			expCls = expMessage.substring(6, expMessage.length() - 10);
		} else if (expMessage.startsWith("Class '")
				&& expMessage.endsWith("' should be in target project, but could not be found!")) {
			expCls = expMessage.substring(7, expMessage.length() - 54);
		} else {
			expCls = expMessage;
		}
		expCls = expCls.replace("/", ".");
//		System.out.println("expCls:"+expCls+"|");
		return expCls;
	}

	private static Set<String> readDistanceFile(String filePath) {
		Set<String> riskClses = new HashSet<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {
				if (!"".equals(line)) {
					String riskCls = line.substring(0,line.indexOf(","));
					riskClses.add(riskCls);
					// System.out.println(riskCls);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return riskClses;
	}
}
