package neu.lab.autotest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClassDistance implements NodeDistance{
	private Map<String, Map<String, Double>> t2b2d;// <top-class,<bottom-class,distance>>

	public ClassDistance(String distanceFile) throws Exception {
		initClsDist(distanceFile);
	}

	private void initClsDist(String distanceFile) throws Exception {
		t2b2d = new HashMap<String, Map<String, Double>>();
		BufferedReader reader = new BufferedReader(new FileReader(distanceFile));
		String line = reader.readLine();
		while (line != null) {
			if (!"".equals(line)) {
				if (line.endsWith("true")) {
					String[] ccdh = line.split(",");
					String bottom = ccdh[0];
					String top = ccdh[1];
					Double distance = Double.valueOf(ccdh[2]);
					Map<String, Double> b2d = t2b2d.get(top);
					if (b2d == null) {
						b2d = new HashMap<String, Double>();
						t2b2d.put(top, b2d);
					}
					b2d.put(bottom, distance);
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}

	public String getNextExe(Set<String> exedClses) {
		int maxRchNum = 0;
		String nextCls = null;
		for (String topCls : t2b2d.keySet()) {
			if (!exedClses.contains(topCls)) {
				int rchNum = t2b2d.get(topCls).size();
				if (rchNum > maxRchNum) {
					nextCls = topCls;
					maxRchNum = rchNum;
				}
			}
		}
		return nextCls;
	}

	public int getEntryClsNum() {
		return t2b2d.keySet().size();
	}
}
