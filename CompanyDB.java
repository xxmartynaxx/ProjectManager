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

        try (BufferedReader reader = new BufferedReader(new FileReader("DB_Tasks.txt"));
                PrintWriter writer = new PrintWriter(new FileWriter("Temporary.txt"))) {

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

        File oldFile = new File("DB_Tasks.txt");
        oldFile.delete();
        File newFile = new File("Temporary.txt");
        newFile.renameTo(oldFile);

    }

    public static void loadMembers(Project project) {

        project.team.members = new ArrayList<TeamMember>();

        try (BufferedReader reader = new BufferedReader(new FileReader("DB_Members.txt"));
                PrintWriter writer = new PrintWriter(new FileWriter("Temporary.txt"))) {

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

        File oldFile = new File("DB_Members.txt");
        oldFile.delete();
        File newFile = new File("Temporary.txt");
        newFile.renameTo(oldFile);

    }

    public static void loadSchedule(Project project) {

        project.schedule.meetings = new ArrayList<Meeting>();

        try (BufferedReader reader = new BufferedReader(new FileReader("DB_Meetings.txt"));
                PrintWriter writer = new PrintWriter(new FileWriter("Temporary.txt"))) {

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

        File oldFile = new File("DB_Meetings.txt");
        oldFile.delete();
        File newFile = new File("Temporary.txt");
        newFile.renameTo(oldFile);

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
