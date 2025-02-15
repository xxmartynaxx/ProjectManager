import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // bazowe informacje dotyczące firmy
    public static int projectsCounter = 1;
    public static ArrayList<Worker> companyWorkers = new ArrayList<Worker>();
    public static ArrayList<ProjectManager> companyProjectManagers = new ArrayList<ProjectManager>();
    public static ArrayList<Project> companyProjects = new ArrayList<Project>();

    public static void main(String[] args) {

        CompanyDB.loadData();

        System.out.println("\n--- Projects Managing Program ---\n");

        System.out.println("Company's Workers:");
        for (Worker w : companyWorkers) {
            w.displayInfo();
        }

        System.out.println("\nCompany's Project Managers:");
        for (ProjectManager pm : companyProjectManagers) {
            pm.displayInfo();
        }

        System.out.println("\nCompany's Projects:");
        if (companyProjects.size() == 0) {
            System.out.println("No projects have been added to the list yet.");
        } else {
            for (Project p : companyProjects) {
                System.out.println(p.getIndex() + "  " + p.getName());
            }
        }

        // flaga wyjścia
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nSelect one of the actions below:");
            System.out.println("1. Add a new project to the system");
            System.out.println("2. Remove the project from the system");
            System.out.println("3. Manage one of the projects from the system");
            System.out.println("4. Exit");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> addProject(scanner);
                case 2 -> removeProject(scanner);
                case 3 -> manageProject(scanner);
                case 4 -> {
                    // przed wyjściem zapisujemy ogólne zmiany, jakie zaszły w projektach
                    CompanyDB.saveProjectsToFile();
                    exit = true;
                    System.out.println("\nExiting program.");
                }
                default -> System.out.println("\nInvalid choice. Please choose between 1 and 4.\n");
            }
        }

        scanner.close();
    }

    // zwrócenie konkretnego pracownika (po jego unikalnym indeksie)
    public static Worker getWorkerByIndex(int index) {

        for (Worker worker : companyWorkers) {
            if (worker.getIndex() == index) {
                return worker;
            }
        }
        return null;
    }

    // zwrócenie konkretnego kierownika projektu (po jego unikalnym indeksie)
    public static ProjectManager getPMByIndex(int index) {

        for (ProjectManager projectManager : companyProjectManagers) {
            if (projectManager.getIndex() == index) {
                return projectManager;
            }
        }
        return null;
    }

    // zwrócenie konkretnego projektu (po jego unikalnym indeksie)
    public static Project getProjectByIndex(int index) {

        for (Project project : companyProjects) {
            if (project.getIndex() == index) {
                return project;
            }
        }
        return null;
    }

    // dodanie nowego projektu do systemu
    public static void addProject(Scanner scanner) {

        System.out.println("\nTo add a project, complete the form below.");

        System.out.println("-- Enter the name of the project: ");
        String name = scanner.nextLine();

        System.out.println("-- Enter the project's description: ");
        String description = scanner.nextLine();

        System.out.println("-- Enter the project's deadline (YYYY-MM-DD): ");
        String userDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (!date.isAfter(LocalDate.now())) {
            System.out.println("\nThe deadline cannot refer to a date that has already passed.");
            return;
        }

        System.out.println("-- Enter the index of the PM who should manage the project: ");
        int indexPM = scanner.nextInt();
        scanner.nextLine();

        if (getPMByIndex(indexPM) == null) {
            System.out.println("\nInvalid index. There is no Project Manager with this index.");
            return;
        }

        System.out.println("-- Enter the project's sponsor: ");
        String sponsor = scanner.nextLine();

        System.out.println("-- Enter the budget for the project: ");
        double budget = scanner.nextDouble();
        scanner.nextLine();

        Project newProject = new Project(name, description, date, getPMByIndex(indexPM), sponsor, budget);

        companyProjects.add(newProject);
        // pilnujemy, by indeksy nadawane projektom były unikalne
        projectsCounter++;

        System.out.println("\nThe project has been added successfully.");
    }

    // usunięcie projektu z systemu
    public static void removeProject(Scanner scanner) {

        if (companyProjects.isEmpty()) {
            System.out.println("\nNo projects to remove from the system.");
            return;
        }

        System.out.println("\nEnter the index of the project you want to remove.");
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        for (Project project : companyProjects) {
            if (project.getIndex() == userChoice) {
                companyProjects.remove(project);
                CompanyDB.clearFiles(project);
                break;
            }
        }

        System.out.println("\nThe project has been removed successfully.");
    }

    // zarządzanie istniejącym projektem
    public static void manageProject(Scanner scanner) {

        if (companyProjects.isEmpty()) {
            System.out.println("\nNo projects in the system to manage.");
            return;
        }

        System.out.println("\nSelect the project to manage.");
        for (Project p : companyProjects) {
            System.out.println("--> " + p.getIndex() + " " + p.getName());
        }

        int userChoice = scanner.nextInt();
        Project projectToManage = getProjectByIndex(userChoice);
        if (projectToManage == null) {
            System.out.println("\nInvalid index. There is no project in the system with such index");
        }

        else {
            // użytkownik wskazał projekt, którym chce zarządzać
            // system pobiera wszystkie dane dotyczące tego projektu
            CompanyDB.loadMembers(projectToManage);
            CompanyDB.loadSchedule(projectToManage);
            CompanyDB.loadTasks(projectToManage);
            Managing.manageProject(scanner, projectToManage);
        }
    }

}