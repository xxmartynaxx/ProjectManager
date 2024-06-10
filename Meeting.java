import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Meeting {

    // atrybuty klasy
    private int projectIndex;
    private String title;
    private String place;
    private LocalDateTime time;
    private double duration;

    // konstruktor klasy
    public Meeting(int projectIndex, String title, String place, LocalDateTime time, double duration) {
        this.projectIndex = projectIndex;
        this.title = title;
        this.place = place;
        this.time = time;
        this.duration = duration;
    }

    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        String formatted = String.format("%s", this.time.format(formatter));
        String toDisplay = String.format("%s -- %s -- %.2f [h]", this.place, formatted, this.duration);
        System.out.println("--> " + this.title + ": " + toDisplay);
    }

    public String parseToString() {
        String pIndex = Integer.toString(projectIndex);
        String t = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String d = Double.toString(duration);

        String line = pIndex + ";" + title + ";" + place + ";" + t + ";" + d;
        return line;
    }

    public static Meeting loadFromFile(String line) {
        // postać informacji pobieranych z pliku:
        // projectIndex, title, place, time, duration
        String[] informations = line.split(";");

        int pIndex = Integer.parseInt(informations[0]);
        String title = informations[1];
        String place = informations[2];
        LocalDateTime time = LocalDateTime.parse(informations[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        double duration = Double.parseDouble(informations[4]);

        Meeting meeting = new Meeting(pIndex, title, place, time, duration);

        return meeting;
    }
    
}