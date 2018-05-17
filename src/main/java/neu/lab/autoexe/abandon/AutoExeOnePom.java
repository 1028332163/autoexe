package neu.lab.autoexe.abandon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import neu.lab.autoexe.AutoExe;

public class AutoExeOnePom extends AutoExe{

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_onepom\\";
	}

	@Override
	protected String getProjectDir() {
		return Conf.OnePomProjectDir;
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\onePom.bat";
	}
	
	protected void writeBat(String pomPath) throws IOException {
		if(!new File(Conf.OnePomProjectDir).exists()) {
			PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(getBatPath())));
			printer.println("cd " + Conf.OnePomProjectDir);
			printer.println(getCommand());
			printer.close();
		}
		
	}
	@Override
	public String getCommand() {
		return "mvn neu.lab:conflict:1.0:classDupRisk -e";
	}


}
