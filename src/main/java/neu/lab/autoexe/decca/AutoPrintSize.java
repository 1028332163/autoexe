package neu.lab.autoexe.decca;

public class AutoPrintSize extends AutoDecca{

	public AutoPrintSize(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn -Dmaven.test.skip=true neu.lab:decca:1.0:printCp -e";
	}

}
