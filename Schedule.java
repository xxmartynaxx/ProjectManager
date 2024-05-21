import java.time.DayOfWeek;
import java.util.ArrayList;

public class Schedule {
    
    // harmonogram zawiera informację o dniu cotygodniowych raportów
    // oraz listę nadchodzących spotkań
    public DayOfWeek reportDay;
    public ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    // METODY KLASY

    // ustanowienie dnia tygodnia, w którym będą wysyłane raporty
    public void setReportDay(DayOfWeek day) {
        this.reportDay = day;
    }

    // wyświetlenie listy wszystkich spotkań
    public void showMeetings() {
        for (Meeting meeting : meetings) {
            meeting.displayInfo();
            System.out.println("\n");
        }
    }

    // dodanie spotkania
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    // usunięcie spotkania
    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }
    
}