class Worker {

    // atrybuty klasy Worker
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

        if (addToCompanyWorkers) {
            Main.companyWorkers.add(this);
        }
        
    }

    // wyświetlanie informacji o pracowniku
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