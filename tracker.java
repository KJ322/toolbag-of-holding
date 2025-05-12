import java.util.Scanner;
import java.util.Map;
import java.io.IOException;

public class tracker 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int xp = 0;
        int hp = 0;
        int maxHp = 0;
        int inspiration = 0;

        // Read HP from charSheet.txt
        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            String hpStr = sections.getOrDefault("Current Hit Points", "").trim();
            String maxHpStr = sections.getOrDefault("Max Hit Points", "").trim();
            if (!hpStr.isEmpty()) {
                hp = Integer.parseInt(hpStr);
            }
            if (!maxHpStr.isEmpty()) {
                maxHp = Integer.parseInt(maxHpStr);
            }
            if (maxHp == 0) {
                System.out.print("Enter your max HP: ");
                maxHp = scanner.nextInt();
                scanner.nextLine();
            }
            if (hp == 0) {
                hp = maxHp;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not read HP from file. Please enter manually.");
            System.out.print("Enter your max HP: ");
            maxHp = scanner.nextInt();
            scanner.nextLine();
            hp = maxHp;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Tracker Menu ---");
            System.out.println("1. View status");
            System.out.println("2. Add XP");
            System.out.println("3. Subtract XP");
            System.out.println("4. Take damage");
            System.out.println("5. Heal");
            System.out.println("6. Set inspiration points");
            System.out.println("7. Add inspiration point");
            System.out.println("8. Subtract inspiration point");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("XP: " + xp);
                    System.out.println("HP: " + hp + "/" + maxHp);
                    System.out.println("Inspiration Points: " + inspiration);
                    break;
                case 2:
                    System.out.print("How much XP to add? ");
                    xp += scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("XP updated.");
                    break;
                case 3:
                    System.out.print("How much XP to subtract? ");
                    xp -= scanner.nextInt();
                    if (xp < 0) xp = 0;
                    scanner.nextLine();
                    System.out.println("XP updated.");
                    break;
                case 4:
                    System.out.print("How much damage taken? ");
                    hp -= scanner.nextInt();
                    if (hp < 0) hp = 0;
                    scanner.nextLine();
                    saveHpToFile(hp);
                    System.out.println("HP updated.");
                    break;
                case 5:
                    System.out.print("How much healing? ");
                    hp += scanner.nextInt();
                    if (hp > maxHp) hp = maxHp;
                    scanner.nextLine();
                    saveHpToFile(hp);
                    System.out.println("HP updated.");
                    break;
                case 6:
                    System.out.print("Set inspiration points to: ");
                    inspiration = scanner.nextInt();
                    if (inspiration < 0) inspiration = 0;
                    scanner.nextLine();
                    System.out.println("Inspiration updated.");
                    break;
                case 7:
                    inspiration++;
                    System.out.println("Inspiration increased.");
                    break;
                case 8:
                    if (inspiration > 0) inspiration--;
                    System.out.println("Inspiration decreased.");
                    break;
                case 9:
                    saveHpToFile(hp);
                    running = false;
                    System.out.println("Exiting tracker.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void saveHpToFile(int hp) {
        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            sections.put("Current Hit Points", String.valueOf(hp));
            CharSheetManager.writeCharSheet(sections);
        } catch (IOException e) {
            System.out.println("Could not save HP to file.");
        }
    }
}
