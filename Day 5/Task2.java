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
        int rowRange = 128;
        int columnRange = 8;
        int row = 0;
        int column = 0;
        int Id;
        int maxId = 0;
        ArrayList<String> input = readFile("input.txt");
        ArrayList<Integer> allIds = new ArrayList<Integer>();
        for (int i = 0; i < 1024; i++) { allIds.add(i); }

        // Could have just used binary number system but ah well
        for (String line : input) {
            for (int i = 0; i < 10; i++) {
                char letter = line.charAt(i);
                if (i <= 6) {
                    if (letter == 'B') {
                        rowRange /= 2;
                        row += rowRange;
                    }
                    if (letter == 'F') {
                        rowRange /= 2;
                    }
                }
                else {
                    if (letter == 'R') {
                        columnRange /= 2;
                        column += columnRange;
                    }
                    if (letter == 'L') {
                        columnRange /= 2;
                    }
                }
            }
            Id = row * 8 + column;
            allIds.remove(Integer.valueOf(Id));
            rowRange = 128;
            columnRange = 8;
            row = 0;
            column = 0;
        }
        System.out.println(allIds); // Take the Id that is inbetween the jump from the large Ids and small ones
    }

}
