import java.util.ArrayList;

class ProjectManager extends Worker {

    // lista projektów, którymi aktualnie zarządza
    private ArrayList<Project> projects = new ArrayList<Project>();

    // konstruktor klasy
    public ProjectManager(int index, String firstName, String lastName, String email) {
        super(index, firstName, lastName, email, false);

        Main.companyProjectManagers.add(this);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void showProjects() {

        if (projects.size() == 0) {
            System.out.println("-- The list of projects is empty. Consider creating a project first.");
            return;
        }

        for (Project project : projects) {
            System.out.println("--> " + project.getName());
        }
    }
    
}