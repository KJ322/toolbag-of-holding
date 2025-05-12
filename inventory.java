import java.io.*;
import java.util.*;

public class inventory 
{
    private static ArrayList<String> inventory = new ArrayList<>();

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) 
        {
            System.out.println("\nInventory Tracker:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. View inventory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    removeItem(scanner);
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting inventory tracker.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) 
    {
        System.out.print("Enter the name of the item to add: ");
        String item = scanner.nextLine();
        inventory.add(item);
        updateFile();
        System.out.println("Item added to inventory.");
    }

    private static void removeItem(Scanner scanner) 
    {
        System.out.print("Enter the name of the item to remove: ");
        String item = scanner.nextLine();
        if (inventory.remove(item)) 
        {
            updateFile();
            System.out.println("Item removed from inventory.");
        } 
        else 
        {
            System.out.println("Item not found in inventory.");
        }
    }

    private static void viewInventory() 
    {
        System.out.println("\nCurrent Inventory:");
        if (inventory.isEmpty()) 
        {
            System.out.println("Inventory is empty.");
        } 
        else 
        {
            for (String item : inventory) 
            {
                System.out.println("- " + item);
            }
        }
    }

    private static void updateFile() 
    {
        try 
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            StringBuilder inventorySection = new StringBuilder("");
            for (String item : inventory) 
            {
                inventorySection.append("- ").append(item).append("\n");
            }
            sections.put("Inventory:", inventorySection.toString().trim());
            CharSheetManager.writeCharSheet(sections);
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while updating the inventory.");
            e.printStackTrace();
        }
    }
}