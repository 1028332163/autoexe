package neu.lab.autotest.prob;

import java.util.Map;

import neu.lab.autoexe.ExeParam;
import neu.lab.autoexe.util.MvnId2path;

public class TestParam implements Comparable<TestParam>, ExeParam {
	static Map<String, String> id2path;
	static {
		id2path = MvnId2path.getId2path(AutoTest2En.id2pathFile);
	}
	String bottom;
	String top;
	Double distance;
	Double prob;
	String distanceFile;

	public TestParam(String bottom, String top, Double distance, Double prob, String distanceFile) {
		this.bottom = bottom;
		this.top = top;
		this.distance = distance;
		this.prob = prob;
		this.distanceFile = distanceFile;
	}

	public String getBottom() {
		return bottom;
	}

	public String getDistanceFile() {
		return distanceFile;
	}

	public int compareTo(TestParam o) {
		 if (this.distance - o.distance > 0) {
			return 1;
		} else if (this.distance - o.distance < 0) {
			return -1;
		} else
		if (this.prob - o.prob > 0) {
			return 1;// des
		} else if (this.prob - o.prob < 0) {
			return -1;
		} else {
			return bottom.hashCode() + top.hashCode() - (o.bottom.hashCode() + o.top.hashCode());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bottom == null) ? 0 : bottom.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((prob == null) ? 0 : prob.hashCode());
		result = prime * result + ((top == null) ? 0 : top.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TestParam other = (TestParam) obj;
		if (bottom == null) {
			if (other.bottom != null) {
				return false;
			}
		} else if (!bottom.equals(other.bottom)) {
			return false;
		}
		if (distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!distance.equals(other.distance)) {
			return false;
		}
		if (prob == null) {
			if (other.prob != null) {
				return false;
			}
		} else if (!prob.equals(other.prob)) {
			return false;
		}
		if (top == null) {
			if (other.top != null) {
				return false;
			}
		} else if (!top.equals(other.top)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return  bottom + "\n" + top + "\ndistance=" + distance + ", prob=" + prob + ", distanceFile="
				+ distanceFile + "]+\n"+getMvnCmd();
	}

	private String getTopClass() {
		// <neu.lab.plug.testcase.homemade.b.B1: void m2()>
		return top.split(":")[0].substring(1);
	}

	public String getPompath() {
		return id2path.get(MvnId2path.filePath2mvnId(distanceFile));
	}

	public String getMvnCmd() {
		String mvnCmd = "cmd.exe /C ";
		mvnCmd += ("mvn org.evosuite.plugins:evosuite-maven-plugin:8.15:generate -f="
				+ getPompath() + " -Dclass=" + getTopClass() + " -Dcriterion=MTHD_PROB_RISK " + "-Drisk_method=\""
				+ bottom + "\" " + "-Dmthd_prob_distance_file=" + distanceFile + " -Dmaven.test.skip=true -e");
		return mvnCmd;
	}

	public String getParamSig() {
		return bottom + getTopClass() + distanceFile;
	}

	public Object getSkipParam() {
		return null;
	}

}
