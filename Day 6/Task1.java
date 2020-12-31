import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
        int total = 0;
        String question;
        ArrayList<String> input = readFile("input.txt");
        ArrayList<String> questionPoll = new ArrayList<String>();
        for (String line : input) {
            if (line.equals("")) {
                total += questionPoll.size();
                questionPoll = new ArrayList<String>();
            }
            else {
                for (int i = 0; i <= line.length() - 1; i++) {
                    question = Character.toString(line.charAt(i));
                    if (!(questionPoll.contains(question))) {
                        questionPoll.add(question);
                    }
                }
            }
        }
        System.out.println(total);
    }

}
