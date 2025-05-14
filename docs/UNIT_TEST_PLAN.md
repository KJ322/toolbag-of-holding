# Unit Test Plan for Toolbag of Holding

This document outlines the plan for adding unit tests to the Toolbag of Holding project.

## Test Framework

We'll use JUnit 5 for testing. Dependencies needed:

```xml
<!-- Add to pom.xml if using Maven -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

```bash
# Or download JUnit 5 JAR files manually if not using a build tool
# Place in a 'lib' directory
```

## Test Structure

Tests should be organized in a parallel structure to the main code:

```
src/
  main/
    java/
      *.java (current files)
  test/
    java/
      CharSheetManagerTest.java
      diceRollerTest.java
      rollStatsTest.java
      ...
```

## Priority Test Cases

1. **CharSheetManager Tests**
   - Test file reading/writing
   - Test section management
   - Test default section creation
   
2. **Dice Rolling Tests**
   - Test random number distribution 
   - Test roll calculation logic
   - Test modifier application
   
3. **Stat Calculation Tests**
   - Test ability score to modifier conversion
   - Test stat rolling mechanics
   - Test HP calculation based on class and CON

4. **Inventory Management Tests**
   - Test adding/removing items
   - Test persistence to file

## Mocking Strategy

To avoid file system dependencies:

1. Create a mockable file system interface
2. Use temporary files for testing file operations
3. Mock user input with predetermined values

## Refactoring Needed for Testability

Current code has several issues that make testing difficult:

1. **Static Methods**: Most functionality is in static methods, making mocking difficult.
   - Solution: Create interfaces and classes that can be mocked

2. **Direct System I/O**: Direct use of System.out and Scanner makes testing user interaction challenging.
   - Solution: Abstract I/O operations to allow for injectable test doubles

3. **File Operations**: Direct file operations are hard to test.
   - Solution: Create file operation abstractions

## Implementation Plan

1. Set up testing environment and dependencies
2. Create test utility classes for common operations
3. Start with unit tests for core functionality that doesn't require UI:
   - CharSheetManager
   - Dice roll calculations
   - Stat calculations
4. Refactor code to improve testability
5. Add more comprehensive tests as code becomes more testable

## Test Execution

```bash
# To compile and run tests (after JUnit is set up)
javac -cp .:junit-platform-console-standalone-1.8.2.jar -d ./out src/test/java/*.java src/main/java/*.java
java -jar junit-platform-console-standalone-1.8.2.jar --class-path ./out --scan-class-path
```