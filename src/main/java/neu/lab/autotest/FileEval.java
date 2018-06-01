package neu.lab.autotest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * evaluate the level of file . level-0:file is empty. level-1:file has data.
 * level-2:file has record from host.
 * level-3:file has record from host && class is not inner-class.
 * @author asus
 *
 */
public class FileEval {
	public static void main(String[] args) throws Exception {
		String dirPath = "D:\\ws_testcase\\distance_cls";
		File dir = new File(dirPath);
		for (File distanceFile : dir.listFiles()) {
			if (!distanceFile.getName().startsWith("level_"))
				evalFile(distanceFile);
		}
	}

	public static void evalFile(File distanceFile) throws Exception {
		System.out.println("evaluate file: " + distanceFile.getName());
		BufferedReader reader = new BufferedReader(new FileReader(distanceFile));
		String line = reader.readLine();
		int level = 0;
		while (line != null) {
			if (!"".equals(line)) {
				if (level < 1)
					level = 1;
				if (line.endsWith("true")) {
					if (level < 2)
						level = 2;
					if(!line.contains("$")) {
						level=3;
						break;
					}
				}
			}
			line = reader.readLine();
		}
		if (level != 0)
			System.out.println(distanceFile + " level is " + level);
		reader.close();
		distanceFile.renameTo(
				new File(distanceFile.getParent() + File.separator + "level_" + level + "_" + distanceFile.getName()));
	}
}
