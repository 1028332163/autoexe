package neu.lab.autoexe.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PomFinder {
	/**path is directory instead of pomFile.
	 * @param father
	 * @return
	 */
	public static List<String> findPomDirs(File father) {
		File[] children = father.listFiles();
		List<String> pomPaths = new ArrayList<String>();
		for (File child : children) {
			if (child.getName().equals("pom.xml")) {
				pomPaths.add(father.getAbsolutePath());
			}
			if (child.isDirectory()) {
				pomPaths.addAll(findPomDirs(child));
			}
		}
		return pomPaths;
	}
	/** 
	 * @param root
	 * @return  <maven-coordinate,pom-path>
	 */
	public Map<String, String> getId2path(File root) {
		Map<String, String> id2path = new HashMap<String, String>();
		addFilePoms(root,id2path);
		return id2path;
	}

	private void addFilePoms(File father,Map<String, String> id2path) {
		File[] children = father.listFiles();
		for (File child : children) {
			if (child.getName().equals("pom.xml")) {
				try {
					PomReader pom = new PomReader(child.getPath());
					if(id2path.get(pom.getCoordinate())==null) {
						id2path.put(pom.getCoordinate(), child.getPath());
					}else{
//						System.out.println("duplicate path for:"+pom.getCoordinate()+" "+alreadyHas+" "+child.getPath());
					}
				} catch (Exception e) {
//					System.out.println("can't read pom " + child.getPath());
				}

			}
			if (child.isDirectory()) {
				addFilePoms(child,id2path);
			}
		}
	}
	

}
