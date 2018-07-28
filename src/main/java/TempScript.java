import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TempScript {
	
	public static void main(String[] args) throws Exception {
		List<String> inFilePaths = new ArrayList<String>();
		inFilePaths.add("D:\\ws_testcase\\image_develop_100_20M\\state_debug_0_50\\Project_build_success.txt");
		inFilePaths.add("D:\\ws_testcase\\image_develop_100_20M\\state_debug_50_70\\Project_build_success.txt");
		inFilePaths.add("D:\\ws_testcase\\image_develop_100_20M\\state_debug_71_100\\Project_build_success.txt");
		inFilePaths.add("D:\\ws_testcase\\image_develop_100_20M\\state_debug_20M\\Project_build_success.txt");
		String outPath = "D:\\ws_testcase\\image_develop_100_20M\\Project_build_success.txt";
		combineFile(inFilePaths,outPath);
	}
	private static void combineFile(List<String> inFilePaths,String outPath) {
		try {
			PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(outPath)));
			for(String inFilePath:inFilePaths) {
				BufferedReader reader = new BufferedReader(new FileReader(inFilePath));
				String line = reader.readLine();
				while (line != null) {
					if (!line.equals("")) {
						printer.println(line);
					}
					line = reader.readLine();
				}
				reader.close();
			}
			printer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void printAllNullPoint() throws Exception{
		Set<String> nullPositions = new TreeSet<String>();
		BufferedReader reader = new BufferedReader(new FileReader("D:\\cWs\\notepad++\\nullpoint.txt"));
		String line = reader.readLine();
		boolean getNext = false;
		while (line != null) {
			if (!line.equals("")) {
				if(line.startsWith("java.lang.NullPointerException")) {
					getNext = true;
				}else {
					if(getNext == true) {
						nullPositions.add(line);
						getNext = false;
					}
				}
			}
			line = reader.readLine();
		}
		reader.close();
		for(String nullPos:nullPositions) {
			System.out.println(nullPos);
		}
	}
}
