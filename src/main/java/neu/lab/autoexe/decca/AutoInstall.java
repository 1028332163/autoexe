package neu.lab.autoexe.decca;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoInstall extends AutoDecca{

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_install\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.installProjectDir;
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\aotoInstall.bat";
	}

	@Override
	public String getCommand() {
		return "mvn clean package install -Dmaven.test.skip=true -e";
	}

}
