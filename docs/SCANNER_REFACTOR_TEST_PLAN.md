# Scanner Refactoring Test Plan

This document outlines the testing plan for the refactored Scanner management in the Toolbag of Holding project.

## Changes Made

1. Added a centralized `ScannerUtil` class that:
   - Manages a single Scanner instance for the entire application
   - Provides helper methods for input validation
   - Ensures proper resource cleanup on application exit

2. Refactored main program flow in `dndToolbag.java`:
   - Removed multiple Scanner creation/closing
   - Added proper navigation between menus
   - Used ScannerUtil for input validation

3. Refactored `diceRoller.java`:
   - Consolidated duplicate dice rolling methods into a single method
   - Used ScannerUtil for user input
   - Fixed resource leaks

## Testing Steps

### 1. Base Functionality Test

1. Compile the project:
   ```
   javac *.java
   ```

2. Run the application:
   ```
   java dndToolbag
   ```

3. Test Main Menu navigation:
   - Select option 1 (Play)
   - Select option 2 (Character Creation)
   - Select option 3 (Exit)
   - Test invalid input handling

### 2. Play Mode Test

1. Navigate to Play Mode
2. Test each option:
   - Option 1: Roll dice
   - Option 2: Inventory
   - Option 3: Character sheet
   - Option 4: Tracker
   - Option 5: Combat
   - Option 6: Back to main menu

### 3. Dice Roller Test

1. Test each dice type:
   - d4
   - d6
   - d8
   - d10
   - d12
   - d20 (with modifier)
   - d100

2. Test multiple dice
   - Roll multiple dice of each type

3. Test roll again functionality
   - Choose to roll again
   - Choose to return to Play Mode

### 4. Character Creation Test

1. Navigate to Character Creation
2. Test each option:
   - Option 1: Roll stats
   - Option 2: Calculate HP
   - Option 3: Inventory
   - Option 4: Notes
   - Option 5: Finished (returns to Play Mode)
   - Option 6: Back to main menu

### 5. Combat Mode Test

1. Navigate to Combat Mode
2. Test each option:
   - Option 1: Engage
   - Option 2: Cast spells
   - Option 3: Track HP/inspiration
   - Option 4: Flee (return to Play Mode)

### 6. Resource Management Test

1. Use a memory profiler to ensure no Scanner leaks
2. Test application shutdown to ensure Scanner is properly closed

## Expected Results

1. No runtime exceptions related to Scanner operations
2. No "resource leak" warnings from the JVM
3. Consistent navigation between menus
4. Proper validation of all user input
5. Ability to run the full application workflow without crashes

## Notes for Future Development

1. Further refactoring needed:
   - Implement proper object-oriented design
   - Move file operations to dedicated classes
   - Create proper unit tests

2. Current limitations:
   - Still using static methods
   - Module classes still using main() methods
   - Limited error handling for file operations