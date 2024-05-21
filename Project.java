import java.time.LocalDate;
import java.time.Period;

public class Project {

    // atrybuty klasy Project
    public String name;
    public String description;
    public LocalDate deadline;
    public ProjectManager projectManager;
    public Team team;
    public TaskManager taskManager;
    public String sponsor;
    public double budget;

    // konstruktor klasy Project
    public Project(String name, String description, LocalDate deadline, String sponsor, double budget) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.sponsor = sponsor;
        this.budget = budget;
        this.taskManager = new TaskManager();
        taskManager.project = this;
        this.team = new Team();
    }

    // METODY KLASY

    // ustalenie kierownika projektu
    public void setProjectManager(ProjectManager manager) {
        this.projectManager = manager;
    }

    // wyświetlenie podstawowych informacji o projekcie
    public void displayInfo() {
        System.out.println("TITLE: " + this.name);
        System.out.println("DESCRIPTION: ");
        System.out.println("-- " + this.description);
        System.out.println("DEADLINE: " + this.deadline);
        System.out.println("SPONSOR: " + this.sponsor);
        System.out.println("BUDGET: " + this.budget);
        System.out.println("TEAM: ");
        (this.team).displayInfo();
    }

    // zmiana budżetu przeznaczonego na projekt
    public void updateBudget(double newBudget) {
        this.budget = newBudget;
    }

    // monitorowanie budżetu
    public void checkBudget() {

        double moneyUsed = 0;
        double moneyNeeded = 0;

        for (Task completedTask : (this.taskManager).completedTasks) {
            moneyUsed += completedTask.estimatedCost;
        }

        for (Task task : (this.taskManager).tasks) {
            moneyNeeded += task.estimatedCost;
        }

        if (moneyUsed <= this.budget) {
            String mU = String.format("The amount of money that has been used so far: %.2f", moneyUsed);
            String mN = String.format("The amount of money needed to complete remaining tasks: %.2f", moneyNeeded);

            System.out.println(mU);
            System.out.println(mN);
            System.out.println(String.format("Remaining budget: %.2f", this.budget));
        }

        else {
            double exc = moneyUsed - this.budget;
            System.out.println(String.format("The budget was exceeded by %.2f amount of money!", exc));
        }

        System.out.println("\n");

    }

    // wyświetlenie czasu, jaki pozostał na wykonanie projektu
    public void timeLeft() {

        Period period = (LocalDate.now()).until(this.deadline);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        System.out.println("The time remaining to complete the project");
        System.out.println("Years: " + years + ", months: " + months + ", days: " + days + "\n");

    }

}