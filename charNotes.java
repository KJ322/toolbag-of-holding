import java.io.*;
import java.util.*;

public class charNotes {
    private static ArrayList<String> notes = new ArrayList<>();

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) 
        {
            System.out.println("\nCharacter Notes Manager:");
            System.out.println("1. Add a note");
            System.out.println("2. View notes");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting character notes manager.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNote(Scanner scanner) 
    {
        System.out.print("Enter your character note: ");
        String note = scanner.nextLine();
        notes.add(note);
        updateFile();
        System.out.println("Note added.");
    }

    private static void viewNotes() 
    {
        System.out.println("\nCharacter Notes:");
        if (notes.isEmpty()) 
        {
            System.out.println("No notes available.");
        } 
        else 
        {
            for (String note : notes) 
            {
                System.out.println("- " + note);
            }
        }
    }

    private static void updateFile() 
    {
        try 
        {
            Map<String, String> sections = CharSheetManager.readCharSheet();
            StringBuilder notesSection = new StringBuilder("");
            for (String note : notes) 
            {
                notesSection.append("- ").append(note).append("\n");
            }
            sections.put("Notes:", notesSection.toString().trim());
            CharSheetManager.writeCharSheet(sections);
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while updating the notes.");
            e.printStackTrace();
        }
    }
}