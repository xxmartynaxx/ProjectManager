import java.util.ArrayList;
import java.util.Scanner;

class Team {

    // zespół ma swoją unikalną nazwę, listę należących do niego członków
    public String nickname;
    private ArrayList<TeamMember> members = new ArrayList<TeamMember>();

    // wypisanie informacji o zespole
    public void displayInfo() {
        System.out.println("Nickname: " + this.nickname);
        System.out.println("Team members:");
        for (TeamMember member : members) {
            System.out.println("--> " + member.getIndex() + " " + member.firstName + " " + member.lastName);
        }
    }

    // dodanie nowego członka do zespołu
    public void addMember(Scanner scanner, Project project) {

        System.out.println("\nTo add a Team Member, complete the form below. ");

        System.out.println("\nEnter the index of the Worker, you want to add to the team.");
        int indexWorker = scanner.nextInt();

        if (Main.getWorkerByIndex(indexWorker) != null && getMemberByIndex(indexWorker) == null) {

            System.out.println("\nEnter the permission status for this Worker.");
            int permissionStatus = scanner.nextInt();

            TeamMember newMember = new TeamMember(indexWorker, project.getIndex(), Main.getWorkerByIndex(indexWorker).firstName, Main.getWorkerByIndex(indexWorker).lastName, Main.getWorkerByIndex(indexWorker).email, permissionStatus);

            members.add(newMember);

        }

        else {
            System.out.println("\nCould not find any Worker with this index or this Worker is already a part of this team.");
            return;
        }

    }

    // usunięcie członka z zespołu
    public void removeMember(Scanner scanner) {
        System.out.println("\nEnter the index of the team member to remove from the team.");
        int indexTM = scanner.nextInt();

        for (TeamMember teamMember : members) {
            if (teamMember.getIndex() == indexTM) {
                members.remove(teamMember);
            }
        }
    }

    // zwrócenie konkretnego członka zespołu (po jego unikalnym indeksie)
    public TeamMember getMemberByIndex(int index) {

        for (TeamMember teamMember : members) {
            if (teamMember.getIndex() == index) {
                return teamMember;
            }
        }
        return null;
    }

}