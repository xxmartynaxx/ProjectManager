import java.util.Scanner;

public class Managing {

    public static void manageProject(Scanner scanner, Project project) {

        // flaga powrotu
        boolean goBack = false;

        while (!goBack) {
            System.out.println("\nSelect one of the sections below:");
            System.out.println("1. Open the Task Manager's view.");
            System.out.println("2. Open the Team's view.");
            System.out.println("3. Open the Project's view.");
            System.out.println("4. Open the Team Member's view.");
            System.out.println("5. Open the Project Manager's view.");
            System.out.println("6. Open the Schedule's view.");
            System.out.println("7. Go back.");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> Views.taskManagerView(scanner, project);
                case 2 -> Views.teamView(scanner, project);
                case 3 -> Views.projectView(scanner, project);
                case 4 -> Views.teamMemberView(scanner, project);
                case 5 -> Views.projectManagerView(scanner, project);
                case 6 -> Views.scheduleView(scanner, project);
                case 7 -> {
                    // przed wyjściem do menu głównego
                    // trzeba zapisać wszelkie zmiany, jakie zaszły podczas działania programu
                    CompanyDB.saveMembersToFile(project.getTeam().getMembers());
                    CompanyDB.saveTasksToFile(project.getTaskManager().getTasks());
                    CompanyDB.saveTasksToFile(project.getTaskManager().getCompletedTasks());
                    CompanyDB.saveScheduleToFile(project.getSchedule().getMeetings());
                    TaskManager.tasksCounter = 1;
                    goBack = true;
                }
                default -> System.out.println("\nInvalid choice. Please choose between 1 and 7.\n");
            }
        }
    }
    
}