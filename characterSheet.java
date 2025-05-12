import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class characterSheet 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner (System.in);
        String filePath = "/workspaces/toolbag-of-holding/charSheet.txt"; // Replace with the actual path to your file

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                System.out.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error reading file: " + e.getMessage());
        }
        dndToolbag.playMode(in);
    }  
}