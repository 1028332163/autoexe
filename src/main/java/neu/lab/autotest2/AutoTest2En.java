package neu.lab.autotest2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neu.lab.autoexe.AutoExe;
import neu.lab.autotest.FileEval;
import neu.lab.autotest.MethodDistances;

public class AutoTest2En extends AutoExe{
	
	static String dir = "D:\\ws_testcase\\distance_mthdProb\\";

	public static void main(String[] args) throws Exception {
//		List<TestParams> params = new ArrayList<TestParams>();
//		for(File child:FileEval.getHighLevelFiles(new File(dir))) {
//			MethodDistances distances = new MethodDistances(child.getAbsolutePath());
//			params.addAll(distances.getTestParams());
//		}
//		Collections.sort(params);
//		for(TestParams param:params) {
//			System.out.println(param);
//		}
	}

	@Override
	protected void initPomPaths() {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void initFileSyn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getNextMvnCmd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean shouldSkip(String pomPath) {
		// TODO Auto-generated method stub
		return false;
	}
}
