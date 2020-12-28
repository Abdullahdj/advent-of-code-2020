import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task1 {
    public static ArrayList<String> readFile(String file) {
        ArrayList<String> input = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                input.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void main(String args[]) {
        boolean valid = false;
        int fields = 0;
        int validIds = 0;
        ArrayList<String> input = readFile("input.txt");

        for (String line : input) {
            if (line.equals("")) { if (valid) { validIds++; valid = false; } fields = 0; }
            if (line.contains("pid")) { fields ++; }
            if (line.contains("ecl")) { fields ++; }
            if (line.contains("hcl")) { fields ++; }
            if (line.contains("hgt")) { fields ++; }
            if (line.contains("eyr")) { fields ++; }
            if (line.contains("iyr")) { fields ++; }
            if (line.contains("byr")) { fields ++; }
            if (fields == 7) {valid = true;}
        }
        System.out.println(validIds);
    }

}