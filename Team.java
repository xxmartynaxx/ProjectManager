import java.util.ArrayList;
import java.util.Scanner;

class Team {

    // atrybuty klasy
    private String nickname;
    private ArrayList<TeamMember> members = new ArrayList<TeamMember>();

    public void initializeMembersArray() {
        this.members = new ArrayList<TeamMember>();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public ArrayList<TeamMember> getMembers() {
        return this.members;
    }

    public void addMember(TeamMember teamMember) {
        this.members.add(teamMember);
    }

    public void displayInfo() {
        System.out.println("\nNickname: " + this.nickname);
        System.out.println("Team members:");

        if (members.size() == 0) {
            System.out.println("-- The list of members is empty. Consider adding a new member to your team first.");
            return;
        }

        for (TeamMember member : members) {
            System.out.println("--> " + member.getIndex() + " " + member.getFirstName() + " " + member.getLastName());
        }
    }

    public void addNewMember(Scanner scanner, Project project) {

        System.out.println("\nTo add a Team Member, complete the form below. ");

        System.out.println("-- Enter the index of the Worker, you want to add to the team:");
        int workerIndex = scanner.nextInt();

        if (Main.getWorkerByIndex(workerIndex) != null && getMemberByIndex(workerIndex) == null) {

            System.out.println("-- Enter the permission status for this Worker:");
            int permissionStatus = scanner.nextInt();

            TeamMember newMember = new TeamMember(workerIndex, project.getIndex(),
                    Main.getWorkerByIndex(workerIndex).getFirstName(), Main.getWorkerByIndex(workerIndex).getLastName(),
                    Main.getWorkerByIndex(workerIndex).getEmail(), permissionStatus);

            members.add(newMember);
            System.out.println("\nThe member has been added successfully.");

        }

        else {
            System.out.println(
                    "\nCould not find any Worker with this index or this Worker is already a part of this team.");
            return;
        }
    }

    public void removeMember(Scanner scanner) {

        if (members.size() == 0) {
            System.out.println("\nThe list of members is empty.");
            return;
        }

        System.out.println("\nEnter the index of the team member to remove from the team.");
        int indexTM = scanner.nextInt();

        for (TeamMember teamMember : members) {
            if (teamMember.getIndex() == indexTM) {
                members.remove(teamMember);
            }
        }
    }

    public TeamMember getMemberByIndex(int index) {

        for (TeamMember teamMember : members) {
            if (teamMember.getIndex() == index) {
                return teamMember;
            }
        }
        return null;
    }

}