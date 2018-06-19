package neu.lab.autotest.post;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
	public static void main(String[] args) throws Exception {
//		staDepth();
		staCov();
	}

	public static void staDepth() throws Exception {
		List<Double> depths = new ArrayList<Double>();
		File dir = new File("D:\\ws_testcase\\distance_cls\\");
		// read all file
		for (File levelFile : dir.listFiles()) {
			if (levelFile.getName().startsWith("level_3")) {
				System.out.println(levelFile.getName());
				BufferedReader reader = new BufferedReader(new FileReader(levelFile));
				String line = reader.readLine();
				while (line != null) {
					if (!line.equals("")) {
						String[] b_t_d_h = line.split(",");
						if (b_t_d_h[3].equals("true")) {
							depths.add(Double.valueOf(b_t_d_h[2]));
						}
					}
					line = reader.readLine();
				}
				reader.close();
			}
		}
		// write all file
		PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter("D:\\ws_testcase\\depSta.txt")));
		for (Double depth : depths) {
			printer.println(depth);
		}
		printer.close();
	}

	public static void staCov() throws Exception {
		Map<Integer, Integer> project2neiborNum = new HashMap<Integer, Integer>();
		List<Integer> covs = new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader(new FileReader("D:\\ws_testcase\\log_neibortest.txt"));
		Integer projectOrder = 0;
		String line = reader.readLine();

		while (line != null) {
			if (!line.equals("")) {
				if (line.startsWith("projectOrder:")) {
					projectOrder = Integer.valueOf(line.substring(line.indexOf(":") + 1));
				} else if (line.startsWith("doneProject/all:")) {
					project2neiborNum.put(projectOrder, Integer.valueOf(line.substring(line.indexOf(":")+1,line.lastIndexOf("/"))));
				}else if(line.startsWith("[INFO] INFO  evo_logger - * Coverage of criterion LINE: ")) {
					covs.add(Integer.valueOf(line.substring(line.lastIndexOf(" ")+1,line.lastIndexOf("%"))));
				}
			}
			line = reader.readLine();
		}
		reader.close();
		
		PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter("D:\\ws_testcase\\covrageSta.txt")));
		for(Integer cov:covs) {
			printer.println(cov);
		}
		printer.close();
		int totalNum = 0;
		for(Integer num:project2neiborNum.values()) {
			totalNum+=num;
		}
		System.out.println("success/total num:"+covs.size()+"/"+totalNum);
	}
}
