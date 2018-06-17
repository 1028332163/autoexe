package neu.lab.autoexe.evosuite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutoTestNeibor {
	private static Map<String, ProjectNeiborTester> pom2tester;
	private static Set<String> skipProjects;

	static {
		skipProjects = new HashSet<String>();
		skipProjects.add("D:\\ws\\gitHub_old\\carbondata-apache-carbondata-1.1.1\\integration\\spark2\\pom.xml");
		skipProjects.add("D:\\ws\\gitHub_old\\crunch-cdh5.12.2-release\\crunch-hbase\\pom.xml");
		skipProjects.add(
				"D:\\ws\\gitHub_old\\camel-camel-2.19.4\\platforms\\spring-boot\\components-starter\\camel-spark-starter\\pom.xml");
		skipProjects.add(
				"D:\\ws\\gitHub_old\\eagle-eagle-0.3.0-incubating\\eagle-core\\eagle-embed\\eagle-embed-hbase\\pom.xml");
		skipProjects.add("D:\\ws\\gitHub_old\\incubator-atlas-release-0.7.1-rc2\\addons\\hive-bridge\\pom.xml");
		skipProjects.add(
				"D:\\ws\\gitHub_old\\cxf-cxf-3.1.14\\distribution\\src\\main\\release\\samples\\jax_rs\\spring_boot_scan\\eureka-registry\\pom.xml");
	}

	public static void main(String[] args) throws Exception {
		readNeiborFile();
		autoTest();
	}

	private static void autoTest() throws Exception {
		int projectNum = 0;
		for (String pom : pom2tester.keySet()) {
			if (!skipProjects.contains(pom)) {
				System.out.println("handle project:"+pom);
				pom2tester.get(pom).runTest(projectNum);
			}
			else 
				System.out.println("skip pom:"+pom);
			projectNum++;
		}
	}

	private static void readNeiborFile() throws Exception {
		pom2tester = new HashMap<String, ProjectNeiborTester>();
		BufferedReader reader = new BufferedReader(new FileReader("D:\\ws_testcase\\neibor.txt"));
		String line = reader.readLine();
		while (line != null) {
			if (!line.equals("")) {
				String[] pom_neibor_jar = line.split(",");
				getNotNullTester(pom_neibor_jar[0]).addNeibor(pom_neibor_jar[1], pom_neibor_jar[2]);
			}
			line = reader.readLine();
		}
		reader.close();
	}

	private static ProjectNeiborTester getNotNullTester(String pom) {

		ProjectNeiborTester tester = pom2tester.get(pom);
		if (tester == null) {
			tester = new ProjectNeiborTester(pom);
			pom2tester.put(pom, tester);
		}
		return tester;
	}

}
