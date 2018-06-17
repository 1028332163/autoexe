package neu.lab.autoexe;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import neu.lab.autoexe.util.ExecUtil;
import neu.lab.autoexe.util.FileSyn;

public abstract class AutoExe {

	protected List<String> pomPaths;// list of pom-path
	protected Set<String> skipProject;
	protected FileSyn doneProject;

	public AutoExe() {
		initPomPaths();
		initSkip();
		initFileSyn();
	}

	protected abstract void initPomPaths();

	protected abstract void initSkip();

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
				if (!skipProject.contains(pomPath)) {
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


}
