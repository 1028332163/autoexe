package neu.lab.autotest.prob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import neu.lab.autoexe.AutoExe;
import neu.lab.autoexe.ExeParam;
import neu.lab.autoexe.util.FileSyn;

public class AutoTest2En extends AutoExe {

	public AutoTest2En() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	static String dir = "D:\\ws_testcase\\distance_mthdBranch\\";

	public static void main(String[] args) throws Exception {
		new AutoTest2En().autoExe();
	}

	@Override
	protected List<ExeParam> getExeParams() {
		List<TestParams> params = new ArrayList<TestParams>();
		for (File child : new File(dir).listFiles()) {
			try {
				MthdProbDistances distances = new MthdProbDistances(child.getAbsolutePath());
				params.addAll(distances.getTestParams());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(params);
//		for (TestParams param : params) {
//			String paramStr = param.toString();
//			// if (!paramStr.startsWith("[bottom=<com.google.common"))
//			System.out.println(paramStr);
//		}
		//combine parameter that has same top class.
		List<ExeParam> returnParams = new ArrayList<ExeParam>();
		Set<String> sigs = new HashSet<String>();
		for(ExeParam param:params) {
			if(!sigs.contains(param.getParamSig())) {
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
