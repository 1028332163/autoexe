package neu.lab.autoexe.decca;


public class AutoDebug2 extends AutoDecca{

	public AutoDebug2(String projectDir) {
		super(projectDir);
	}

	public String getCommand() {
		return "mvn -Dmaven.test.skip=true package neu.lab:decca:1.0:debug2 -e";
	}



}
