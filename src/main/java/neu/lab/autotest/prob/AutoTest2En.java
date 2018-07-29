package neu.lab.autotest.prob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import neu.lab.autoexe.AutoExe;
import neu.lab.autoexe.ExeParam;
import neu.lab.autoexe.util.DebugUtil;
import neu.lab.autoexe.util.FileSyn;
import neu.lab.autoexe.util.MvnId2path;

public class AutoTest2En extends AutoExe {

	public AutoTest2En() throws IOException {
		super();
	}

	static String id2pathFile = MvnId2path.newPath;
	static String dir = "D:\\ws_testcase\\image_new_50_20M\\distance";

//		static String id2pathFile = MvnId2path.latestPath;
//		static String dir = "D:\\ws_testcase\\image_latest_50_20M\\distance";

	//		 static String id2pathFile = MvnId2path.developPath;
//		 static String dir = "D:\\ws_testcase\\distance_mthdBranch";

	public static void main(String[] args) throws Exception {
//		 for (String pomPath : new AutoTest2En().getAllProjectPath()) {
//		 System.out.println(DebugUtil.getAddPomCode(pomPath));
//		 }
		new AutoTest2En().printStaInfo();
	}

	public void printStaInfo() {
		Map<String, Map<String, Set<String>>> pom2conflict2mthds = new TreeMap<String, Map<String, Set<String>>>();
		for (ExeParam exeParam : this.exeParams) {
			TestParam param = (TestParam) exeParam;
			Map<String, Set<String>> conflict2mthd = pom2conflict2mthds.get(param.getPompath());
			if (conflict2mthd == null) {
				conflict2mthd = new TreeMap<String, Set<String>>();
				pom2conflict2mthds.put(param.getPompath(), conflict2mthd);
			}
			Set<String> riskMthds = conflict2mthd.get(param.getDistanceFile());
			if (riskMthds == null) {
				riskMthds = new TreeSet<String>();
				conflict2mthd.put(param.getDistanceFile(), riskMthds);
			}
			riskMthds.add(param.getBottom());
		}

		int conflictCnt = 0;
		int riskMthdCnt = 0;
		Set<String> projects = new TreeSet<String>();
		for (String pomPath : pom2conflict2mthds.keySet()) {
			System.out.println("module:" + pomPath);
		}
		System.out.println();

		for (String pomPath : pom2conflict2mthds.keySet()) {
			projects.add(pomPath.split("\\\\")[3]);
			System.out.println("module:" + pomPath);
			Map<String, Set<String>> conflict2mthd = pom2conflict2mthds.get(pomPath);
			for (String conflict : conflict2mthd.keySet()) {
				System.out.println("conflict:" + conflict);
				conflictCnt++;
				Set<String> riskMthds = conflict2mthd.get(conflict);
				for (String riskMthd : riskMthds) {
					System.out.println("riskMthd:" + riskMthd);
					riskMthdCnt++;
				}
			}
			System.out.println();
		}
		System.out.println("project size:" + projects.size() + ",module size:" + pom2conflict2mthds.keySet().size()
				+ ", conflict size:" + conflictCnt + ", riskMthd size:" + riskMthdCnt);
	}

	public Set<String> getAllProjectPath() {
		Set<String> projectPaths = new HashSet<String>();
		Map<String, String> id2path = MvnId2path.getId2path(id2pathFile);
		for (ExeParam exeParam : this.exeParams) {
			TestParam param = (TestParam) exeParam;
			String path = param.getPompath();
			if (path != null) {
				projectPaths.add(path);
				// System.out.println(path);
				// System.out.println();
			} else {
				System.out.println("can't find path for " + MvnId2path.filePath2mvnId(param.distanceFile));
			}
		}
		return projectPaths;
	}

	/**
	 * bottomMthd-distanceFile as signature.
	 */
	public void printRiskMthdNum() {
		Set<String> staSigs = new HashSet<String>();// bottomMthd-distanceFile
		for (ExeParam exeParam : this.exeParams) {
			TestParam param = (TestParam) exeParam;
			String staSig = param.getBottom() + param.getDistanceFile();
			if (!staSigs.contains(staSig)) {
				System.out.println(param);
				System.out.println();
				staSigs.add(staSig);
			}
		}
	}

	@Override
	protected List<ExeParam> getExeParams() {
		List<TestParam> params = new ArrayList<TestParam>();
		for (File child : new File(dir).listFiles()) {
			try {
				MthdProbDistances distances = new MthdProbDistances(child.getAbsolutePath());
				params.addAll(distances.getTestParams());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collections.sort(params);

		// combine parameter that has same top class.
		List<ExeParam> returnParams = new ArrayList<ExeParam>();
		Set<String> sigs = new HashSet<String>();// bottomMthd-topClass-distanceFile.

		for (TestParam param : params) {
			if (!sigs.contains(param.getParamSig())) {
				sigs.add(param.getParamSig());
				returnParams.add(param);
			}
		}
		return returnParams;
	}

	@Override
	protected FileSyn initFileSyn() throws IOException {
		return new FileSyn("D:\\ws_testcase\\image\\state_autoBranchEvo\\doneProjct.txt");
	}

	@Override
	protected boolean shouldSkip(Object pomPath) {
		return false;
	}
}
