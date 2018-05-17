package neu.lab.autoexe;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoDebug extends AutoExe {
	public String getBatPath() {
		return "D:\\ws\\debug.bat";
	}

	public String getCommand() {
		return "mvn -Dmaven.test.skip=true package neu.lab:decca:1.0:debug -e";
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_debug\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.debugDir;
	}
}
