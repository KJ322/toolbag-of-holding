# Toolbag of Holding - Slide Template

## Slide Structure

Each slide should follow this general structure for consistency:

```
# [Slide Title]

![Thematic image related to slide content](path/to/image.png)

## Key Points

- Point 1
- Point 2
- Point 3

## Details/Code Example

```java
// Sample code with syntax highlighting
public class Example {
    public static void main(String[] args) {
        System.out.println("Hello, adventurer!");
    }
}
```

## Visual Notes

- Use this color palette for all diagrams and highlights:
  - Primary: #7C2128 (D&D red)
  - Secondary: #DBC07C (parchment)
  - Accent: #2C3E50 (dark blue)
  - Background: #F5F1E6 (light parchment)
  - Text: #1A1A1A (near black)

- Use these fonts:
  - Headings: "Modesto" or "Times New Roman" (serif)
  - Body text: "Calibri" or "Arial" (sans-serif)
  - Code: "Consolas" or "Courier New" (monospace)

- Use D&D-themed iconography:
  - Dice for bullet points
  - Scroll backgrounds for code sections
  - Shield icon for security/protection points
  - Spellbook icon for data storage sections
```

## Example Slide: Character Creation Process

# Character Creation Process

![Adventurer creating a character](images/character-creation.png)

## Character Creation Flow

- Enter basic character information (name, class)
- Roll and assign stats (STR, DEX, CON, INT, WIS, CHA)
- Calculate hit points based on class and CON modifier
- Set up initial inventory
- Add character notes (languages, proficiencies, etc.)

## Code Implementation

```java
// Character creation process from dndToolbag.java
public static void charCreation() 
{
    boolean running = true;
    
    // Step 1: Gather basic character info
    charInfo.main(null);

    while (running) 
    {
        System.out.println("You are in character creation mode.\nYou can:");
        System.out.println("1. Roll stats");
        System.out.println("2. Calculate HP");
        System.out.println("3. Start your inventory");
        System.out.println("4. Make notes");
        System.out.println("5. Finished");
        
        int choice = ScannerUtil.getIntInRange("Choose an option: ", 1, 5);
        
        // Process user selection...
    }
}
```

## Visual Notes

- Include a flowchart showing the full character creation process
- Highlight the cyclical nature of the menu until completion
- Show sample stat rolls with dice graphics
- Add screenshot of completed character sheet