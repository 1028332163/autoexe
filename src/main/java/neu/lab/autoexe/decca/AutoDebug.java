package neu.lab.autoexe.decca;


public class AutoDebug extends AutoDecca {
	public AutoDebug(String projectDir) {
		super(projectDir);
	}

	public String getBatPath() {
		return "D:\\ws\\debug.bat";
	}

	public String getCommand() {
		return "mvn -Dmaven.test.skip=true package neu.lab:decca:1.0:debug -e";
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws_testcase\\image\\state_debug\\";
	}

}
