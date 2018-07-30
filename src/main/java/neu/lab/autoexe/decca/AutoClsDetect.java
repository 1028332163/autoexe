package neu.lab.autoexe.decca;


public class AutoClsDetect extends AutoDecca{

	public AutoClsDetect(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn  package -Dmaven.test.skip=true neu.lab:decca:1.0:classDetect -Dappend=true -e";
	}

}
