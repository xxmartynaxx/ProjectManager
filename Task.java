import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {

    // atrybuty klasy Task
    private int index;
    private int projectIndex;
    private int memberIndex;
    public String name;
    public String description;
    public int requiredPermissionStatus;
    public LocalDate deadline;
    public double estimatedCost;
    public boolean isCompleted;

    // konstruktor klasy
    public Task(int memberIndex, String name, String description, int requiredPermissionStatus, LocalDate deadline,
            double estimatedCost) {
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
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println(String.format("Required permission status: %d", requiredPermissionStatus));
        System.out.println(String.format("Deadline: %s", deadline));
        System.out.println(String.format("Estimated cost: %.2f", estimatedCost));
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getProjectIndex() {
        return this.projectIndex;
    }

    public void setProjectIndex(int index) {
        this.projectIndex = index;
    }

    public int getMemberIndex() {
        return this.memberIndex;
    }

    public void setMemberIndex(int newMemberIndex) {
        this.memberIndex = newMemberIndex;
    }

    public String parseToString() {
        String tIndex = Integer.toString(index);
        String pIndex = Integer.toString(projectIndex);
        String mIndex = Integer.toString(memberIndex);
        String rPS = Integer.toString(requiredPermissionStatus);
        String d = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String eC = Double.toString(estimatedCost);

        String c;
        if (isCompleted) {
            c = "c";
        } else {
            c = "nc";
        }

        String line = pIndex + ";" + tIndex + ";" + mIndex + ";" + name + ";" + description + ";" + rPS + ";" + d + ";"
                + eC + ";" + c;
        return line;
    }

    public static Task loadFromFile(String line) {
        // projectIndex, index, memberIndex, name, descr, req, deadline, estimCost,
        // isCompl
        String[] informations = line.split(";");

        int pIndex = Integer.parseInt(informations[0]);
        int tIndex = Integer.parseInt(informations[1]);
        int mIndex = Integer.parseInt(informations[2]);
        String name = informations[3];
        String description = informations[4];
        int requiredPermissionStatus = Integer.parseInt(informations[5]);
        LocalDate deadline = LocalDate.parse(informations[6], DateTimeFormatter.ISO_LOCAL_DATE);
        double estimatedCost = Double.parseDouble(informations[7]);
        String isCompleted = informations[8];

        Task task = new Task(mIndex, name, description, requiredPermissionStatus, deadline, estimatedCost);

        task.setIndex(tIndex);
        task.setProjectIndex(pIndex);

        if (isCompleted == "c") {
            task.isCompleted = true;
        }

        return task;
    }

}