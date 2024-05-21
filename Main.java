
// Demonstracja działania programu na przykładzie ZMIANY (ODŚWIEŻENIA) WIZERUNKU FIRMY (ang. REBRANDING)
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

        public static void main(String[] args) {

                System.out.println("\n");

                // pierwszym krokiem jest wprowadzenie pracowników firmy,
                // w której pojawił się projekt do wykonania
                Worker workerJane = new Worker("Jane", "Sunny", "jane.sunny@company.com");
                Worker workerAlex = new Worker("Alex", "Stark", "alex.stark@company.com");
                Worker workerMark = new Worker("Mark", "Smith", "mark.smith@company.com");

                ProjectManager managerJudy = new ProjectManager("Judy", "Torrens", "judy.torrens@company.com");

                // następnie definiujemy projekt
                Project rebrandingProject = new Project("Company Rebranding",
                                "Refreshing the company's image to align with modern trends and values.",
                                LocalDate.of(2025, 1, 15),
                                "Company's Managers", 120000);

                // ustanawiamy kierownika projektu
                // sprawdzamy, czy managerJudy może kierować nowopowstałym projektem
                if (managerJudy.addProject(rebrandingProject)) {
                        rebrandingProject.setProjectManager(managerJudy);
                }

                else {
                        System.out.println("This Project Manager is already managing 3 projects.");
                        System.out.println("Choose another Project Manager to manage this project.");
                }

                // tworzymy zespół, który będzie pracował nad projektem
                (rebrandingProject.team).setNickname("Band To Rebrand");

                (rebrandingProject.team).addMember(workerJane, 2); // indeks 1
                (rebrandingProject.team).addMember(workerAlex, 1); // indeks 2
                (rebrandingProject.team).addMember(workerMark, 3); // indeks 3

                // dopisujemy zadania, jakich należy się podjąć
                Task research = new Task("Market Research", 0, LocalDate.of(2024, 7, 20), 5000);
                Task logo = new Task("New Logo Design", 2, LocalDate.of(2024, 8, 10), 15000);
                Task website = new Task("Website Redesign", 2, LocalDate.of(2024, 9, 10), 25000);
                Task materials = new Task("Marketing Materials", 2, LocalDate.of(2024, 10, 17), 55000);
                Task campaign = new Task("Launch Campaign", 3, LocalDate.of(2024, 12, 15), 15000);

                // wprowadzamy zadania do taskManagera projektu
                (rebrandingProject.taskManager).addTask(research);
                (rebrandingProject.taskManager).addTask(logo);
                (rebrandingProject.taskManager).addTask(website);
                (rebrandingProject.taskManager).addTask(materials);
                (rebrandingProject.taskManager).addTask(campaign);

                // dodajemy opisy zadań, aby członkowie zespołu dokładnie wiedzieli, co robić
                research.setDescription(
                                "Conducting market research to understand how the brand is perceived by customers, what their preferences are, and analyzing the competitive landscape. This task is crucial to ensure the rebranding aligns with market needs and expectations.");

                logo.setDescription(
                                "Creating a new logo that reflects the updated brand identity, including the design of a new color palette and typography. This task aims to visually represent the new values and vision of the company.");

                website.setDescription(
                                "Redesigning the company's website to align with the new brand identity. This involves updating the visual design, improving user experience, and ensuring the website effectively communicates the rebranded message.");

                materials.setDescription(
                                "Developing new marketing materials, such as business cards, brochures, banners, and product packaging, to reflect the new brand identity. This task ensures all customer touchpoints are consistent with the rebranded image.");

                campaign.setDescription(
                                "Planning and executing a launch campaign to announce the rebranding to customers, partners, and the public. This includes preparing communication strategies, promotional activities, and media outreach to ensure a successful rebranding launch.");

                // przypisujemy zadania konkretnym członkom zespołu
                TeamMember memberJane = (rebrandingProject.team).getMemberByIndex(1);
                TeamMember memberAlex = (rebrandingProject.team).getMemberByIndex(2);
                TeamMember memberMark = (rebrandingProject.team).getMemberByIndex(3);

                (rebrandingProject.taskManager).assignTask(research, memberAlex);
                (rebrandingProject.taskManager).assignTask(logo, memberJane);
                (rebrandingProject.taskManager).assignTask(website, memberJane);
                (rebrandingProject.taskManager).assignTask(materials, memberMark);
                (rebrandingProject.taskManager).assignTask(campaign, memberMark);

                // wypisujemy zadania danego członka zespołu
                memberMark.showTasks();

                // projekt ma już wszystko, co potrzebne, by rozpocząć nad nim pracę
                rebrandingProject.displayInfo();

                // sprawdzamy, jak wygląda sytuacja z budżetem
                rebrandingProject.checkBudget();

                // ustalamy dzień przesyłania raportów
                (rebrandingProject.team).schedule.setReportDay(DayOfWeek.TUESDAY);

                // członek zespołu może podzielić się swoimi przemyśleniami
                memberAlex.addToCommonFile("Some piece of information to share with the others.");

                // może też pisać raporty, które będą sprawdzane przez kierownika projektu
                memberJane.submitReport("Some piece of information about my recent tasks.");
                managerJudy.showReport(memberJane);

                // aktualizujemy harmonogram zespołu - dodajemy spotkanie
                Meeting lunchTogether = new Meeting("Lunch for Team Members", "XYZ",
                                LocalDateTime.of(2024, 6, 10, 12, 30, 0), 2);
                (rebrandingProject.team).schedule.addMeeting(lunchTogether);

                // wyświetlamy harmonogram zespołu
                (rebrandingProject.team).schedule.showMeetings();

                // odznaczamy ukończone zadania
                (rebrandingProject.taskManager).setAsCompleted(logo, memberJane);
                (rebrandingProject.taskManager).setAsCompleted(research, memberAlex);

                // wyświetlamy postępu projektu
                (rebrandingProject.taskManager).generateReport();

                // ponownie sprawdzamy, jak wygląda sytuacja z budżetem
                rebrandingProject.checkBudget();

                // wyświetlamy czas, jaki pozostał do ukończenia projektu
                rebrandingProject.timeLeft();
        }

}