import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {

    // harmonogram zawiera listę nadchodzących spotkań
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    public void initializeMeetingsArray() {
        this.meetings = new ArrayList<Meeting>();
    }

    public ArrayList<Meeting> getMeetings() {
        return this.meetings;
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    // wyświetlenie listy wszystkich spotkań
    public void showMeetings() {

        if (meetings.size() == 0) {
            System.out
                    .println("-- The list of meetings is empty. Consider adding a new meeting to the schedule first.");
            return;
        }

        for (Meeting meeting : meetings) {
            meeting.displayInfo();
        }
    }

    // dodanie spotkania
    public void addMeeting(Scanner scanner, Project project) {

        System.out.println("\nTo add a meeting, complete the form below.");
        scanner.nextLine();

        System.out.println("-- Enter the title of the meeting: ");
        String title = scanner.nextLine();

        System.out.println("-- Enter the place of the meeting: ");
        String place = scanner.nextLine();

        System.out.println("-- Enter the time of the meeting (YYYY-MM-DD HH:MM): ");
        String userDate = scanner.nextLine();
        LocalDateTime time = LocalDateTime.parse(userDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (!time.isAfter(LocalDateTime.now())) {
            System.out.println("\nThe time of the meeting cannot refer to a date that has already passed.");
            return;
        }

        System.out.println("-- Enter the duration of the meeting (hours): ");
        double duration = scanner.nextDouble();
        scanner.nextLine();

        Meeting newMeeting = new Meeting(project.getIndex(), title, place, time, duration);
        meetings.add(newMeeting);

        System.out.println("\nThe meeting has been added successfully.");

    }

}