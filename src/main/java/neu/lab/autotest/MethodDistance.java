package neu.lab.autotest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MethodDistance implements NodeDistance{
	private Map<String, Map<String, Double>> m_b2t2d;// <bottom-method,<top-method,distance>>

	private Map<String, Map<String, Double>> c_t2p2d;// <top-class,<bottom-class,distance>>

	public MethodDistance(String distanceFile) throws Exception {
		initMthdDist(distanceFile);
		initClsDist();
	}

	private void initMthdDist(String distanceFile) throws Exception {
		m_b2t2d = new HashMap<String, Map<String, Double>>();
		BufferedReader reader = new BufferedReader(new FileReader(distanceFile));
		String line = reader.readLine();
		while (line != null) {
			if (!"".equals(line)) {
				String[] mmdh = line.split(">,");// method-method-distance-host
				if ("true".equals(mmdh[2].split(",")[1])) {
					String bottom = mmdh[0] + ">";
					String top = mmdh[1] + ">";
					Double distance = Double.valueOf(mmdh[2].split(",")[0]);
					Map<String, Double> t2d = m_b2t2d.get(bottom);
					if (null == t2d) {
						t2d = new HashMap<String, Double>();
						m_b2t2d.put(bottom, t2d);
					}
					t2d.put(top, distance);
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}

	public String getNextExe(Set<String> exedClses) {
		int maxRchNum = 0;
		String nextCls = null;
		for (String topCls : c_t2p2d.keySet()) {
			if (!exedClses.contains(topCls)) {
				int rchNum = c_t2p2d.get(topCls).size();
				if (rchNum > maxRchNum) {
					nextCls = topCls;
					maxRchNum = rchNum;
				}
			}
		}
		return nextCls;
	}

	public int getEntryClsNum() {
		return c_t2p2d.keySet().size();
	}

	private void initClsDist() {
		c_t2p2d = new HashMap<String, Map<String, Double>>();
		for (String bottomMthd : m_b2t2d.keySet()) {
			Map<String, Double> m_t2d = m_b2t2d.get(bottomMthd);
			String bottomCls = mthdSig2cls(bottomMthd);
			for (String topMthd : m_t2d.keySet()) {
				Double newDist = m_t2d.get(topMthd);
				String topCls = mthdSig2cls(topMthd);
				Map<String, Double> c_b2d = c_t2p2d.get(topCls);
				if (null == c_b2d) {
					c_b2d = new HashMap<String, Double>();
					c_t2p2d.put(topCls, c_b2d);
				}
				Double oldDist = c_b2d.get(bottomCls);
				if (oldDist == null || newDist < oldDist) {
					c_b2d.put(bottomCls, newDist);
				}
			}
		}
	}

	/**
	 * @param mthdSig
	 *            eg.:<org.slf4j.event.SubstituteLoggingEvent: org.slf4j.event.Level
	 *            getLevel()>
	 * @return eg.:org.slf4j.event.SubstituteLoggingEvent
	 */
	private String mthdSig2cls(String mthdSig) {
		return mthdSig.substring(1, mthdSig.indexOf(":"));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("all entry class:" + c_t2p2d.size());
		// for (String source : c_t2p2d.keySet()) {
		// Map<String, Double> dises = c_t2p2d.get(source);
		// for (String target : dises.keySet()) {
		// sb.append(source + "," + target + "," + dises.get(target));
		// sb.append(System.lineSeparator());
		// }
		// }
		return sb.toString();
	}
}
