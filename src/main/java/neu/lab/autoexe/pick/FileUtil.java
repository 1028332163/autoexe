package neu.lab.autoexe.pick;

import java.io.File;

public class FileUtil {
	public static boolean moveFile(String srcFileName, String tgtDir) {  
	      
	    File srcFile = new File(srcFileName);  
	    if(!srcFile.exists() || !srcFile.isFile())   
	        return false;  
	      
	    File destDir = new File(tgtDir);  
	    if (!destDir.exists())  
	        destDir.mkdirs();  
	      
	    return srcFile.renameTo(new File(tgtDir + File.separator + srcFile.getName()));  
	}  
}
