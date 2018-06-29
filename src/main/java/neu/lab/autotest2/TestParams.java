package neu.lab.autotest2;

public class TestParams implements Comparable<TestParams>{
	
	String bottom;
	String top;
	Double prob;

	public TestParams(String bottom, String top, Double prob) {
		this.bottom = bottom;
		this.top = top;
		this.prob = prob;
	}

	public int compareTo(TestParams o) {
		if(this.prob-o.prob>0) {
			return -1;
		}else if(this.prob-o.prob<0) {
			return 1;
		}else {
			return bottom.hashCode()+top.hashCode()-(o.bottom.hashCode()+o.top.hashCode());
		}
	}

	@Override
	public String toString() {
		return "bottom=" + bottom + ", top=" + top + ", prob=" + prob + "]";
	}
	
}
