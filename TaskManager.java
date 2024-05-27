import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    // informacja w ramach jakiego projektu,
    // przechowuje listę zadań do wykonania oraz
    // listę skończonych zadań
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private ArrayList<Task> completedTasks = new ArrayList<Task>();
    private static int tasksCounter = 1;

    // wyświetlenie zadań
    public void showTasks() {

        if (tasks.size() == 0) {
            System.out.println("\nThe list of tasks is empty. Consider adding the task first.");
            return;
        }
        for (Task task : tasks) {
            task.displayinfo();
            System.out.println("\n");
        }
    }

    public void initializeTasksArray() {
        this.tasks = new ArrayList<Task>();
        this.completedTasks = new ArrayList<Task>();
    }

    public void addToTasks(Task task) {
        this.tasks.add(task);
    }

    public void addToCompletedTasks(Task task) {
        this.completedTasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public ArrayList<Task> getCompletedTasks() {
        return this.completedTasks;
    }

    // wykonanie zadania przez któregoś z członków zespołu
    // przeniesienie wykonanego zadania do listy zadań ukończonych
    // aktualizacja budżetu projektu
    public void setAsCompleted(Scanner scanner, Project project) {

        if (tasks.size() == 0) {
            System.out.println("\nThe list of tasks is empty. Consider adding the task first.");
            return;
        }

        System.out.println("\nEnter the index of the task you want to set as completed.");
        int taskIndex = scanner.nextInt();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                task.updateStatus();
                ;
                completedTasks.add(task);
                tasks.remove(task);
                project.setBudget(project.getBudget() - task.getEstimatedCost());
                break;
            }
        }

        System.out.println("\nTask's status has been changed successfully.");
    }

    // przepisanie zadania na innego członka zespołu
    public void reassignTask(Scanner scanner, Project project) {

        if (tasks.size() == 0) {
            System.out.println("\nThe list of tasks is empty. Consider adding the task first.");
            return;
        }

        System.out.println(
                "\nEnter the index of the task you want to assign to another person, the index of the team member you want to take the task from, and the index of the team member you want to assign the task to.");
        int taskIndex = scanner.nextInt();
        int removeFrom = scanner.nextInt();
        int moveTo = scanner.nextInt();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                // sprawdzenie czy są w tym samym zespole
                if (project.getTeam().getMemberByIndex(removeFrom) != null
                        && project.getTeam().getMemberByIndex(moveTo) != null) {
                    if (project.getTeam().getMemberByIndex(moveTo).getPermissionStatus() >= task
                            .getRequiredPermissionStatus()) {
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

        if (tasks.size() == 0) {
            System.out.println("\nThe list of tasks is empty. Consider adding the task first.");
            return;
        }

        System.out.println(
                "\nEnter the index of the task for which you want to change the due date and the new due date.");
        int taskIndex = scanner.nextInt();
        String deadline = scanner.nextLine();
        LocalDate newDeadline = LocalDate.parse(deadline);

        if (!newDeadline.isAfter(LocalDate.now())) {
            System.out.println("\nThe deadline cannot refer to a date that has already passed.");
            return;
        }

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                task.setNewDeadline(newDeadline);
                break;
            }
        }

        System.out.println("\nTask's deadline has been changed successfully.");
    }

    // aktualizowanie szacowanego kosztu wykonania zadania
    public void updateTaskEstimatedCost(Scanner scanner, Project project) {

        if (tasks.size() == 0) {
            System.out.println("\nThe list of tasks is empty. Consider adding the task first.");
            return;
        }

        System.out.println(
                "\nEnter the index of the task for which you want to change the estimated cost and the new estimated cost.");
        int taskIndex = scanner.nextInt();
        double newEstimatedCost = scanner.nextDouble();

        for (Task task : tasks) {
            if (task.getIndex() == taskIndex) {
                task.setNewEstimatedCost(newEstimatedCost);
                break;
            }
        }

        System.out.println("\nTask's estimated cost has been changed successfully.");
    }

    // dodanie nowego zadania do listy
    public void addTask(Scanner scanner, Project project) {

        System.out.println("\nTo add a task, complete the form below.");
        scanner.nextLine();

        System.out.println("-- Enter the name of the task: ");
        String name = scanner.nextLine();

        System.out.println("-- Enter the task's description: ");
        String description = scanner.nextLine();

        System.out.println("-- Enter the task's required permission status: ");
        int requiredPS = scanner.nextInt();
        scanner.nextLine();

        System.out.println("-- Enter the task's deadline (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        LocalDate deadline = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (!deadline.isAfter(LocalDate.now())) {
            System.out.println("\nThe deadline cannot refer to a date that has already passed.");
            return;
        }

        System.out.println("-- Enter the task's estimated cost: ");
        double estimatedCost = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("-- Enter the index of the Worker who should work on this task: ");
        int TMIndex = scanner.nextInt();
        scanner.nextLine();

        if (project.getTeam().getMemberByIndex(TMIndex) == null) {
            System.out.println("This Worker is not a part of this team.");
            return;
        }

        Task newTask = new Task(TMIndex, name, description, requiredPS, deadline, estimatedCost);
        newTask.setIndex(tasksCounter);
        newTask.setProjectIndex(project.getIndex());

        tasks.add(newTask);
        tasksCounter++;

        System.out.println("\nThe task has been added successfully.");
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