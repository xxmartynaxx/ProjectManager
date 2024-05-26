import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    // informacja w ramach jakiego projektu,
    // przechowuje listę zadań do wykonania oraz
    // listę skończonych zadań
    public Project project;
    public ArrayList<Task> tasks = new ArrayList<Task>();
    public ArrayList<Task> completedTasks = new ArrayList<Task>();
    private static int tasksCounter = 1;

    // wyświetlenie zadań
    public void showTasks() {
        for (Task task : tasks) {
            task.displayinfo();
            System.out.println("\n");
        }
    }

    // wykonanie zadania przez któregoś z członków zespołu
    // przeniesienie wykonanego zadania do listy zadań ukończonych
    // aktualizacja budżetu projektu
    public void setAsCompleted(Scanner scanner, Project project) {

        System.out.println("\nEnter the index of the task you want to set as completed.");
        int taskIndex = scanner.nextInt();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                task.isCompleted = true;
                completedTasks.add(task);
                tasks.remove(task);
                // potem przy już ostatecznym rozliczeniu i wpisywaniu do pliku to wszystkie
                // taski z completedTasks niech będą zapisane z memberIndex = 0
                project.budget -= task.estimatedCost;
                break;
            }
        }

        System.out.println("\nTask's status has been changed successfully.");
    }

    // przepisanie zadania na innego członka zespołu
    public void reassignTask(Scanner scanner, Project project) {

        System.out.println(
                "\nEnter the index of the task you want to assign to another person, the index of the team member you want to take the task from, and the index of the team member you want to assign the task to.");
        int taskIndex = scanner.nextInt();
        int removeFrom = scanner.nextInt();
        int moveTo = scanner.nextInt();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                // sprawdzenie czy są w tym samym zespole
                if (project.team.getMemberByIndex(removeFrom) != null
                        && project.team.getMemberByIndex(moveTo) != null) {
                    if (project.team.getMemberByIndex(moveTo).getPermissionStatus() >= task.requiredPermissionStatus) {
                        task.setMemberIndex(moveTo);
                    } else {
                        System.out.println(
                                "\nThe person you want to assign the task to does not have sufficient authority.");
                        return;
                    }
                } else {
                    System.out.println("\nBoth team members have to be part of the project's team.");
                    return;
                }
            }
        }

        System.out.println("\nThe task has been reassign to another team member successfully.");

    }

    // zmiana terminu wykonania zadania
    public void changeTaskDeadline(Scanner scanner, Project project) {

        System.out.println(
                "\nEnter the index of the task for which you want to change the due date and the new due date.");
        int taskIndex = scanner.nextInt();
        String deadline = scanner.nextLine();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                LocalDate newDeadline = LocalDate.parse(deadline);
                task.deadline = newDeadline;
                break;
            }
        }

        System.out.println("\nTask's deadline has been changed successfully.");
    }

    // aktualizowanie szacowanego kosztu wykonania zadania
    public void updateTaskEstimatedCost(Scanner scanner, Project project) {

        System.out.println(
                "\nEnter the index of the task for which you want to change the estimated cost and the new estimated cost.");
        int taskIndex = scanner.nextInt();
        double newEstimatedCost = scanner.nextDouble();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                task.estimatedCost = newEstimatedCost;
                break;
            }
        }

        System.out.println("\nTask's estimated cost has been changed successfully.");
    }

    // dodanie nowego zadania do listy
    public void addTask(Scanner scanner, Project project) {

        System.out.println("\nTo add a task, complete the form below.");

        System.out.println("-- Enter the name of the task: ");
        String name = scanner.nextLine();

        System.out.println("-- Enter the task's description: ");
        String description = scanner.nextLine();

        System.out.println("-- Enter the task's required permission status: ");
        String requiredPS = Integer.toString(scanner.nextInt());
        scanner.nextLine();

        System.out.println("-- Enter the task's deadline (YYYY-MM-DD): ");
        String deadline = scanner.nextLine();

        System.out.println("-- Enter the task's estimated cost: ");
        String estimatedCost = Double.toString(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("-- Enter the index of the Worker who should work on this task: ");
        String indexTM = Integer.toString(scanner.nextInt());
        scanner.nextLine();

        String info = Integer.toString(tasksCounter) + ";" + Integer.toString(project.getIndex()) + ";" + indexTM + ";"
                + name + ";" + description + ";" + requiredPS + ";" +
                deadline + ";" + estimatedCost;

        try (FileWriter writer = new FileWriter("Tasks.txt", true)) {
            writer.write(info + "\n");
            System.out.println("\nThe task has been added successfully.");
            tasksCounter++;

        } catch (IOException e) {
            System.out.println("\nAn error occurred while saving the task to the file: ");
        }

    }

    // wyświetlenie postępu projektu
    public void generateReport() {
        System.out.println("Project progress report");
        System.out.println("Completed tasks: " + completedTasks.size());
        System.out.println("Remaining tasks: " + tasks.size());
        double progress = (double) completedTasks.size() / (completedTasks.size() + tasks.size());
        System.out.println(String.format("Project progress: %.2f", progress));
    }

}