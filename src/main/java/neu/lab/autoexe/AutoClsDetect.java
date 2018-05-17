package neu.lab.autoexe;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoClsDetect extends AutoExe{

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_clsDetect\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.clsDetectProjectDir;
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\clsDetect.bat";
	}

	@Override
	public String getCommand() {
		return "mvn  package -Dmaven.test.skip=true neu.lab:decca:1.0:classDetect -Dappend=true -e";
	}

}
