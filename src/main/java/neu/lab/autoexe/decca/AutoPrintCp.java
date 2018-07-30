package neu.lab.autoexe.decca;

public class AutoPrintCp extends AutoDecca{

	public AutoPrintCp(String projectDir) {
		super(projectDir);
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws_testcase\\image\\state_printCp\\";
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\printCp.bat";
	}

	@Override
	public String getCommand() {
		return "mvn -Dmaven.test.skip=true package neu.lab:decca:1.0:printCp -e";
	}

}
