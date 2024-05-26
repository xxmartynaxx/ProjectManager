import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    
    // harmonogram zawiera listę nadchodzących spotkań
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    // wyświetlenie listy wszystkich spotkań
    public void showMeetings() {
        for (Meeting meeting : meetings) {
            meeting.displayInfo();
        }
    }

    // dodanie spotkania
    public void addMeeting(Scanner scanner, Project project) {

        System.out.println("\nTo add a meeting, complete the form below.");

        System.out.println("-- Enter the title of the meeting: ");
        String title = scanner.nextLine();

        System.out.println("-- Enter the place of the meeting: ");
        String place = scanner.nextLine();

        System.out.println("-- Enter the time of the meeting (YYYY-MM-DD HH:MM): ");
        String userDate = scanner.nextLine();
        LocalDateTime time = LocalDateTime.parse(userDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("-- Enter the duration of the meeting (hours): ");
        double duration = scanner.nextDouble();
        scanner.nextLine();

        Meeting newMeeting = new Meeting(project.getIndex(), title, place, time, duration);
        meetings.add(newMeeting);

       // w CompanyDB będzie trzeba załadować te spotkania do odpowiednich projektów
    }
    
}