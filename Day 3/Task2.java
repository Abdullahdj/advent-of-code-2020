import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {

    public static void main(String[] args) {
        ArrayList<Integer> treeCounts = new ArrayList<Integer>();
        ArrayList<String> input = readFile("input.txt");
        long product = 1;
        treeCounts.add(calculateTrajectory(input, 1, 1));
        treeCounts.add(calculateTrajectory(input, 3, 1));
        treeCounts.add(calculateTrajectory(input, 5, 1));
        treeCounts.add(calculateTrajectory(input, 7, 1));
        treeCounts.add(calculateTrajectory(input, 1, 2));
        for (int val : treeCounts) { product *= val; }
        System.out.println(product);
    }

    public static int calculateTrajectory(ArrayList<String> input, int x, int y ) {
        int xPointer = 0;
        int treeCount = 0;
        for (int yPointer = 0; yPointer < input.size(); yPointer += y) {
            String row = input.get(yPointer);
            if (yPointer != 0) {
                if(row.charAt(xPointer) == '#') { treeCount++; }
                xPointer = (xPointer + x) % row.length();
            }
            else { xPointer = (xPointer + x) % row.length(); }
        }
        return treeCount;
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