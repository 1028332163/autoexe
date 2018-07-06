package neu.lab.autoexe.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MvnId2path {
	static String newPath = "projectFile\\id2path_new.txt";
	static String oldPath = "projectFile\\id2path_old.txt";

	public static Map<String, String> getNewId2path() {
		return getId2path(newPath);
	}

	public static Map<String, String> getOldId2path() {
		return getId2path(oldPath);
	}

	public static String filePath2mvnId(String filePath) {
		return fileName2mvnId(new File(filePath).getName());
	}

	public static String fileName2mvnId(String fileName) {
		if (fileName.startsWith("level_"))
			fileName = fileName.substring(8);
		fileName = fileName.substring(0, fileName.length() - 4);// delete .txt
		if (fileName.indexOf("@") != -1) {
			fileName = fileName.substring(0, fileName.indexOf("@"));
		}
		return fileName.replace("+", ":");
	}
	
	public static String getNewPath(String mvnSig) {
		return getNewId2path().get(mvnSig);
	}

	private static Map<String, String> getId2path(String idPathFile) {
		Map<String, String> id2path = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(idPathFile));
			String line = reader.readLine();
			while (line != null) {
				if (!line.equals("")) {
//					System.out.println(line);
					String[] id_path = line.split(",");
					 if(id_path.length==2)
					id2path.put(id_path[0], id_path[1].replace("+", "\\"));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id2path;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MvnId2path.getNewPath("org.apache.continuum:continuum-scm:1.4.3"));
//		Map<String, String> id2path = new PomFinder().getId2path(new File("D:\\ws\\gitHub_old\\"));
//		PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(oldPath)));
//		for (String id : id2path.keySet()) {
//			String path = id2path.get(id).replace("\\", "+");
//			printer.println(id + "," + path);
//		}
//		printer.close();
	}
}
