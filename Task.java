import java.time.LocalDate;
class Task {

    // atrybuty klasy Task
    public int index;
    private int projectIndex;
    private int memberIndex;
    public String name;
    public String description;
    public int requiredPermissionStatus;
    public LocalDate deadline;
    public double estimatedCost;
    public boolean isCompleted;

    // konstruktor klasy
    public Task(int memberIndex, String name, String description, int requiredPermissionStatus, LocalDate deadline, double estimatedCost) {
        this.memberIndex = memberIndex;
        this.name = name;
        this.description = description;
        this.requiredPermissionStatus = requiredPermissionStatus;
        this.deadline = deadline;
        this.estimatedCost = estimatedCost;
        this.isCompleted = false;
    }

    // wyświetlanie informacji
    public void displayinfo() { 
        System.out.println("Name: "+ name);
        System.out.println("Description: " + description);
        System.out.println(String.format("Required permission status: %d", requiredPermissionStatus));
        System.out.println(String.format("Deadline: %s", deadline));
        System.out.println(String.format("Estimated cost: %.2f", estimatedCost));
    }

    public int getIndex() {
        return this.index;
    }

    public int getProjectIndex() {
        return this.projectIndex;
    }

    public int getMemberIndex() {
        return this.memberIndex;
    }

    public void setMemberIndex(int newMemberIndex) {
        this.memberIndex = newMemberIndex;
    }
    
}