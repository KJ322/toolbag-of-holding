import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class rollStats 
{
    public static void main(String[] args) 
    {
        int[] rolledStats = new int[6]; // Array to store the total rolled values

        intro();

        try 
        {
            for (int i = 0; i < 6; i++) 
            {
                int[] rollsResult = rolls(); // Get the full rolls array
                int rollTotal = rollsResult[0] + rollsResult[1] + rollsResult[2] + rollsResult[3]
                        - Math.min(Math.min(rollsResult[0], rollsResult[1]), Math.min(rollsResult[2], rollsResult[3]));
                rolledStats[i] = rollTotal; // Store the total rolled value
            }

            // Allows the user to re-roll stats below 7 if they want
            rolledStats = rerollLowStats(rolledStats);

            // Assign stats and calculate modifiers
            assignStatsAndCalculateModifiers(rolledStats);

        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void intro() 
    {
        System.out.println("Hello! Let's roll up your character's stats!");
        System.out.println("This program will roll your stats, and then you'll be able to assign them.");
        System.out.println("Let's get started!");
    }

    public static int[] rolls() 
    {
        int roll1 = (int) (Math.random() * 6) + 1;
        int roll2 = (int) (Math.random() * 6) + 1;
        int roll3 = (int) (Math.random() * 6) + 1;
        int roll4 = (int) (Math.random() * 6) + 1;

        return new int[] { roll1, roll2, roll3, roll4 };
    }

    public static int[] rerollLowStats(int[] rolledStats) 
    {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < rolledStats.length; i++) 
        {
            if (rolledStats[i] < 7) 
            {
                System.out.println("Stat " + rolledStats[i] + " is below 7. Would you like to re-roll it? (yes/no)");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("yes")) 
                {
                    int[] rollsResult = rolls(); // Re-roll the stat
                    int rollTotal = rollsResult[0] + rollsResult[1] + rollsResult[2] + rollsResult[3]
                            - Math.min(Math.min(rollsResult[0], rollsResult[1]), Math.min(rollsResult[2], rollsResult[3]));
                    rolledStats[i] = rollTotal; // Update the stat with the new roll
                    System.out.println("Re-rolled stat: " + rollTotal);
                }
            }
        }

        
        return rolledStats;
    }

    public static void assignStatsAndCalculateModifiers(int[] rolledStats) {
        Scanner scanner = new Scanner(System.in);
        String[] statNames = { "STR", "DEX", "CON", "INT", "WIS", "CHA" };
        int[] assignedStats = new int[6];
        boolean[] used = new boolean[6];

        System.out.println("You rolled the following stats: ");
        for (int stat : rolledStats) {
            System.out.print(stat + " ");
        }
        System.out.println("\nNow, assign these stats to your character's attributes.");

        for (int i = 0; i < statNames.length; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Assign a value to " + statNames[i] + ": ");
                int value = scanner.nextInt();

                for (int j = 0; j < rolledStats.length; j++) {
                    if (rolledStats[j] == value && !used[j]) {
                        assignedStats[i] = value;
                        used[j] = true;
                        validInput = true;
                        break;
                    }
                }

                if (!validInput) {
                    System.out.println("Invalid input. Please assign an unused rolled value.");
                }
            }
        }

        System.out.println("You have assigned the stats as follows:");
        StringBuilder statsSection = new StringBuilder("");
        for (int i = 0; i < statNames.length; i++) {
            int modifier = calculateModifier(assignedStats[i]);
            System.out.println(statNames[i] + ": " + assignedStats[i] + " (Modifier: " + modifier + ")");
            statsSection.append(statNames[i]).append(": ").append(assignedStats[i])
                        .append(" (Modifier: ").append(modifier).append(")\n");
        }

        try {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            sections.put("Stats:", statsSection.toString().trim());
            CharSheetManager.writeCharSheet(sections);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static int calculateModifier(int abilityScore) {
        return (abilityScore - 10) / 2;
    }
}