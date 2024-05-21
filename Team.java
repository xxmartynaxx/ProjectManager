import java.io.File;
import java.util.ArrayList;

class Team {

    // zespół ma swoją unikalną nazwę, listę należących do niego członków,
    // harmonogram spotkań oraz plik (wspólny dla wszystkich członków zespołu) do
    // dzielenia się ideami
    public String nickname;
    public ArrayList<TeamMember> members = new ArrayList<TeamMember>();
    public Schedule schedule = new Schedule();
    public File commonFile;
    public static int memberIndex = 1;

    // METODY KLASY

    // wypisanie informacji o zespole
    public void displayInfo() {
        System.out.println("Nickname: " + this.nickname);
        System.out.println("Team members:");
        for (TeamMember member : members) {
            System.out.println("--> " + member.firstName + " " + member.lastName);
        }
        System.out.println("\n");
    }

    // ustalenie unikalnej nazwy zespołu
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // przypisanie zespołowi jego wspólnego pliku
    public void setCommonFile(File commonFile) {
        this.commonFile = commonFile;
    }

    // dodanie nowego członka do zespołu
    public void addMember(Worker newMember, int permissionStatus) {
        TeamMember member = new TeamMember(newMember.firstName, newMember.lastName, newMember.email, permissionStatus);
        member.memberOf = this;
        member.index = memberIndex;
        members.add(member);

        Team.memberIndex += 1;
    }

    // usunięcie członka z zespołu
    public void removeMember(TeamMember member) {
        members.remove(member);
    }

    // zwrócenie konkretnego członka zespołu (po jego unikalnym indeksie)
    public TeamMember getMemberByIndex(int index) {

        for (TeamMember member : members) {
            if (member.index == index) {
                return member;
            }
        }
        System.out.println("Could not find any TeamMember with this index.\n");
        return null;

    }

}