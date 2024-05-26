import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CompanyDB {

    public static void loadData() {
        Worker workerJane = new Worker(1, "Jane", "Sunny", "jane.sunny@company.com", true);
        Worker workerAlex = new Worker(2, "Alex", "Stark", "alex.stark@company.com", true);
        Worker workerMark = new Worker(3, "Mark", "Smith", "mark.smith@company.com", true);

        ProjectManager managerJudy = new ProjectManager(1, "Judy", "Torrens", "judy.torrens@company.com");

        loadProjects();
    }

    public static void clearFiles(Project project) {
        String projectIndex = Integer.toString(project.getIndex());
        clearFile("DB_Tasks.txt", projectIndex);
        clearFile("DB_Members.txt", projectIndex);
        clearFile("DB_Meetings.txt", projectIndex);
    }

    private static void clearFile(String fileName, String projectIndex) {
        File file = new File(fileName);
        File tempFile = new File("Temporary.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(projectIndex)) {
                    writer.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
        }

        file.delete();
        tempFile.renameTo(file);
    }

    public static void loadProjects() {

        Main.companyProjects = new ArrayList<Project>();

        try (BufferedReader reader = new BufferedReader(new FileReader("DB_Projects.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Project project = Project.loadFromFile(line);
                Main.companyProjects.add(project);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Projects file not found.");

        } catch (IOException e) {
            System.out.println("An error occured while loading the projects file.");
        }
    }

    public static void loadTasks(Project project) {

        project.taskManager.tasks = new ArrayList<Task>();
        project.taskManager.completedTasks = new ArrayList<Task>();
        File file = new File("DB_Tasks.txt");
        File tempFile = new File("Temporary.txt");

        if (!file.exists()) {
            System.out.println("File does not exist yet, skipping tasks loading.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (!line.startsWith(Integer.toString(project.getIndex()))) {
                    writer.println(line);
                }

                else {
                    Task task = Task.loadFromFile(line);
                    if (task.isCompleted) {
                        project.taskManager.completedTasks.add(task);
                    } else {
                        project.taskManager.tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the tasks file.");
        }

        file.delete();
        tempFile.renameTo(file);

    }

    public static void loadMembers(Project project) {

        project.team.members = new ArrayList<TeamMember>();
        File file = new File("DB_Members.txt");
        File tempFile = new File("Temporary.txt");

        if (!file.exists()) {
            System.out.println("File does not exist yet, skipping members loading.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (!line.startsWith(Integer.toString(project.getIndex()))) {
                    writer.println(line);
                }

                else {
                    TeamMember teamMember = TeamMember.loadFromFile(line);
                    project.team.members.add(teamMember);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the members file.");
        }

        file.delete();
        tempFile.renameTo(file);

    }

    public static void loadSchedule(Project project) {

        project.schedule.meetings = new ArrayList<Meeting>();
        File file = new File("DB_Meetings.txt");
        File tempFile = new File("Temporary.txt");

        if (!file.exists()) {
            System.out.println("File does not exist yet, skipping schedule loading.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (!line.startsWith(Integer.toString(project.getIndex()))) {
                    writer.println(line);
                }

                else {
                    Meeting meeting = Meeting.loadFromFile(line);
                    project.schedule.meetings.add(meeting);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the meetings file.");
        }

        file.delete();
        tempFile.renameTo(file);

    }

    public static void saveProjectsToFile() {

        File file = new File("DB_Projects.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {

            for (Project project : Main.companyProjects) {
                String line = project.parseToString();
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Projects file.");
        }

    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {

        File file = new File("DB_Tasks.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            for (Task task : tasks) {
                String line = task.parseToString();
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Tasks file.");
        }

    }

    public static void saveMembersToFile(ArrayList<TeamMember> members) {

        File file = new File("DB_Members.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            for (TeamMember teamMember : members) {
                String line = teamMember.parseToString();
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Members file.");
        }

    }

    public static void saveScheduleToFile(ArrayList<Meeting> meetings) {

        File file = new File("DB_Meetings.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            for (Meeting meeting : meetings) {
                String line = meeting.parseToString();
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Meetings file.");
        }

    }

}
