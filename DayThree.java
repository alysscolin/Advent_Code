import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayThree {
    /* get the character of current line based on right 3, down 1 rule
    * the first line number is 0 */
    static char getCharacter(String line, int lineNum) {
        if (line.length() >= (lineNum * 3)) {
            char ch = line.charAt(lineNum * 3);
            return ch;
        }
       return '*';
    }

    public static void main(String [] args) {
        BufferedReader reader;
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader("map.txt"));

            String line = reader.readLine();
            /* skip the first line */
            line = reader.readLine();
            int lineNum = 1;
            while (line != null) {
                char ch = getCharacter(line, lineNum);
                if (ch == '#') {
                    count++;
                }
                line = reader.readLine();
                lineNum++;
            }
            reader.close();
            System.out.println("There are " + count + " trees");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
