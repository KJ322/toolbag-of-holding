# Resource Management Fixes

## Scanner Object Issues

The codebase has significant issues with Scanner resource management. This document outlines problems and fixes.

### Problem Locations

1. **diceRoller.java**
   - Line 28: `Scanner in = new Scanner(System.in);` in `getDice()`
   - Line 194: `Scanner in = new Scanner(System.in);` in `rollAgain()`
   - Line 214: `in.close();` - closes Scanner but then passes it to `dndToolbag.playMode(in)`

2. **rollToAttack.java**
   - Line 26: `Scanner in = new Scanner(System.in);` in `rollAttack()`
   - Line 183: `Scanner in = new Scanner(System.in);` in `rollAgain()`
   - Line 203: `in.close();` - closes Scanner but program continues

3. **rollStats.java**
   - Line 59: `Scanner scanner = new Scanner(System.in);` in `rerollLowStats()`
   - Line 84: `Scanner scanner = new Scanner(System.in);` in `assignStatsAndCalculateModifiers()`
   - Neither Scanner is closed

4. **dndToolbag.java**
   - Line 9: `Scanner k = new Scanner(System.in);` in `main()`
   - Scanner is passed to other methods but never closed

### Proposed Solution

1. **Single Scanner Approach**
   - Create a single Scanner in the main class
   - Pass this Scanner to all methods that need user input
   - Close the Scanner only when the program exits

2. **Implementation Steps**
   - Modify dndToolbag.java to create a single Scanner instance
   - Update all method signatures to accept a Scanner parameter
   - Remove all local Scanner creations
   - Add a proper shutdown hook to ensure Scanner is closed

3. **Changes Required**

```java
// In dndToolbag.java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
        // Application logic
        mainMenu(scanner);
    } finally {
        // Ensure scanner is closed when program exits
        scanner.close();
    }
}

public static void mainMenu(Scanner scanner) {
    // Previously was playMode(Scanner k)
    // Use passed scanner instead of creating new one
}

// In diceRoller.java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
        intro();
        getDice(scanner);
    } finally {
        // Do not close the scanner if it was passed from another class
        // Only close if this is the entry point
        if (args != null) {
            scanner.close();
        }
    }
}

public static void getDice(Scanner scanner) {
    // Use passed scanner instead of creating new one
}
```

4. **Testing Strategy**
   - After modifying each class, test individual functionality
   - Ensure methods use the passed Scanner instead of creating new ones
   - Verify no "resource leak" warnings in IDE
   - Validate program behaves correctly with the new pattern

### Additional Resource Management Issues

1. **File Operations**
   - CharSheetManager.java has file operations that should use try-with-resources
   - Update all file reading/writing code to follow this pattern

2. **Exception Handling**
   - Add proper exception handling for Scanner operations
   - Avoid swallowing exceptions with empty catch blocks