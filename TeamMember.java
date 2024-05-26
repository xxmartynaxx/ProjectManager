import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class TeamMember extends Worker {

    // członek zespołu ma określony stopień uprawnień, przypisany zespół
    // oraz własną listę zadań do wykonania
    private int projectIndex;
    private int permissionStatus;

    // konstruktor klasy
    public TeamMember(int index, int projectIndex, String firstName, String lastName, String email,
            int permissionStatus) {
        super(index, firstName, lastName, email, false);
        this.projectIndex = projectIndex;
        this.permissionStatus = permissionStatus;
    }

    public int getPermissionStatus() {
        return this.permissionStatus;
    }

    // wyświetlanie listy zadań członka zespołu
    public void showTasks(Project project) {

        System.out.println(this.firstName + "'s tasks: ");
        for (Task task : project.taskManager.tasks) {
            if (task.getMemberIndex() == this.getIndex()) {
                System.out.println("--> " + task.name);
                System.out.println(task.description);
                System.out.println("\n");
            }
        }
    }

    // edycja wspólnego pliku
    public void addToCommonFile(Scanner scanner, Project project) {

        System.out.println(
                "\nEnter the information you want to add to the common file.\nNote: Everyone in the team will be able to see this information.");
        String idea = scanner.nextLine();

        String fileName = project.team.nickname + "_commonFile.txt";
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(idea + "\n");
            writer.write("-- ADDED BY: " + this.firstName + " " + this.lastName + " -- " + LocalDate.now() + " --\n\n");
        } catch (IOException e) {
            System.out.println("\nAn error occurred while writing to the common file.");
        }
    }

    public String parseToString() {

        String wIndex = Integer.toString(this.getIndex());
        String pIndex = Integer.toString(projectIndex);
        String pS = Integer.toString(permissionStatus);

        String line = pIndex + ";" + wIndex + ";" + pS;
        return line;
    }

    public static TeamMember loadFromFile(String line) {
        // projectIndex, index, permissionStatus
        String[] informations = line.split(";");

        int pIndex = Integer.parseInt(informations[0]);
        int wIndex = Integer.parseInt(informations[1]);
        int pS = Integer.parseInt(informations[2]);

        Worker w = Main.getWorkerByIndex(wIndex);

        TeamMember teamMember = new TeamMember(wIndex, pIndex, w.firstName, w.lastName, w.email, pS);

        return teamMember;
    }

}