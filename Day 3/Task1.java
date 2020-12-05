import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        int xPointer = 0;
        int treeCount = 0;

        ArrayList<String> input = readFile("input.txt");

        for (int yPointer = 0; yPointer < input.size(); yPointer++) {
            String row = input.get(yPointer);
            if (yPointer != 0) {
                if(row.charAt(xPointer) == '#') { treeCount++; }
                xPointer = (xPointer + 3) % row.length();
            }
            else { xPointer = (xPointer + 3) % row.length(); }
        }
        System.out.println(treeCount);
    }

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
}