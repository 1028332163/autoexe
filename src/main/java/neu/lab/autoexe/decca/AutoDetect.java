package neu.lab.autoexe.decca;


public class AutoDetect extends AutoDecca {
	public AutoDetect(String projectDir) {
		super(projectDir);
	}

	public String getCommand() {
		return "mvn package neu.lab:decca:1.0:detect -Dmaven.test.skip=true -e";
	}

}
