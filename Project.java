import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Project {

    // atrybuty klasy
    private int index;
    private String name;
    private String description;
    private LocalDate deadline;
    private ProjectManager projectManager;
    private Team team;
    private TaskManager taskManager;
    private Schedule schedule;
    private String sponsor;
    private double budget;

    // konstruktor klasy
    public Project(String name, String description, LocalDate deadline, ProjectManager projectManager, String sponsor,
            double budget) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.sponsor = sponsor;
        this.budget = budget;

        this.projectManager = projectManager;
        projectManager.addProject(this);

        // zapewnienie unikalnego indeksu
        this.index = Main.projectsCounter;

        this.taskManager = new TaskManager();

        this.team = new Team();
        team.setNickname(String.format("ProjectNo__%d__", index));

        this.schedule = new Schedule();
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public ProjectManager getProjectManager() {
        return this.projectManager;
    }

    public Team getTeam() {
        return this.team;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return this.budget;
    }

    public void displayInfo() {
        System.out.println("TITLE: " + this.name);
        System.out.println("DESCRIPTION: ");
        System.out.println("-- " + this.description);
        System.out.println("DEADLINE: " + this.deadline);
        System.out.println("SPONSOR: " + this.sponsor);
        System.out.println("BUDGET: " + this.budget);
    }

    public void updateBudget(Scanner scanner) {
        System.out.println("\nEnter a new budget for the project.");
        double newBudget = scanner.nextDouble();

        this.budget = newBudget;

        System.out.println("\nThe budget has been changed successfully.");
    }

    public void checkBudget() {

        double moneyUsed = 0;
        double moneyNeeded = 0;

        for (Task completedTask : (this.taskManager).getCompletedTasks()) {
            moneyUsed += completedTask.getEstimatedCost();
        }

        for (Task task : (this.taskManager).getTasks()) {
            moneyNeeded += task.getEstimatedCost();
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

    public static Project loadFromFile(String line) {
        // postać informacji pobieranych z pliku:
        // index, name, descr, deadline, PMIndex, sponsor, budget
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

}