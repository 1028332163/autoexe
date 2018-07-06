package neu.lab.autoexe;

import java.io.IOException;
import java.util.List;

import neu.lab.autoexe.util.ExecUtil;
import neu.lab.autoexe.util.FileSyn;

public abstract class AutoExe {

	protected List<ExeParam> exeParams;// list of pom-path
	protected FileSyn doneProject;

	public AutoExe() throws IOException {
		exeParams = getExeParams();
		doneProject = initFileSyn();
	}

	protected abstract List<ExeParam> getExeParams();

	protected abstract FileSyn initFileSyn() throws IOException;

	protected void beforeExeMvn() {
	}

	protected void afterExeMvn() {

	}

	public void autoExe() {
		System.out.println(" already done in last autotest is " + doneProject.recordNum());
		for (ExeParam param : exeParams) {
			if (!doneProject.contains(param.getParamSig())) {
				if (!shouldSkip(param.getSkipParam())) {
					String mvnCmd = param.getMvnCmd();
					beforeExeMvn();
					try {
						 ExecUtil.exeMvn(mvnCmd);
					} catch (Exception e) {
						e.printStackTrace();
					}
					afterExeMvn();
				} else {
					System.out.println("skip project:" + param.getParamSig());
				}
			} else {
				System.out.println(param.getParamSig() + " was executed.");
			}
			doneProject.add(param.getParamSig());
			System.out.println("doneProject/all:" + doneProject.recordNum() + "/" + exeParams.size());
		}
	}

	protected abstract boolean shouldSkip(Object pomPath);

}
