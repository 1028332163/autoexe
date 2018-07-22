package neu.lab.autoexe.pick;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import neu.lab.autoexe.util.FileUtil;
import neu.lab.autoexe.util.ZipUtil;

public class Picker {
	// private Map<String,String> project2path;

	public Picker() {
		// project2path = getExistMap();
	}

	// private Map<String,String> getExistMap(){
	// return new HashMap<String,String>();
	// }
	public void pick(List<String> projectList) throws Exception {
		for(String projectPath:projectList) {
			System.out.println(projectPath);
			pick(projectPath);
		}
	}

	/**
	 * @param projectPath
	 *            D:\ws\gitHub_old\activemq-cpp-activemq-cpp-pom-3.1.0-RC1\activemq-cpp-openwire-generator\pom.xml
	 * @throws Exception
	 */
	public void pick(String projectPath) throws Exception {
		String projectSubDir = projectPath.replace(Conf.DIR_ALL_OLD, "").split("\\\\")[0];// activemq-cpp-activemq-cpp-pom-3.1.0-RC1
		if (!new File(Conf.TMP_DIR + projectSubDir + ".zip").exists()) {
			System.out.println(Conf.TMP_DIR + projectSubDir + ".zip doesn't exist");
			if (!new File(Conf.DIR_ALL_OLD + projectSubDir + ".zip").exists()) {
				System.out.println(Conf.DIR_ALL_OLD + projectSubDir + ".zip doesn't exist");
				ZipUtil.zip(Conf.DIR_ALL_OLD + projectSubDir, Conf.DIR_ALL_OLD, projectSubDir + ".zip");
			}
			FileUtil.moveFile(Conf.DIR_ALL_OLD + projectSubDir + ".zip", Conf.TMP_DIR);
		}
//		ZipUtil.unzip(Conf.TMP_DIR+projectSubDir + ".zip", Conf.TMP_DIR+projectSubDir);
	}
//	/**
//	 * @param wholeProjectPath  D:\ws\gitHub_old\activemq-cpp-activemq-cpp-pom-3.1.0-RC1\
//	 * @param subProjectPath  D:\ws\gitHub_old\activemq-cpp-activemq-cpp-pom-3.1.0-RC1\activemq-cpp-openwire-generator\pom.xml
//	 */
//	public void deleteUnuse(String wholeProjectPath,String subProjectPath) {
//		
//	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("D:\\cWS\\notepad++\\projectList.txt"));
		String line = reader.readLine();
		List<String> projectList = new ArrayList<String>();
		while(line!=null) {
			if(line.contains("@")) {
				projectList.add(line.substring(line.lastIndexOf("@")+1));
			}
			line = reader.readLine();
		}
		new Picker().pick(projectList);
		reader.close();
	}

}
