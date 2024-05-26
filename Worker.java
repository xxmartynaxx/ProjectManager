class Worker {

    // atrybuty klasy Worker
    private int index;
    public String firstName;
    public String lastName;
    public String email;

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
}