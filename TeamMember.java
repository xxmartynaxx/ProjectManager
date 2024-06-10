import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class TeamMember extends Worker {

    // atrybuty klasy
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

    public void showTasks(Project project) {

        System.out.println(this.getFirstName() + "'s tasks: ");
        for (Task task : project.getTaskManager().getTasks()) {
            if (task.getMemberIndex() == this.getIndex()) {
                System.out.println("--> " + task.getName());
                System.out.println(task.getDescription());
            }
        }
    }

    public void addToCommonFile(Scanner scanner, Project project) {

        scanner.nextLine();
        System.out.println(
                "\nEnter the information you want to add to the common file.\nNote: Everyone in the team will be able to see this information.");
        String idea = scanner.nextLine();

        // gwarancja unikalności pliku tekstowego
        String fileName = project.getTeam().getNickname() + "_commonFile.txt";
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(idea + "\n");
            writer.write("-- ADDED BY: " + this.getFirstName() + " " + this.getLastName() + " -- " + LocalDate.now()
                    + " --\n\n");
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
        // postać informacji pobieranych z pliku:
        // projectIndex, index, permissionStatus
        String[] informations = line.split(";");

        int pIndex = Integer.parseInt(informations[0]);
        int wIndex = Integer.parseInt(informations[1]);
        int pS = Integer.parseInt(informations[2]);

        Worker w = Main.getWorkerByIndex(wIndex);

        TeamMember teamMember = new TeamMember(wIndex, pIndex, w.getFirstName(), w.getLastName(), w.getEmail(), pS);
        return teamMember;
    }

}