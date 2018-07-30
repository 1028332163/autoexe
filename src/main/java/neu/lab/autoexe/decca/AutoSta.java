package neu.lab.autoexe.decca;


public class AutoSta extends AutoDecca {
	public AutoSta(String projectDir) {
		super(projectDir);
	}

	public String getCommand() {
		return "mvn neu.lab:decca:1.0:sta -e";
	}

}
