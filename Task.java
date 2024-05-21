import java.time.LocalDate;

class Task {

    // atrybuty klasy Task
    public String name;
    public String description = "";
    public int requiredPermissionStatus;
    public LocalDate deadline;
    public double estimatedCost;
    public boolean isCompleted;

    // konstruktor klasy
    public Task(String name, int requiredPermissionStatus, LocalDate deadline, double estimatedCost) {
        this.name = name;
        this.requiredPermissionStatus = requiredPermissionStatus;
        this.deadline = deadline;
        this.estimatedCost = estimatedCost;
        this.isCompleted = false;
    }

    // METODY KLASY

    // ustalanie opisu zadania
    public void setDescription(String description) {
        this.description = description;
    }

    // wyświetlanie opisu zadania
    public void showDescription() {
        System.out.println(this.description);
    }

    // aktualizowanie szacowanego kosztu wykonania zadania
    public void updateEstimatedCost(double newEstimatedCost) {
        this.estimatedCost = newEstimatedCost;
    }
    
}