package neu.lab.autotest.neighbor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import neu.lab.autotest.ProjectTester;

public class NeiborFileGetter {
	static String distanceRoot = "D:\\ws_testcase\\distance_cls\\";
	static Set<String> skipProjects;
	static {
		skipProjects = new HashSet<String>();
		skipProjects.add(distanceRoot + "level_3_eagle+eagle-embed-hbase+0.3.0-incubating.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.atlas+hive-bridge+0.7.1-incubating.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.camel+camel-spark-starter+2.19.4.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.carbondata+carbondata-spark2+1.1.1.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.crunch+crunch-hbase+0.11.0-cdh5.12.2.txt");
		skipProjects.add(distanceRoot + "level_3_org.apache.cxf.samples+spring-boot-sample-rs-scan-eureka+3.1.14.txt");
	}
	
	private static String levelDir = "D:\\ws_testcase\\distance_cls\\";
	public static void main(String[] args) throws Exception {
		int doneProjectNum = 0;
		//get pom2path
		Map<String, String> id2path = readId2path("projectFile\\projectId2path.txt");
		//get all high-level-file
		List<File> level3Files = new ArrayList<File>();
		for(File levelFile:new File(levelDir).listFiles()) {
			if(levelFile.getName().startsWith("level_3")){
				level3Files.add(levelFile);
			}
		}
		//get neighbor file of high-level-project
		for (File highLevelFile : level3Files) {
			System.out.println("==========highLevelProject:" + highLevelFile);
			if (skipProjects.contains(highLevelFile.getAbsolutePath())) {
				System.out.println("skip project:" + highLevelFile.getAbsolutePath());
				continue;
			}
			String mvnId = name2id(highLevelFile.getName());
				String pomPath = id2path.get(mvnId);
				if (pomPath != null) {
					String distanceFile = highLevelFile.getAbsolutePath();
					System.out.println("dis:" + distanceFile);
					System.out.println("pom:" + pomPath);
					String mvnCmd = getMvnCmd(pomPath);
					System.out.println("mvn cmd:" + mvnCmd);
					try {
						exeMvn(mvnCmd);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				} else {
					System.out.println("can't find pomFile for " + highLevelFile.getName());
				}

			doneProjectNum++;
			System.out.println("doneProject/all:" + doneProjectNum + "/" + level3Files.size());
		}
	}
	private static Map<String, String> readId2path(String filePath) throws Exception {
		Map<String, String> id2path = new HashMap<String, String>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
		String line = reader.readLine();
		while (line != null) {
			if (!line.equals("")) {
				String[] id_path = line.split(",");
				if (id_path.length == 2)
					id2path.put(id_path[0], id_path[1]);
			}
			line = reader.readLine();
		}
		reader.close();
		return id2path;
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
	
	private static void exeMvn(String mvnCmd) throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(mvnCmd);
		DefaultExecutor executor = new DefaultExecutor();
		executor.execute(cmdLine);
	}

	private static String getMvnCmd(String pomPath) {
//		mvn -f=D:\ws_testcase\projects\commons-pool2-2.5.0-src -Dmaven.test.skip=true neu.lab:decca:1.0:debug 
		String line = "cmd.exe /C ";
		line = line + "mvn -f="+pomPath+" -Dmaven.test.skip=true neu.lab:decca:1.0:debug";
		return line;
	}
}
