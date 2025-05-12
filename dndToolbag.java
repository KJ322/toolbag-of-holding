import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

public class dndToolbag 
{    
    public static void main(String[] args)
    {
        Scanner k = new Scanner (System.in);
        final int PLAY = 1;
        final int CREATE = 2;


        System.out.println("Welcome to the Tool Bag of Holding!\n\nDo you want to play or create a character?");
        System.out.println("1. Play\n2. Character Creation\n3. Exit");
        System.out.print("Choose: ");
        //int userMode = k.nextInt();

        if (k.hasNextInt())
        {
            int userMode = k.nextInt();
            if (userMode == PLAY)
            {
                playMode(k);
            }
            else if (userMode == CREATE)
            {
                charCreation(k);
            }
            else
            {
                System.out.println("Invalid input.");
                System.out.println("1. Play\n2. Character Creation\n3. Exit");
                userMode = k.nextInt();
            }
        }
        else
        {
            System.out.println("Invalid input. Please enter 1 or 2.");
        } 
    }

    public static void playMode(Scanner k) 
    {
        final int ROLL = 1;
        final int INVENTORY = 2;
        final int CHAR_SHEET = 3;
        final int TRACKER = 4;
        final int COMBAT = 5;
    
        boolean running = true;
    
        while (running) {
            System.out.print("You are in play mode.\nYou can:\n");
            System.out.println("1. Roll");
            System.out.println("2. Keep track of inventory");
            System.out.println("3. Check your character sheet");
            System.out.println("4. Track inspiration, hp, and xp");
            System.out.println("5. Enter combat");
            System.out.print("Choose an option: ");
    
            if (k.hasNextInt()) 
            {
                int choice = k.nextInt();
                k.nextLine(); // Consume newline
                System.out.println();
    
                switch (choice) 
                {
                    case ROLL:
                        diceRoller.main(null);
                        break;
                    case INVENTORY:
                        inventory.main(null);
                        break;
                    case CHAR_SHEET:
                        characterSheet.main(null);
                        break;
                    case TRACKER:
                        tracker.main(null);
                        break;
                    case COMBAT:
                        combatMode(k);
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            } 
            else 
            {
                System.out.println("Invalid input. Please enter a number.");
                k.nextLine(); // Consume invalid input
            }
        }
    }


    public static void charCreation(Scanner k) 
    {
        final int ROLL_STATS = 1;
        final int CALC_HP = 2;
        final int INVENTORY = 3;
        final int NOTES = 4;
        final int FINISHED = 5;
    
        boolean running = true;
    
        charInfo.main(null);

        while (running) 
        {
            System.out.print("You are in character creation mode.\nYou can:\n");
            System.out.println("1. Roll stats");
            System.out.println("2. Calculate HP");
            System.out.println("3. Start your inventory");
            System.out.println("4. Make notes");
            System.out.println("5. Finished");
            System.out.print("Choose an option: ");
    
            if (k.hasNextInt()) 
            {
                int choice = k.nextInt();
                k.nextLine(); // Consume newline
                System.out.println();
    
                switch (choice) 
                {
                    case ROLL_STATS:
                        rollStats.main(null);
                        break;
                    case CALC_HP:
                        hitPoints.main(null);
                        break;
                    case INVENTORY:
                        inventory.main(null);
                        break;
                    case NOTES:
                        charNotes.main(null);
                        break;
                    case FINISHED:
                        System.out.println("Character creation finished.");
                        running = false;
                        playMode(k);
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            } 
            else 
            {
                System.out.println("Invalid input. Please enter a number 1-5.");
                k.nextLine(); // Consume invalid input
            }
        }
    }

    public static void combatMode(Scanner k)
    {
        //TODO
        final int ATTACK = 1;
        final int SPELLS = 2;
        final int TRACK = 3;
        final int FLEE = 4;


        System.out.print("You are in combat mode.\nYou can:\n");
        System.out.println("1. Engage");
        System.out.println("2. Cast spells");
        System.out.println("3. Track HP and inspiration");
        System.out.println("4. Flee (exit)");
        System.out.print("Chose an option: ");
        int choice = k.nextInt();

        if (choice == ATTACK)
        {
            rollToAttack.main(null);
        }
        else if (choice == SPELLS)
        {
            //TODO
            System.out.print("Call spell tracker java file");
        }
        else if (choice == TRACK)
        {
            //TODO
            tracker.main(null);
        }
        else if (choice == FLEE)
        {
            System.out.println("You have disengaged from combat.");
            playMode(k);
        }
        else
        {
            System.out.println("Invalid input.");
            combatMode(k);
        }
    }
}