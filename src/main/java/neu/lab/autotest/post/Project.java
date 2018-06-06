package neu.lab.autotest.post;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Project {

	private String pomPath;
	private Map<String, Set<String>> cls2expClses;// use distance-file as classId.
	private Set<String> riskClses;
	
	public Project(String pomPath) {
		this.pomPath = pomPath;
		cls2expClses = new HashMap<String,Set<String>>();
	}
	
	public void addExpCls(String testedCls,String expCls) {
		Set<String> expClses = cls2expClses.get(testedCls);
		if(expClses==null) {
			expClses = new HashSet<String>();
			cls2expClses.put(testedCls, expClses);
		}
		expClses.add(expCls);
	}

	public void setRiskClses(Set<String> riskClses) {
//		System.out.println("risk class in:" + pomPath);
		for (String riskCls : riskClses) {
//			System.out.println("risk class:"+riskCls+"|");
		}
		this.riskClses = riskClses;
	}
	
	public String getNeededExpClses() {
		StringBuilder sb = new StringBuilder();
		for(String testedCls:cls2expClses.keySet()) {
			Set<String> neededExpClses = getNeededExpClses(testedCls);
			if(neededExpClses.size()>0) {
				sb.append("testedClass:"+testedCls+System.lineSeparator());
				for(String neededExpCls:neededExpClses) {
					sb.append("riskClass:"+neededExpCls+System.lineSeparator());
				}
			}
		}
		return sb.toString();
	}
	
	private Set<String> getNeededExpClses(String testedCls){
		Set<String> neededExpClses = new HashSet<String>();
//		if("org.apache.airavata.client.stub.interpretor.WorkflowInterpretorStub".equals(testedCls)) {
//			System.out.println(riskClses.size());
//			for(String riskCls:riskClses) {
//				System.out.println("risk class "+riskCls);
//			}
//		}
		for(String expCls:cls2expClses.get(testedCls)) {
			System.out.println("expCls:"+expCls+" in "+pomPath+ " "+testedCls);
			if(riskClses.contains(expCls)) {
				System.out.println("thank goodness:"+expCls);
				neededExpClses.add(expCls);
			}
		}
		return neededExpClses;
	}

	public String getPomPath() {
		return pomPath;
	}


}
