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
        int people = 0;
        int total = 0;
        String question;
        ArrayList<String> input = readFile("input.txt");
        HashMap<String, Integer> questionPoll = new HashMap<String, Integer>();
        for (String line : input) {
            if (line.equals("")) {
                for (String key : questionPoll.keySet()) {
                    if (questionPoll.get(key) == people) {
                        total ++;
                    }
                }
                questionPoll = new HashMap<String, Integer>();
                people = 0;
            }
            else {
                for (int i = 0; i <= line.length() - 1; i++) {
                    question = Character.toString(line.charAt(i));
                    if (!(questionPoll.containsKey(question))) {
                        questionPoll.put(question, 1);
                    }
                    else {
                        questionPoll.put(question, questionPoll.get(question) + 1);
                    }
                }
                people++;
            }

        }
        System.out.println(total);
    }

}
