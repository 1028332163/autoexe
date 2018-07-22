package neu.lab.autoexe.util;

public class DebugUtil {
	
	/**
	 * @param pomPath D:\ws\gitHub_new\accumulo-rel-1.7.3\fate (\pom.xml)
	 * @return list.add("D:\\ws\\gitHub_new\\accumulo-rel-1.7.3\\fate");
	 */
	public static String getAddPomCode(String pomPath) {
		String pomCode = "list.add(\""
				+pomPath.replace("\\pom.xml", "").replace("\\", "\\\\")
				+ "\");";
		return pomCode;
	}
}
