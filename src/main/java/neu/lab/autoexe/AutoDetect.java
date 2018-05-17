package neu.lab.autoexe;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoDetect extends AutoExe {
	public String getBatPath() {
		return "D:\\ws\\detect.bat";
	}

	public String getCommand() {
		return "mvn package neu.lab:decca:1.0:detect -Dmaven.test.skip=true -e";
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_detect\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.detectProjectDir;
	}

}
