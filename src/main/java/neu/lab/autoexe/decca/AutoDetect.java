package neu.lab.autoexe.decca;


public class AutoDetect extends AutoDecca {
	public AutoDetect(String projectDir) {
		super(projectDir);
	}

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
}
