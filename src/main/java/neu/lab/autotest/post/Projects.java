package neu.lab.autotest.post;

import java.util.HashMap;
import java.util.Map;

public class Projects {
	private static Projects instance;

	private Projects() {
		name2obj = new HashMap<String, Project>();
	}

	public static Projects i() {
		if (instance == null) {
			instance = new Projects();
		}
		return instance;
	}

	private Map<String, Project> name2obj;
	
	public Project getProject(String projectPomPath) {
		return name2obj.get(projectPomPath);
	}

	public void addProject(Project project) {
		name2obj.put(project.getPomPath(), project);
	}
	
	public Project getNotNullProject(String projectPomPath) {
		Project project = name2obj.get(projectPomPath);
		if (project == null) {
			project = new Project(projectPomPath);
			name2obj.put(projectPomPath, project);
		}
		return project;
	}
	
	public String getNeededExpClses() {
		StringBuilder sb = new StringBuilder("");
		for (String projectName : name2obj.keySet()) {
			String proNeeded = name2obj.get(projectName).getNeededExpClses();
			if(!"".equals(proNeeded)) {
				sb.append("project:"+projectName+System.lineSeparator());
			}
		}
		return sb.toString();
	}
}
