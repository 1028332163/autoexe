package abandon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeFileSyn {
//	private Map<String,Integer> pro2time;
//	private PrintWriter printer;
//	public TimeFileSyn(String stateDir,String fileName) throws IOException{
//		File dir = new File(stateDir);
//		if (!dir.exists())
//			dir.mkdirs();
//		pro2time = readFile(stateDir + fileName);
//		printer = new PrintWriter(new BufferedWriter(new FileWriter(stateDir + fileName, true)));
//	}
//	
//	private Map<String,Integer> readFile(String filePath) {
//		Map<String,Integer> fileList = new HashMap<String,Integer>();
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(filePath));
//			String line = reader.readLine();
//			while (line != null) {
//				if (!fileList.contains(line)) {
//					fileList.add(line);
//				}
//				line = reader.readLine();
//			}
//			reader.close();
//		} catch (Exception e) {
//		}
//		return fileList;
//	}
}
