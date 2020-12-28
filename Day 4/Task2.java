import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Task2 {
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
        ArrayList<String> inputStraightened = new ArrayList<String>();
        ArrayList<String> inputCleaned = new ArrayList<String>();
        ArrayList<String[]> splitInput = new ArrayList<String[]>();

        String newId = "";
        for (String line : input) {
            if (line.equals("")) {
                inputStraightened.add(newId);
                newId = "";
            }
            else {
                if (line.charAt(line.length() - 1) == ' ') { newId += line; }
                else { newId += line + " "; }
            }
        }

        for (String line : inputStraightened) {
            if (line.contains("pid")) { fields++; }
            if (line.contains("ecl")) { fields++; }
            if (line.contains("hcl")) { fields++; }
            if (line.contains("hgt")) { fields++; }
            if (line.contains("eyr")) { fields++; }
            if (line.contains("iyr")) { fields++; }
            if (line.contains("byr")) { fields++; }
            if (fields == 7) { inputCleaned.add(line); }
            valid = false;
            fields = 0;
        }

        for (String line : inputCleaned) { splitInput.add(line.split(" ")); }
        // for (String[] line : splitInput) { System.out.println(line.length); }

        int validation = 1;
        for (String[] Id : splitInput) {
            for (String field : Id) {
                if (field.substring(0, 4).equals("pid:")) { validation *= checkPid(field.substring(4)); }
                if (field.substring(0, 4).equals("ecl:")) { validation *= checkEcl(field.substring(4)); }
                if (field.substring(0, 4).equals("hcl:")) { validation *= checkHcl(field.substring(4)); }
                if (field.substring(0, 4).equals("hgt:")) { validation *= checkHgt(field.substring(4)); }
                if (field.substring(0, 4).equals("eyr:")) { validation *= checkEyr(field.substring(4)); }
                if (field.substring(0, 4).equals("iyr:")) { validation *= checkIyr(field.substring(4)); }
                if (field.substring(0, 4).equals("byr:")) { validation *= checkByr(field.substring(4)); }
            }
            if (validation == 1) {validIds += 1;}
            validation = 1;
        }
        System.out.println(validIds);
    }

    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) { return false; }
        return str.chars().allMatch(Character::isDigit);
    }

    public static int checkPid(String value) {
        if (value.length() != 9) { return 0; }
        else { if (isNumeric(value)) { return 1; } }
        return 0;
    }

    public static int checkEcl(String value) {
        ArrayList<String> colours = new ArrayList<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
        if (colours.contains(value)) { return 1; }
        return 0;
    }

    public static int checkHcl(String value) {
        ArrayList<String> characters = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f"));
        if (value.substring(0, 1).equals("#")) {
            if (value.substring(1).length() == 6) {
                for (char character : value.substring(1).toCharArray()) {
                    if ((!(isNumeric(String.valueOf(character)))) && (!(characters.contains(String.valueOf(character))))) {
                        return 0;
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    public static int checkHgt(String value) {
        if ((value.substring(value.length() - 2).equals("cm")) && (isNumeric(value.substring(0, value.length() - 2)))) {
            int val = Integer.parseInt(value.substring(0, value.length() - 2));
            if ((val <= 193) && (val >= 150)) { return 1 ; }
        }
        if ((value.substring(value.length() - 2).equals("in")) && (isNumeric(value.substring(0, value.length() - 2)))) {
            int val = Integer.parseInt(value.substring(0, value.length() - 2));
            if ((val <= 76) && (val >= 59)) { return 1 ;  }
        }
        return 0;
    }

    public static int checkEyr(String value) {
        if (value.length() == 4 && isNumeric(value)) {
            int val = Integer.parseInt(value);
            if ((val <= 2030) && (val >= 2020)) { return 1 ; }
        }
        return 0;
    }

    public static int checkIyr(String value) {
        if (value.length() == 4 && isNumeric(value)) {
            int val = Integer.parseInt(value);
            if ((val <= 2020) && (val >= 2010)) { return 1 ; }
        }
        return 0;
    }

    public static int checkByr(String value) {
        if (value.length() == 4 && isNumeric(value)) {
            int val = Integer.parseInt(value);
            if ((val <= 2002) && (val >= 1920)) { return 1 ; }
        }
        return 0;
    }

}
