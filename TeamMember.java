import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

class TeamMember extends Worker {

    // członek zespołu ma swój unikatowy indeks,
    // określony stopień uprawnień, przypisany zespół oraz
    // własną listę zadań do wykonania
    public int index;
    private int permissionStatus;
    public Team memberOf;
    public String report = "";
    public ArrayList<Task> memberTasks = new ArrayList<Task>();
    private LocalDate memberSince;

    // konstruktor klasy
    public TeamMember(String firstName, String lastName, String email, int permissionStatus) {
        super(firstName, lastName, email);
        this.permissionStatus = permissionStatus;
        this.memberSince = LocalDate.now();
    }

    // METODY KLASY

    // dokumentowanie swojej pracy (w ramach cotygodniowego raportu)
    public void submitReport(String weeklyReport) {

        DayOfWeek today = (LocalDate.now()).getDayOfWeek();
        DayOfWeek raportsDay = (memberOf.schedule).reportDay;
        if (today.equals(raportsDay)) {
            this.report = weeklyReport;
        }

        else {
            System.out.println(String.format("The report can only be submitted on %s.", raportsDay));
        }
    }

    // wyświetlanie listy zadań członka zespołu
    public void showTasks() {

        System.out.println(this.firstName + "'s tasks: ");

        for (Task task : memberTasks) {
            System.out.println("--> " + task.name);
            task.showDescription();
            System.out.println("\n");
        }

    }

    // sprawdzenie, czy członek zespołu ma wymagane uprawnienia
    public boolean isAllowed(Task task) {
        return permissionStatus >= task.requiredPermissionStatus;
    }

    // edycja wspólnego pliku
    public void addToCommonFile(String idea) {

        try {
            if (memberOf.commonFile == null || memberOf.commonFile.length() == 0) {
                String fileName = memberOf.nickname + "_commonFile.txt";
                memberOf.commonFile = new File(fileName);
            }

            FileWriter writer = new FileWriter(memberOf.commonFile, true);
            writer.write(idea + "\n");
            writer.write("-- ADDED BY: " + this.firstName + " " + this.lastName + " -- " + LocalDate.now() + " --\n\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the common file: " + e.getMessage());
            e.printStackTrace();
        }

    }

}