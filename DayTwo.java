import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Result {
    String policy;
    String givenLetter;
    String password;
}

public class DayTwo {
    static Result parseLine(String line) {
        String [] str = new String[3];
        Result result = new Result();
        if (line != null || !line.isEmpty())
        {
            str = line.split(" ");

            result.policy = str[0];
            result.givenLetter = str[1];
            result.password = str[2];
        }

        return result;
    }

    static int letterCount(String password, char letter) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch == letter) {
                count++;
            }
        }
        return count;
    }

    static boolean isValidPassword(Result result) {
        String [] validNumber = new String[2];
        int min, max, count;
        validNumber = result.policy.split("-");
        min = Integer.parseInt(validNumber[0]);
        max = Integer.parseInt(validNumber[1]);
        char letter = result.givenLetter.charAt(0);
        String password = result.password;

        count = letterCount(password, letter);

        if (count <= max && count >= min)
            return true;
        else
            return false;
    }

    public static void main(String [] args) {
        BufferedReader reader;
        Result result = new Result();
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader("password.txt"));

            String line = reader.readLine();
            while (line != null) {
                result = parseLine(line);
                if (isValidPassword(result)) {
                    count++;
                }
                line = reader.readLine();
            }
            reader.close();
            System.out.println("There are " + count + " valid password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
