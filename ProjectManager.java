import java.util.ArrayList;

class ProjectManager extends Worker {

    // kierownik projektów posiada listę projektów, którymi zarządza
    private ArrayList<Project> projects = new ArrayList<Project>();

    // konstruktor klasy
    public ProjectManager(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    // METODY KLASY

    // wyświetlanie listy projektów
    public void showProjects() {
        for (Project project : projects) {
            System.out.println("--> " + project.name);
        }
        System.out.println("\n");
    }

    // dodawanie kolejnego projektu do listy projektów
    public boolean addProject(Project project) {

        if (projects == null || projects.size() <= 3) {
            projects.add(project);
            return true;
        }

        System.out.println("The Project Manager may not manage more than 3 projects at the same time.");
        System.out.println("Consider removing a project from the list.\n");
        return false;

    }

    // usuwanie projektu z listy projektów
    public void removeProject(Project project) {
        projects.remove(project);
    }

    // przejrzenie raportu członka zespołu
    public void showReport(TeamMember teamMember) {
        System.out.println(teamMember.firstName + " " + teamMember.lastName + "'s report");
        System.out.println("--> " + teamMember.report + "\n");
    }

}