package neu.lab.autoexe.decca;


public class AutoFindVer extends AutoDecca{

	public AutoFindVer(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn neu.lab:decca:1.0:findVersion -e";
	}

}
