package neu.lab.autoexe.entrance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AutoExeEntrance {

	public static final String staProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String detectProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String clsDetectProjectDir = "D:\\ws\\final_3\\gitHub_old\\";
	public static final String upVerProjectDir = "D:\\ws\\solvebug\\ignite-master\\";
	public static final String findVerDir = "D:\\ws\\solvebug\\ignite-master\\";
	// TODO which directory
	public static final String debugDir = "D:\\ws\\gitHub_latest2\\";
	public static final String debug2Dir = "D:\\ws\\gitHub_develop\\";

	// D:\ws\gitHub_old\
	public static void main(String[] args) throws IOException {
		new neu.lab.autoexe.decca.AutoDebug().autoExe(getPomPathBySize("D:\\ws_testcase\\projectSize_latest2.txt"),
				true);
		// for(String
		// pomPath:getPomPathBySize("D:\\ws_testcase\\projectSize_latest2.txt")) {
		// System.out.println(pomPath);
		// }
	}

	// public static void printExePath() {
	// String path = "";
	// for (String pomPath : findPomPaths(new File(path))) {
	// System.out.println(pomPath);
	// }
	// }

	// public static List<String> findPomPaths(File father) {
	// File[] children = father.listFiles();
	// List<String> pomPaths = new ArrayList<String>();
	// for (File child : children) {
	// if (child.getName().equals("pom.xml")) {
	// pomPaths.add(father.getAbsolutePath());
	// }
	// if (child.isDirectory()) {
	// pomPaths.addAll(findPomPaths(child));
	// }
	// }
	// return pomPaths;
	// }
	private static List<String> getPomPathBySize(String sizeFile) {
		Map<Integer, List<String>> size2poms = new TreeMap<Integer, List<String>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(sizeFile));
			String line = reader.readLine();
			while (line != null) {
				if (!line.equals("")) {
					String[] pom_jar_size = line.split(" ");
					Integer size = Integer.valueOf(pom_jar_size[2]);
					List<String> poms = size2poms.get(size);
					if (poms == null) {
						poms = new ArrayList<String>();
						size2poms.put(size, poms);
					}
					poms.add(pom_jar_size[0]);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> sortedPomPaths = new ArrayList<String>();
		for (Integer size : size2poms.keySet()) {
			sortedPomPaths.addAll(size2poms.get(size));
		}
		return sortedPomPaths;
	}

	private static List<String> getPomPaths() {
		List<String> list = new ArrayList<String>();
		list.add("D:\\ws\\gitHub_develop\\googleads-java-lib-master\\modules\\ads_lib");
		list.add(
				"D:\\ws\\gitHub_develop\\azure-storage-java-master\\microsoft-azure-storage-samples\\src\\com\\microsoft\\azure\\storage\\logging");
		list.add("D:\\ws\\gitHub_develop\\github-api-master");
		list.add("D:\\ws\\gitHub_develop\\swagger-parser-master\\modules\\swagger-compat-spec-parser");
		list.add("D:\\ws\\gitHub_develop\\azure-storage-java-master\\microsoft-azure-storage-samples");
		list.add("D:\\ws\\gitHub_develop\\alexa-skills-kit-sdk-for-java-2.0.x\\ask-sdk-dynamodb-persistence-adapter");
		list.add("D:\\ws\\gitHub_develop\\webmagic-master\\webmagic-selenium");
		list.add("D:\\ws\\gitHub_develop\\kurento-java-master\\kurento-repository\\kurento-repository-server");
		list.add("D:\\ws\\gitHub_develop\\alexa-skills-kit-sdk-for-java-2.0.x\\samples\\colorpicker");
		list.add("D:\\ws\\gitHub_develop\\jetbrick-template-2x-master\\jetbrick-template-jetbrickmvc");
		return list;
	}
}
