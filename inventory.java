import java.io.*;
import java.util.*;

public class inventory 
{
    private static ArrayList<String> inventory = new ArrayList<>();

    public static void main(String[] args) 
    {
        // Load inventory from file at the start
        try 
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            String inventorySection = sections.getOrDefault("Inventory:", "");
            inventory.clear();
            if (!inventorySection.isBlank()) 
            {
                String[] items = inventorySection.split("\\n");
                for (String item : items) 
                {
                    // Remove leading "- " if present
                    item = item.trim();
                    if (item.startsWith("- ")) 
                    {
                        item = item.substring(2).trim();
                    }
                    if (!item.isEmpty()) 
                    {
                        inventory.add(item);
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Could not load inventory from file.");
        }

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
                    System.out.println("Exiting inventory tracker.\n");
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
        Map<String, String> sections;
        try 
        {
            sections = CharSheetManager.readCharSheet();
            String inventorySection = sections.getOrDefault("Inventory:", "");
            if (inventorySection.isBlank()) 
            {
                System.out.println("Inventory is empty.");
            } 
            else 
            {
                String[] items = inventorySection.split("\\n");
                for (String item : items) 
                {
                    System.out.println(item);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while reading the inventory from file.");
            e.printStackTrace();
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