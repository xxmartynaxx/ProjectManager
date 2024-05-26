import java.io.BufferedReader;
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
            System.out.println("Projects file not found. Add the project first.");

        } catch (IOException e) {
            System.out.println("An error occured while loading the projects file.");
        }
    }

    public static void loadTasks(Project project) {

        project.taskManager.tasks = new ArrayList<Task>();
        project.taskManager.completedTasks = new ArrayList<Task>();

    }

    public static void loadMembers(Project project) {

    }

    public static void loadSchedule(Project project) {

    }

    public static void saveProjectsToFile() {

    }

    public static void saveTasksToFile() {

    }

    public static void saveMembersToFile() {

    }

    public static void saveScheduleToFile() {

    }

    /*
     * public void saveChanges() {
     * try (BufferedReader reader = new BufferedReader(new
     * FileReader("Projects.txt"));
     * PrintWriter writer = new PrintWriter(new FileWriter("Temporary.txt"))) {
     * 
     * String line;
     * while ((line = reader.readLine()) != null) {
     * if (!line.startsWith(Integer.toString(this.index))) {
     * writer.println(line);
     * }
     * else {
     * String[] info = line.split(";");
     * writer.println(info[0] + ";" + info[1] + ";" + info[2] + ";" + info[3] + ";"
     * + info[4] + ";" + info[5] + ";" + Double.toString(budget) + "\n");
     * }
     * }
     * 
     * } catch (IOException e) {
     * System.out.println("An error occurred while saving changes.");
     * }
     * 
     * File oldFile = new File("Projects.txt");
     * oldFile.delete();
     * File newFile = new File("Temporary.txt");
     * newFile.renameTo(oldFile);
     * 
     * CompanyDB.loadProjects();
     * }
     */

}
