package neu.lab.autoexe.util;


public class DistanceFileUtil {
	// org.apache.commons.compress,org.apache.commons.compress.compressors,0.0,false
	public static String[] readClsDisLine(String line) {
		return line.split(",");
	}
}
