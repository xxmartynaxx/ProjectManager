import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Project {

    // atrybuty klasy Project
    private int index;
    public String name;
    public String description;
    public LocalDate deadline;
    public ProjectManager projectManager;
    public Team team;
    public TaskManager taskManager;
    public Schedule schedule;
    public String sponsor;
    public double budget;

    // konstruktor klasy Project
    public Project(String name, String description, LocalDate deadline, ProjectManager projectManager, String sponsor,
            double budget) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.sponsor = sponsor;
        this.budget = budget;

        this.projectManager = projectManager;
        projectManager.projects.add(this);

        this.index = Main.projectsCounter;

        this.taskManager = new TaskManager();
        taskManager.project = this;

        this.team = new Team();
        team.nickname = String.format("ProjectNo__%d__", index);

        this.schedule = new Schedule();
    }

    public int getIndex() {
        return this.index;
    }

    // załadowanie projektu z pliku
    public static Project loadFromFile(String line) {
        // index, name, descr, deadlin,e PMIndex, sponsor, budget
        String[] informations = line.split(";");

        int index = Integer.parseInt(informations[0]);
        String name = informations[1];
        String description = informations[2];
        LocalDate deadline = LocalDate.parse(informations[3], DateTimeFormatter.ISO_LOCAL_DATE);
        int projectManagerIndex = Integer.parseInt(informations[4]);
        ProjectManager projectManager = Main.getPMByIndex(projectManagerIndex);
        String sponsor = informations[5];
        double budget = Double.parseDouble(informations[6]);

        Project project = new Project(name, description, deadline, projectManager, sponsor, budget);
        project.index = index;
        return project;
    }

    // wyświetlenie podstawowych informacji o projekcie
    public void displayInfo() {
        System.out.println("TITLE: " + this.name);
        System.out.println("DESCRIPTION: ");
        System.out.println("-- " + this.description);
        System.out.println("DEADLINE: " + this.deadline);
        System.out.println("SPONSOR: " + this.sponsor);
        System.out.println("BUDGET: " + this.budget);
    }

    // zmiana budżetu przeznaczonego na projekt
    public void updateBudget(Scanner scanner) {
        System.out.println("\nEnter a new budget for the project.");
        double newBudget = scanner.nextDouble();

        this.budget = newBudget;

        System.out.println("\nThe budget has been changed successfully.");

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
            String mU = String.format("\nThe amount of money that has been used so far: %.2f", moneyUsed);
            String mN = String.format("The amount of money needed to complete remaining tasks: %.2f", moneyNeeded);

            System.out.println(mU);
            System.out.println(mN);
            System.out.println(String.format("Remaining budget: %.2f", this.budget));
        }

        else {
            double exc = moneyUsed - this.budget;
            System.out.println(String.format("The budget was exceeded by %.2f amount of money!", exc));
        }

    }

    // wyświetlenie czasu, jaki pozostał na wykonanie projektu
    public void timeLeft() {

        Period period = (LocalDate.now()).until(this.deadline);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        System.out.println("\nThe time remaining to complete the project");
        System.out.println("Years: " + years + ", months: " + months + ", days: " + days);

    }

    public String parseToString() {

        String pIndex = Integer.toString(index);
        String PMIndex = Integer.toString(projectManager.getIndex());
        String d = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String b = Double.toString(budget);

        String line = pIndex + ";" + name + ";" + description + ";" + d + ";"
                + PMIndex + ";" + sponsor + ";" + b;
        return line;
    }
}