package neu.lab.autotest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import neu.lab.autoexe.util.FileSyn;
import neu.lab.autoexe.util.PomFinder;

public class AutoTestEn {
	// static String distanceFile =
	// "D:\\ws_testcase\\distance\\commons-jxpath+commons-jxpath+1.3.txt";
	// static String pomPath = "D:\\ws_testcase\\projects\\commons-jxpath-1.3-src";
	// static int exeNum = 1000;
	// static int exedNum = 0;
	static String cls_type = "cls";
	static String pomRoot = "D:\\ws\\gitHub_old";
	static String distanceRoot = "D:\\ws_testcase\\distance_cls";
	static String stateDir = "D:\\ws_testcase\\exeState\\";
	static FileSyn doneProject;// record project-mvn-coordinate

	public static void main(String[] args) throws Exception {
		doneProject = new FileSyn(stateDir, "1exed.txt");
		Map<String, String> id2path = new PomFinder().getId2path(new File(pomRoot));
		List<File> highLevelFiles = getHighLevelFiles(new File(distanceRoot));
		for (File highLevelFile : highLevelFiles) {
			String mvnId = name2id(highLevelFile.getName());
			if(!doneProject.contains(mvnId)) {//have not done
				String pomPath = id2path.get(mvnId);
				if (pomPath != null) {
					String distanceFile = highLevelFile.getAbsolutePath();
					System.out.println("dis:" + distanceFile);
					System.out.println("pom:" + pomPath);
					new ProjectTester().runOneProject(pomPath, distanceFile, cls_type);
				} else {
					System.out.println("can't find pomFile for " + highLevelFile.getName());
				}
				doneProject.add(mvnId);
			}
		}
		// String pomPath =
		// "D:\\ws_testcase\\projects\\accumulo-rel-1.7.2\\server\\master";
		// String distanceFile =
		// "D:\\ws_testcase\\distance_cls\\level_3_org.apache.accumulo+accumulo-master+1.7.2.txt";
		// runOneProject(pomPath, distanceFile, cls_type);
	}

	private static List<File> getHighLevelFiles(File distanceRoot) {
		List<File> highLevelFiles = new ArrayList<File>();
		for (File distanceFile : distanceRoot.listFiles()) {
			if (distanceFile.getName().startsWith("level_3")) {
				highLevelFiles.add(distanceFile);
			}
		}
		return highLevelFiles;
	}

	/**
	 * file name instead of path
	 * 
	 * @param highLevelName
	 * @return
	 */
	private static String name2id(String highLevelName) {
		return highLevelName.substring(8).replace(".txt", "").replace("+", ":");
	}

}
