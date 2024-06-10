class Worker {

    // atrybuty klasy
    private int index;
    private String firstName;
    private String lastName;
    private String email;

    // konstruktor klasy
    public Worker(int index, String firstName, String lastName, String email, boolean addToCompanyWorkers) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        // atrybut pozwalający odróżnić pracowników od kierowników projektów
        if (addToCompanyWorkers) {
            Main.companyWorkers.add(this);
        }

    }

    public void displayInfo() {
        System.out.println(index + "  " + firstName + " " + lastName + "  " + email);
    }

    public int getIndex() {
        return this.index;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

}