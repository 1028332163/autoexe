package neu.lab.autoexe.decca;


public class AutoDebug extends AutoDecca {
	public AutoDebug(String projectDir) {
		super(projectDir);
	}

	public String getCommand() {
		return "mvn -Dmaven.test.skip=true package neu.lab:decca:1.0:debug -e";
	}


}
