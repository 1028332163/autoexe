package abandon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import neu.lab.autoexe.util.PomReader;

public class PrintNewPathEntrance {
//	public static void main(String[] args) throws IOException {
//		String newProjectDir = "D:\\ws\\gitHub_new\\";
//		String pickedProjects = "D:\\cWS\\notepad++\\picked.txt";
//		PrintNewPathEntrance.printNewPath(newProjectDir, pickedProjects);
//	}

//	public static void printNewPath(String newProjectDir, String pickedProjects) throws IOException {
//		Map<String, String> project2path = getUsedProjects(pickedProjects);
//		for (String pomPath : AutoExeEntrance.findPomPaths(new File(newProjectDir))) {
//			// System.out.println(pomPath + "\\pom.xml");
//			try {
//				PomReader pom = new PomReader(pomPath + "\\pom.xml");
//				String groupId = pom.getGroupId();
//				String artifactId = pom.getArtifactId().replace("${scala.binary.version}", "2.11");
//				if (project2path.containsKey(groupId + ":" + artifactId)) {
//					String path = project2path.get(groupId + ":" + artifactId);
//					if (null != path)
//						path = path + "@" + pomPath + "\\pom.xml";
//					else
//						path = "@" + pomPath + "\\pom.xml";
//					project2path.put(groupId + ":" + artifactId, path);
//				}
//			} catch (Exception e) {
//				System.out.println("can't read pom for: " + pomPath);
//			}
//		}
//		for (String project : project2path.keySet()) {
//			System.out.println(project + project2path.get(project));
//		}
//	}

	public static Map<String, String> getUsedProjects(String filePath) throws IOException {
		Map<String, String> project2path = new HashMap<String, String>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		while (line != null) {
			if (line.contains("@")) {
				project2path.put(line.split(" ")[0], null);
			}
			line = reader.readLine();
		}
		reader.close();
		return project2path;
	}

}
