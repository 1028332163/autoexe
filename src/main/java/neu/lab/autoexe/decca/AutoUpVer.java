package neu.lab.autoexe.decca;


public class AutoUpVer extends AutoDecca{

	public AutoUpVer(String projectDir) {
		super(projectDir);
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_upVer\\";
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\upVer.bat";
	}

	@Override
	public String getCommand() {
		return "mvn neu.lab:decca:1.0:upVersion -e";
	}

}
