package neu.lab.autoexe.decca;


public class AutoFindVer extends AutoDecca{

	public AutoFindVer(String projectDir) {
		super(projectDir);
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_findVer\\";
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\findVer.bat";
	}

	@Override
	public String getCommand() {
		return "mvn neu.lab:decca:1.0:findVersion -e";
	}

}
