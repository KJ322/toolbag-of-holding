# Testing Dependencies for Toolbag of Holding

This document outlines the dependencies needed to run the full test suite with JUnit.

## Required Libraries

To properly run the unit tests, you'll need the following JUnit dependencies:

1. **JUnit 5 Platform**:
   - `junit-platform-console-standalone-1.9.2.jar`

2. **JUnit 5 Jupiter**:
   - `junit-jupiter-api-5.9.2.jar`
   - `junit-jupiter-engine-5.9.2.jar`
   - `junit-jupiter-params-5.9.2.jar`

3. **Mockito** (for mocking):
   - `mockito-core-5.2.0.jar`
   - `mockito-junit-jupiter-5.2.0.jar`

## Installation Methods

### Manual Download

You can download these JARs from Maven Central:
- [JUnit](https://search.maven.org/search?q=g:org.junit)
- [Mockito](https://search.maven.org/search?q=g:org.mockito)

Save the JAR files to a `lib` directory in your project root.

### Using Maven

If using Maven, add these dependencies to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.2.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Using Gradle

If using Gradle, add these dependencies to your `build.gradle`:

```groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
    testImplementation 'org.mockito:mockito-junit-jupiter:5.2.0'
}
```

## Running Tests with JUnit

### With Manual JARs

```bash
javac -cp ".:lib/*" -d ./out src/main/java/*.java src/test/java/*.java
java -jar lib/junit-platform-console-standalone-1.9.2.jar --class-path ./out --scan-class-path
```

### With Maven

```bash
mvn test
```

### With Gradle

```bash
./gradlew test
```

## Future Test Structure

Once JUnit is integrated, tests should be organized as follows:

```
src/
  main/
    java/
      *.java (current files)
  test/
    java/
      utility/
        TestUtils.java
      scannerUtil/
        ScannerUtilTest.java
      charManager/
        CharSheetManagerTest.java
      dice/
        DiceRollerTest.java
      character/
        StatCalculationTest.java
```

This will provide a clean organization for the growing test suite.