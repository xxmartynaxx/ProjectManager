import java.util.ArrayList;

class ProjectManager extends Worker {

    // kierownik projektów posiada listę projektów, którymi zarządza
    public ArrayList<Project> projects = new ArrayList<Project>();

    // konstruktor klasy
    public ProjectManager(int index, String firstName, String lastName, String email) {
        super(index, firstName, lastName, email, false);

        Main.companyProjectManagers.add(this);
    }

    // wyświetlanie listy projektów
    public void showProjects() {
        for (Project project : projects) {
            System.out.println("--> " + project.name);
        }
    }
}