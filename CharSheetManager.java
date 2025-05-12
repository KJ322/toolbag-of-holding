import java.io.*;
import java.util.*;

public class CharSheetManager 
{
    private static final String FILE_PATH = "charSheet.txt";

    // Define the default structure of the file
    private static final LinkedHashMap<String, String> DEFAULT_SECTIONS = new LinkedHashMap<>() 
    {{
        put("Character Info", "\n");
        put("Hit Points: ", "\n");
        put("Stats:", "\n");
        put("Inventory:", "\n");
        put("Notes:", "\n");
    }};

    // Read the file and return its sections
    public static Map<String, String> readCharSheet() throws IOException 
    {
        Map<String, String> sections = new LinkedHashMap<>(DEFAULT_SECTIONS);

        File file = new File(FILE_PATH);
        if (file.exists()) 
        {
            try (Scanner scanner = new Scanner(file)) 
            {
                String currentSection = null;
                while (scanner.hasNextLine()) 
                {
                    String line = scanner.nextLine();
                    if (sections.containsKey(line)) 
                    {
                        currentSection = line;
                    } 
                    else if (currentSection != null) 
                    {
                        sections.put(currentSection, sections.get(currentSection) + line + "\n");
                    }
                }
            }
        }
        return sections;
    }

    // Write the sections back to the file in the correct order
    public static void writeCharSheet(Map<String, String> sections) throws IOException 
    {
        try (FileWriter writer = new FileWriter(FILE_PATH)) 
        {
            for (Map.Entry<String, String> entry : DEFAULT_SECTIONS.entrySet()) 
            {
                String sectionName = entry.getKey();
                String content = sections.getOrDefault(sectionName, entry.getValue()).trim();
                writer.write(sectionName + "\n");
                writer.write(content + "\n\n");
            }
        }
    }
}
