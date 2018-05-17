package neu.lab.autotest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MethodDistance {
	private Map<String, Map<String, Double>> distances;// <bottom-method,<top-method,distance>>

	private Map<String, Map<String, Double>> clsDistes;//<top-class,<bottom-class,distance>>

	public MethodDistance(String distanceFile) throws Exception {
		initMthdDist(distanceFile);
		initClsDist();
	}

	private void initMthdDist(String distanceFile) throws Exception {
		distances = new HashMap<String, Map<String, Double>>();
		BufferedReader reader = new BufferedReader(new FileReader(distanceFile));
		String line = reader.readLine();
		while (line != null) {
			if (!"".equals(line)) {
				String[] mmdh = line.split(",");// method-method-distance-host
				if ("true".equals(mmdh[3])) {
					String bottom = mmdh[0];
					String top = mmdh[1];
					Double distance = Double.valueOf(mmdh[2]);
					Map<String, Double> m2d = distances.get(bottom);
					if (null == m2d) {
						m2d = new HashMap<String, Double>();
						distances.put(bottom, m2d);
					}
					m2d.put(top, distance);
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}

	private void initClsDist() {
		clsDistes = new HashMap<String, Map<String, Double>>();
		for(String bottomMthd:distances.keySet()) {
			
		}
	}
}
