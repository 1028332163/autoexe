package neu.lab.autoexe.decca;


public class AutoUpVer extends AutoDecca{

	public AutoUpVer(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn neu.lab:decca:1.0:upVersion -e";
	}

}
