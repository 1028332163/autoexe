package neu.lab.autotest.prob;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neu.lab.autotest.MethodDistances;

public class MthdProbDistances extends MethodDistances {
	private String ditanceFile;
	private Map<String, Map<String, Double>> m_b2t2p;

	public MthdProbDistances(String distanceFile) throws Exception {
		super(distanceFile);
		this.ditanceFile = distanceFile;
		initMthdProb(distanceFile);
	}

	private void initMthdProb(String distanceFile) throws Exception {
		m_b2t2p = new HashMap<String, Map<String, Double>>();
		BufferedReader reader = new BufferedReader(new FileReader(distanceFile));
		String line = reader.readLine();
		while (line != null) {
			if (!"".equals(line)) {
				// method-method-distance-host
				String[] mmdh = line.split(">,");
				if (shoudAdd(mmdh)) {
					String bottom = mmdh[0] + ">";
					String top = mmdh[1] + ">";
					Double distance = Double.valueOf(mmdh[2].split(",")[2]);
					Map<String, Double> t2d = m_b2t2p.get(bottom);
					if (null == t2d) {
						t2d = new HashMap<String, Double>();
						m_b2t2p.put(bottom, t2d);
					}
					t2d.put(top, distance);
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}

	public List<TestParam> getTestParams() {
		List<TestParam> params = new ArrayList<TestParam>();
		for (String bottom : m_b2t2d.keySet()) {
			Map<String, Double> t2d = m_b2t2d.get(bottom);
			Map<String, Double> t2p = m_b2t2p.get(bottom);
			for (String top : t2d.keySet()) {
				params.add(new TestParam(bottom, top, t2d.get(top), t2p.get(top), ditanceFile));
			}
		}
		return params;
	}

}
