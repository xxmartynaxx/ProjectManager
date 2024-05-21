import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Meeting {

    // atrybuty klasy Meeting
    public String title;
    public String place;
    public LocalDateTime time;
    public double duration;

    // konstruktor klasy
    public Meeting(String title, String place, LocalDateTime time, double duration) {
        this.title = title;
        this.place = place;
        this.time = time;
        this.duration = duration;
    }

    // METODY KLASY

    // wyświetlanie informacji o spotkaniu
    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        String formatted = String.format("%s", this.time.format(formatter));
        String toDisplay = String.format("%s -- %s -- %.2f [h]", this.place, formatted, this.duration);
        System.out.println("--> " + this.title + ": " + toDisplay);
    }
    
}