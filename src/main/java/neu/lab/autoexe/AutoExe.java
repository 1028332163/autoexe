package neu.lab.autoexe;

import java.util.List;
import java.util.Set;


import neu.lab.autoexe.util.ExecUtil;
import neu.lab.autoexe.util.FileSyn;

public abstract class AutoExe {

	protected List<String> pomPaths;// list of pom-path
	protected FileSyn doneProject;

	public AutoExe() {
		initPomPaths();
		initFileSyn();
	}

	protected abstract void initPomPaths();


	protected abstract void initFileSyn();

	protected abstract String getNextMvnCmd();

	protected void beforeExeMvn() {

	}

	protected void afterExeMvn() {

	}

	public void autoExe() {
		System.out.println(" already done in last autotest is " + doneProject.recordNum());
		for (String pomPath : pomPaths) {
			if (!doneProject.contains(pomPath)) {
				if (!shouldSkip(pomPath)) {
					String mvnCmd = getNextMvnCmd();
					beforeExeMvn();
					try {
						ExecUtil.exeMvn(mvnCmd);
					} catch (Exception e) {
						e.printStackTrace();
					}
					afterExeMvn();
				} else {
					System.out.println("skip project:" + pomPath);
				}
			} else {
				System.out.println(pomPath + " was executed.");
			}
			doneProject.add(pomPath);
			System.out.println("doneProject/all:" + doneProject.recordNum() + "/" + pomPaths.size());
		}
	}
	
	protected abstract boolean shouldSkip(String pomPath);


}
