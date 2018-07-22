package neu.lab.autoexe.entrance;

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
	public static final String debug2Dir = "D:\\ws\\gitHub_snapshot\\";
//	D:\ws\gitHub_old\
	public static void main(String[] args) throws IOException {
		new neu.lab.autoexe.decca.AutoDebug2().autoExe(getPomPaths(),true);
		// new neu.lab.autoexe.AutoDetect().autoExe(true);
		// new neu.lab.autoexe.AutoClsDetect().autoExe(true);
		// new neu.lab.autoexe.AutoSta().autoExe(true);
	}

//	public static void printExePath() {
//		String path = "";
//		for (String pomPath : findPomPaths(new File(path))) {
//			System.out.println(pomPath);
//		}
//	}

//	public static List<String> findPomPaths(File father) {
//		File[] children = father.listFiles();
//		List<String> pomPaths = new ArrayList<String>();
//		for (File child : children) {
//			if (child.getName().equals("pom.xml")) {
//				pomPaths.add(father.getAbsolutePath());
//			}
//			if (child.isDirectory()) {
//				pomPaths.addAll(findPomPaths(child));
//			}
//		}
//		return pomPaths;
//	}

	private static List<String> getPomPaths() {
		List<String> list = new ArrayList<String>();
		list.add("D:\\ws\\gitHub_snapshot\\alexa-skills-kit-sdk-for-java-2.3.4\\ask-sdk");
		list.add("D:\\ws\\gitHub_snapshot\\truth-release_0_41\\extensions\\liteproto");
		list.add("D:\\ws\\gitHub_snapshot\\jetbrick-template-2x-2.1.6\\jetbrick-template-jetbrickmvc");
		list.add("D:\\ws\\gitHub_snapshot\\auto-auto-value-1.6.2\\value\\processor");
		list.add("D:\\ws\\gitHub_snapshot\\alexa-skills-kit-sdk-for-java-2.3.4\\ask-sdk-dynamodb-persistence-adapter");
		list.add("D:\\ws\\gitHub_snapshot\\ff4j-1.7.1\\ff4j-webapi-jersey1x");
		list.add("D:\\ws\\gitHub_snapshot\\incubator-dubbo-dubbo-2.6.2\\dubbo-rpc\\dubbo-rpc-thrift");
		list.add("D:\\ws\\gitHub_snapshot\\ff4j-1.7.1\\ff4j-webapi");
		list.add("D:\\ws\\gitHub_snapshot\\ff4j-1.7.1\\ff4j-spring-boot-autoconfigure");
		list.add("D:\\ws\\gitHub_snapshot\\truth-release_0_41\\extensions\\proto");
		list.add("D:\\ws\\gitHub_snapshot\\raml-java-parser-0.8.25");
		list.add("D:\\ws\\gitHub_snapshot\\ff4j-1.7.1\\ff4j-webapi-jersey2x");
		return list;
	}
}
