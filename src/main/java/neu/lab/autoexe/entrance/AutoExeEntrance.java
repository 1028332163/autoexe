package neu.lab.autoexe.entrance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AutoExeEntrance {

	public static final String staProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String detectProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String clsDetectProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String upVerProjectDir = "D:\\ws\\solvebug\\ignite-master\\";
	public static final String findVerDir = "D:\\ws\\solvebug\\ignite-master\\";
	public static final String debugDir = "D:\\ws\\gitHub_new\\";
//	D:\ws\gitHub_old\
	public static void main(String[] args) throws IOException {
		new neu.lab.autoexe.decca.AutoDebug().autoExe(getPomPaths(),true);
		// new neu.lab.autoexe.AutoDetect().autoExe(true);
		// new neu.lab.autoexe.AutoClsDetect().autoExe(true);
		// new neu.lab.autoexe.AutoSta().autoExe(true);
	}

	public static void printExePath() {
		String path = "";
		for (String pomPath : findPomPaths(new File(path))) {
			System.out.println(pomPath);
		}
	}

	public static List<String> findPomPaths(File father) {
		File[] children = father.listFiles();
		List<String> pomPaths = new ArrayList<String>();
		for (File child : children) {
			if (child.getName().equals("pom.xml")) {
				pomPaths.add(father.getAbsolutePath());
			}
			if (child.isDirectory()) {
				pomPaths.addAll(findPomPaths(child));
			}
		}
		return pomPaths;
	}

	private static List<String> getPomPaths() {
		List<String> list = new ArrayList<String>();
		list.add("D:\\ws\\gitHub_new\\accumulo-rel-1.7.3\\fate");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\commons");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\credential-store\\credential-store-stubs");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\messaging\\client");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\messaging\\core");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\monitoring");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\registry\\registry-cpi");
		list.add("D:\\ws\\gitHub_new\\airavata-airavata-0.16\\modules\\security");
		list.add("D:\\ws\\gitHub_new\\archiva-redback-core-redback-2.6\\redback-users\\redback-users-tests");
		list.add("D:\\ws\\gitHub_new\\axis2-java-1.7.7\\modules\\adb");
		list.add("D:\\ws\\gitHub_new\\axis2-java-1.7.7\\modules\\samples\\transport\\https-sample\\httpsClient");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\model\\fn-execution");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\model\\job-management");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\runners\\core-java");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\runners\\local-java");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\runners\\reference\\java");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\sdks\\java\\extensions\\protobuf");
		list.add("D:\\ws\\gitHub_new\\beam-2.3.0-RC3\\sdks\\java\\io\\amazon-web-services");
		list.add("D:\\ws\\gitHub_new\\Apache-maven-3.5.0\\maven-compat");
		list.add("D:\\ws\\gitHub_new\\Apache-maven-3.5.0\\maven-core");
		list.add("D:\\ws\\gitHub_new\\Apache-maven-3.5.0\\maven-embedder");
		list.add("D:\\ws\\gitHub_new\\Apache-maven-3.5.0\\maven-model-builder");
		list.add("D:\\ws\\gitHub_new\\Apache-maven-3.5.0\\maven-resolver-provider");
		return list;
	}
}
