package neu.lab.autoexe.entrance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import neu.lab.autoexe.AutoClsDetect;
import neu.lab.autoexe.AutoDebug;
import neu.lab.autoexe.AutoDetect;
import neu.lab.autoexe.AutoSta;

public class AutoExeEntrance {

	public static final String staProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String detectProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String clsDetectProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String upVerProjectDir = "D:\\ws\\solvebug\\ignite-master\\";
	public static final String findVerDir = "D:\\ws\\solvebug\\ignite-master\\";
	public static final String debugDir = "D:\\ws\\gitHub_old\\";

	public static void main(String[] args) throws IOException {
		 new AutoDebug().autoExe(true);
//		new AutoDetect().autoExe(true);
//		new AutoClsDetect().autoExe(true);
		// new AutoSta().autoExe(true);
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
		list.add("D:\\ws\\gitHub_old\\incubator-omid-release-0.8.2.0\\codahale-metrics");
		list.add("D:\\ws\\gitHub_old\\tuscany-sca-2.x-2.0\\modules\\binding-http-runtime");
		list.add("D:\\ws\\gitHub_old\\any23-any23-2.1\\plugins\\office-scraper");
		list.add("D:\\ws\\gitHub_old\\oodt-1.2.1\\curator\\sso");
		list.add(
				"D:\\ws\\gitHub_old\\apache-maven-3.5.0\\maven-core\\src\\test\\resources-project-builder\\dual-execution-ids\\sub");
		list.add("D:\\ws\\gitHub_old\\activemq-cpp-activemq-cpp-pom-3.1.0-RC1\\activemq-cpp-openwire-generator");
		list.add("D:\\ws\\gitHub_old\\activemq-artemis-2.1.0\\artemis-journal");
		list.add("D:\\ws\\gitHub_old\\activemq-artemis-2.1.0\\artemis-service-extensions");
		list.add(
				"D:\\ws\\gitHub_old\\hadoop-common-release-2.5.0-rc0\\hadoop-mapreduce-project\\hadoop-mapreduce-client\\hadoop-mapreduce-client-core");
		list.add("D:\\ws\\gitHub_old\\cxf-cxf-3.1.14\\distribution\\src\\main\\release\\samples\\corba\\hello_world");
		list.add("D:\\ws\\gitHub_old\\activemq-artemis-2.1.0\\artemis-ra");
		list.add("D:\\ws\\gitHub_old\\activemq-artemis-2.1.0\\artemis-junit");
		list.add("D:\\ws\\gitHub_old\\airavata-airavata-0.13\\modules\\credential-store-service\\credential-store");
		list.add("D:\\ws\\gitHub_old\\activemq-artemis-2.1.0\\artemis-cdi-client");
		list.add("D:\\ws\\gitHub_old\\airavata-airavata-0.13\\airavata-api\\airavata-client-sdks\\java-client-samples");
		list.add("D:\\ws\\gitHub_old\\brooklyn-server-rel-apache-brooklyn-0.12.0-rc1\\utils\\rest-swagger");
		list.add("D:\\ws\\gitHub_old\\flink-release-1.4.0-rc2\\flink-connectors\\flink-connector-rabbitmq");
		list.add(
				"D:\\ws\\gitHub_old\\nifi-minifi-rel-minifi-0.1.0\\minifi-nar-bundles\\minifi-framework-bundle\\minifi-framework\\minifi-runtime");
		return list;
	}
}
