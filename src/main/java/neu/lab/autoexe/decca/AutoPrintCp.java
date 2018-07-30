package neu.lab.autoexe.decca;

public class AutoPrintCp extends AutoDecca{

	public AutoPrintCp(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn -Dmaven.test.skip=true neu.lab:decca:1.0:printCp -e";
	}

}
