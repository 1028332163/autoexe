package neu.lab.autoexe.decca;


public class AutoInstall extends AutoDecca{

	public AutoInstall(String projectDir) {
		super(projectDir);
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_install\\";
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
