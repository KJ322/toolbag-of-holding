# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

The Toolbag of Holding is a Java-based console application for Dungeons & Dragons players. It provides tools for character creation, gameplay, and combat tracking.

### Key Components

1. **dndToolbag.java**: Main entry point with three primary modes:
   - Play Mode: Access dice rolling, inventory, character sheets, and combat
   - Character Creation: Roll stats, calculate HP, manage inventory, and make notes
   - Combat Mode: Handle attacks, spells, and HP tracking

2. **CharSheetManager.java**: Handles reading/writing character data to a text file with sections for:
   - Character Info
   - Hit Points
   - Assigned Stats
   - Inventory
   - Notes

3. **Utility Classes**:
   - **diceRoller.java**: Handles various dice rolls (d4, d6, d8, d10, d12, d20, d100)
   - **rollStats.java**: Generates and assigns character ability scores
   - **hitPoints.java**: Calculates and tracks character hit points
   - **inventory.java**: Manages character inventory items
   - **charInfo.java**: Records basic character information
   - **charNotes.java**: Stores character notes

## Architecture

The application follows a simple modular design with:
- A main controller class (dndToolbag)
- A data manager (CharSheetManager)
- Specialized tool classes for different D&D mechanics

All character data is stored in a single text file (charSheet.txt) with predefined sections.

## Build and Run Instructions

To compile and run the project:

```bash
# Using the run script (recommended)
./run.sh

# Manual compilation
mkdir -p build/classes
javac -d build/classes src/main/java/*.java
java -cp build/classes dndToolbag
```

## Development Guidance

When working on this codebase:

1. Each class handles a specific D&D mechanic and should maintain that separation of concerns.

2. The CharSheetManager class provides a centralized way to read/write character data - use it instead of creating additional file operations.

3. All user interface code is console-based with Scanner input and System.out output.

4. Classes are connected through static method calls, not object instantiation.

5. Changes should maintain the text-based UI approach and file-based data storage.

## TODO List

The following improvements are planned for this project:

### Completed Items
- ✅ Added centralized ScannerUtil class for input handling
- ✅ Fixed Scanner resource leaks in multiple files
- ✅ Improved error handling with specific error messages
- ✅ Added input validation throughout the application
- ✅ Refactored diceRoller.java to eliminate code duplication
- ✅ Added JavaDoc comments to major methods
- ✅ Created project documentation (README, planning docs)
- ✅ Refactored charNotes.java to use ScannerUtil
- ✅ Refactored rollToAttack.java to eliminate duplicate dice code
- ✅ Refactored CharSheetManager.java with better error handling and backup
- ✅ Added support for multiple character sheets
- ✅ Created basic test framework and utilities
- ✅ Added initial tests for ScannerUtil and CharSheetManager
- ✅ Reorganized project into standard directory structure (src/main/java, src/test/java, docs, etc.)
- ✅ Updated build and run scripts to work with new structure
- ✅ Created interactive HTML presentation for project showcase
- ✅ Added run scripts to handle Java path settings

### Remaining Tasks

1. **Refactoring Completion**:
   - Complete the characterSheet.java implementation
   - Complete remaining HTML presentation slides

2. **Unit Testing Implementation**:
   - Add JUnit library integration
   - Create tests for remaining utility classes
   - Add test coverage reporting
   - Create test fixtures for CharSheetManager
   - Implement test cases for dice rolling accuracy
   - Add tests for character stat calculations

3. **Feature Development**:
   - Implement spell tracking functionality in combat mode
   - Add character leveling mechanics
   - Enhance save/load functionality for multiple characters
   - Create character export/import functionality

4. **User Experience Improvements**:
   - Add confirmation prompts for critical actions
   - Implement a help system
   - Improve character sheet formatting
   - Add command history for frequently used actions

5. **Architecture Enhancements**:
   - Convert to proper object-oriented design
   - Create object models for Character, Inventory, etc.
   - Separate UI code from business logic
   - Implement design patterns for better maintainability

6. **Documentation**:
   - Complete comprehensive JavaDoc documentation
   - Create user guide with examples
   - Add developer guide for contributors