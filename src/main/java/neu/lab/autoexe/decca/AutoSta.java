package neu.lab.autoexe.decca;


public class AutoSta extends AutoDecca {
	public AutoSta(String projectDir) {
		super(projectDir);
	}

	public String getBatPath() {
		return "D:\\ws\\sta.bat";
	}

	public String getCommand() {
		return "mvn neu.lab:decca:1.0:sta -e";
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_sta\\";
	}

}
