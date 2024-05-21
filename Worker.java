class Worker {

    // atrybuty klasy Worker
    public String firstName;
    public String lastName;
    public String email;
    private double salary;

    // konstruktor klasy
    public Worker(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // METODY KLASY

    // wyświetlanie informacji o pracowniku
    public void displayInfo() {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Salary: " + salary);
    }

    // zmiana atrybutów pracownika
    public void changeInfo(String newFirstName, String newLastName, String newEmail) {

        if (this.firstName != newFirstName) {
            this.firstName = newFirstName;
        }

        if (this.lastName != newLastName) {
            this.lastName = newLastName;
        }

        if (this.email != newEmail) {
            this.email = newEmail;
        }

    }

    // ustalanie pensji
    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }

    // uwzględnianie podwyżek
    public void increaseSalary(double amount) {
        salary += amount;
    }

}