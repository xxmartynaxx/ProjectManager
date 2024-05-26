import java.util.Scanner;

public class Views {

    public static void taskManagerView(Scanner scanner, Project project) {

        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nSelect one of the actions below:");
            System.out.println("1. Show the list of remaining tasks.");
            System.out.println("2. Set the task as completed.");
            System.out.println("3. Assign the task to another team member.");
            System.out.println("4. Change the task's deadline.");
            System.out.println("5. Update the task's estimated cost.");
            System.out.println("6. Add a new task to the list.");
            System.out.println("7. Generate a report.");
            System.out.println("8. Go back.");

            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> project.taskManager.showTasks();
                case 2 -> project.taskManager.setAsCompleted(scanner, project);
                case 3 -> project.taskManager.reassignTask(scanner, project);
                case 4 -> project.taskManager.changeTaskDeadline(scanner, project);
                case 5 -> project.taskManager.updateTaskEstimatedCost(scanner, project);
                case 6 -> project.taskManager.addTask(scanner, project);
                case 7 -> project.taskManager.generateReport();
                case 8 -> goBack = true;
                default -> System.out.println("\nInvalid choice. Please choose between 1 and 8.\n");
            }
        }
    }

    public static void teamView(Scanner scanner, Project project) {

        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nSelect one of the actions below:");
            System.out.println("1. Show some information about the team.");
            System.out.println("2. Add a new member to the team.");
            System.out.println("3. Remove a member from the team.");
            System.out.println("4. Go back.");

            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> project.team.displayInfo();
                case 2 -> project.team.addMember(scanner, project);
                case 3 -> project.team.removeMember(scanner);
                case 4 -> goBack = true;
                default -> System.out.println("\nInvalid choice. Please choose between 1 and 4.\n");
            }
        }
    }

    public static void projectView(Scanner scanner, Project project) {

        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nSelect one of the actions below:");
            System.out.println("1. Show some information about the project.");
            System.out.println("2. Update the project's budget.");
            System.out.println("3. Show some information about changes that occurred in the budget during the project realization.");
            System.out.println("4. Show the time left for project realization.");
            System.out.println("5. Go back.");

            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> project.displayInfo();
                case 2 -> project.updateBudget(scanner);
                case 3 -> project.checkBudget();
                case 4 -> project.timeLeft();
                case 5 -> goBack = true;
                default -> System.out.println("\nInvalid choice. Please choose between 1 and 5.\n");
            }
        }
    }

    public static void teamMemberView(Scanner scanner, Project project) {
        System.out.println("\nEnter your index for verification.");
        int userIndex = scanner.nextInt();
        TeamMember teamMember = project.team.getMemberByIndex(userIndex);

        if (teamMember != null) {

            boolean goBack = false;
            while (!goBack) {
                System.out.println("\nSelect one of the actions below:");
                System.out.println("1. Show the list of the tasks.");
                System.out.println("2. Add to Common File.");
                System.out.println("3. Go back.");

                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> teamMember.showTasks(project);
                    case 2 -> teamMember.addToCommonFile(scanner, project);
                    case 3 -> goBack = true;
                    default -> System.out.println("\nInvalid choice. Please choose between 1 and 3.\n");
                }
            }
        }

        else {
            System.out.println("\nInvalid index. There is no Worker with this index working on this project.");
        }
    }

    public static void projectManagerView(Scanner scanner, Project project) {
        System.out.println("\nEnter your index for verification.");
        int userIndex = scanner.nextInt();

        if (userIndex == project.projectManager.getIndex()) {

            boolean goBack = false;
            while (!goBack) {
                System.out.println("\nSelect one of the actions below:");
                System.out.println("1. Show the list of the projects.");
                System.out.println("2. Go back.");

                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> project.projectManager.showProjects();
                    case 2 -> goBack = true;
                    default -> System.out.println("\nInvalid choice. Please choose between 1 and 2.\n");
                }
            }
        }

        else {
            System.out.println("\nInvalid index. Enter the index of the Project Manager who manages this project.");
        }
    }

    public static void scheduleView(Scanner scanner, Project project) {

        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nSelect one of the actions below:");
            System.out.println("1. Show the list of the meetings.");
            System.out.println("2. Add a new meeting to the schedule.");
            System.out.println("3. Go back.");

            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> project.schedule.showMeetings();
                case 2 -> project.schedule.addMeeting(scanner, project);
                case 3 -> goBack = true;
                default -> System.out.println("\nInvalid choice. Please choose between 1 and 3.\n");
            }
        }
    }

}
