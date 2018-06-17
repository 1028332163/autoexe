package neu.lab.autoexe.decca;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoSta extends AutoDecca {
	public String getBatPath() {
		return "D:\\ws\\sta.bat";
	}

	public String getCommand() {
		return "mvn neu.lab:decca:1.0:sta -e";
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_sta\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.staProjectDir;
	}
}
