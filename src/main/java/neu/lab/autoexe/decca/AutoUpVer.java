package neu.lab.autoexe.decca;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoUpVer extends AutoDecca{

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_upVer\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.upVerProjectDir;
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\upVer.bat";
	}

	@Override
	public String getCommand() {
		return "mvn neu.lab:decca:1.0:upVersion -e";
	}

}
