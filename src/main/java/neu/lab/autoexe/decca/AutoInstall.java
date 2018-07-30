package neu.lab.autoexe.decca;


public class AutoInstall extends AutoDecca{

	public AutoInstall(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn clean package install -Dmaven.test.skip=true -e";
	}

}
