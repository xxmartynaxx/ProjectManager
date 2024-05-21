import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {

    // przechowuje listę zadań do wykonania oraz
    // listę skończonych zadań (w ramach danego projektu)
    public Project project;
    public ArrayList<Task> tasks = new ArrayList<Task>();
    public ArrayList<Task> completedTasks = new ArrayList<Task>();

    // METODY KLASY

    // przypisanie zadania jednemu z członków zespołu
    public void assignTask(Task task, TeamMember member) {

        if (member.isAllowed(task)) {
            (member.memberTasks).add(task);
        }

        else {
            System.out.println("This person does not have sufficient authority to complete this task.\n");
        }

    }

    // wykonanie zadania przez któregoś z członków zespołu
    // przeniesienie wykonanego zadania do listy zadań ukończonych
    // aktualizacja budżetu projektu
    public void setAsCompleted(Task task, TeamMember member) {
        (member.memberTasks).remove(task);
        task.isCompleted = true;
        completedTasks.add(task);
        tasks.remove(task);
        project.budget -= task.estimatedCost;
    }

    // przepisanie zadania na innego członka zespołu
    public void moveTask(Task task, TeamMember removeFrom, TeamMember moveTo) {

        if (moveTo.isAllowed(task)) {
            (removeFrom.memberTasks).remove(task);
            (moveTo.memberTasks).add(task);
        }

        else {
            System.out.println("A person you want to assign task to does not have sufficient authority.\n");
        }

    }

    // zmiana terminu wykonania zadania
    public void changeTaskDeadline(Task task, LocalDate newDeadline) {
        task.deadline = newDeadline;
    }

    // dodanie nowego zadania do listy
    public void addTask(Task task) {
        tasks.add(task);
    }

    // usunięcie zadania z listy
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // wyświetlenie postępu projektu
    public void generateReport() {
        System.out.println("Project progress report");
        System.out.println("Completed tasks: " + completedTasks.size());
        System.out.println("Remaining tasks: " + tasks.size());
        double progress = (double) completedTasks.size() / (completedTasks.size() + tasks.size());
        System.out.println(String.format("Project progress: %.2f \n", progress));
    }

}