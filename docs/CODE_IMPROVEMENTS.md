# Code Improvement Plan

This document identifies areas of the codebase that need immediate attention and proposes solutions.

## Critical Issues

### 1. Resource Leaks

**Problem:** Multiple instances of Scanner objects are created but not properly closed.

**Examples:**
- `diceRoller.java:rollAgain()` - Scanner is closed but another Scanner is created in the method it calls
- `rollToAttack.java:rollAgain()` - Similar issue with Scanner resource management

**Solution:**
- Create a single Scanner for the application lifecycle
- Pass the Scanner instance between methods instead of creating new ones
- Ensure proper closing at application exit

### 2. Error Handling

**Problem:** Basic exception handling exists but often just prints stack traces.

**Examples:**
- `CharSheetManager.java:writeCharSheet()`
- `inventory.java:updateFile()`

**Solution:**
- Create a consistent error handling strategy
- Add appropriate recovery mechanisms
- Log errors properly
- Provide user-friendly error messages

### 3. Code Duplication

**Problem:** Significant duplication in dice rolling code.

**Examples:**
- `diceRoller.java` has nearly identical methods for each die type (d4, d6, d8, etc.)
- `rollToAttack.java` also duplicates the same dice rolling logic

**Solution:**
- Refactor to use a single generic dice rolling method
- Add parameters for the number of sides
- Create a DiceRoller class with instance methods instead of static methods

### 4. Unfinished Components

**Problem:** Some files are empty or minimally implemented.

**Examples:**
- `characterSheet.java` is essentially empty
- Several features are marked with TODO comments

**Solution:**
- Prioritize completion of core functionality
- Implement missing character sheet display functionality
- Complete spell tracking in combat mode

## Architecture Improvements

### 1. Object-Oriented Approach

**Problem:** The application uses static methods extensively rather than creating proper objects.

**Solution:**
- Convert to a more object-oriented design
- Create Character class to encapsulate character data
- Make utility classes instantiable with state

### 2. Separation of Concerns

**Problem:** User interface code and business logic are tightly coupled.

**Solution:**
- Separate UI code from game logic
- Create service classes for game mechanics
- Create model classes for data representation

### 3. Configuration Management

**Problem:** Hard-coded values throughout the codebase.

**Examples:**
- File path in `CharSheetManager.java`
- Character attributes and dice types

**Solution:**
- Create a configuration class/file
- Allow customization of key parameters
- Make file paths configurable

## Immediate Action Items

1. Fix resource leaks by refactoring Scanner usage
2. Implement proper exception handling
3. Refactor dice rolling code to eliminate duplication
4. Complete the character sheet implementation
5. Add input validation in user-facing methods