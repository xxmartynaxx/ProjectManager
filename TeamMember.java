import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class TeamMember extends Worker {

    // członek zespołu ma określony stopień uprawnień, przypisany zespół
    // oraz własną listę zadań do wykonania
    private int projectIndex;
    private int permissionStatus;
    // public Team memberOf;
    private ArrayList<Task> memberTasks = new ArrayList<Task>();

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

        // append - jeśli true to dopisujemy, nie nadpisujemy
        if (file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(idea + "\n");
                writer.write(
                        "-- ADDED BY: " + this.firstName + " " + this.lastName + " -- " + LocalDate.now() + " --\n\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the common file.");
            }

        }

        else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(idea + "\n");
                writer.write(
                        "-- ADDED BY: " + this.firstName + " " + this.lastName + " -- " + LocalDate.now() + " --\n\n");
            } catch (IOException e) {
                System.out.println("An error occurred while creating the common file.");
            }
        }
    }

}