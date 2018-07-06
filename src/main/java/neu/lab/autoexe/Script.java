package neu.lab.autoexe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import neu.lab.autoexe.util.DistanceFileUtil;
import neu.lab.autoexe.util.MvnId2path;

public class Script {
	public static void main(String[] args) throws Exception {
		staClsDep();
	}

	public static void staClsDep() throws Exception {
		String distanceDir = "D:\\ws_testcase\\image_classLevel\\distance_cls";
		Map<String,String> id2path = MvnId2path.getOldId2path();
		for (File file : new File(distanceDir).listFiles()) {
			if(file.getName().startsWith("level_3")) {
//				System.out.println(1);
				List<String> toPrint = new ArrayList<String>();
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					if (!line.equals("")) {
						String[] btdh = DistanceFileUtil.readClsDisLine(line);
						double distance = Double.valueOf(btdh[2]);
						boolean isFromHost = Boolean.valueOf(btdh[3]);
						if(isFromHost&&distance<3) {
							toPrint.add(line);
						}
					}
					line = reader.readLine();
				}
				if(toPrint.size()>0) {
//					System.out.println(file.getAbsolutePath());
//					System.out.println(id2path.get(MvnId2path.file2mvnId(file.getName())));
//					for(String line2print:toPrint) {
//						System.out.println(line2print);
//					}
					
				}
				reader.close();
			}
			
		}
	}
}
