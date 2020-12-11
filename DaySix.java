import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* to make thing easier, just directly expose these two data members
* in official release, it should be done like this.*/
class YesNumberInGroup {
    int yesSum;
    String chars;
}

public class DaySix {

    /* pay attention to the repeated characters in a group */
    static int countYesInEachGroup(String chars) {
        boolean[] exist = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < chars.length(); i++) {
            exist[chars.charAt(i)] = true;
        }
        int count = 0;
        for (int i = 0; i < exist.length; i++) {
            if (exist[i]){
                count++;
            }
        }
        return count;
    }

    static ArrayList<YesNumberInGroup> getAllGrpYes(String fileName) {
        ArrayList<YesNumberInGroup> groups = new ArrayList<YesNumberInGroup>();
        YesNumberInGroup yesNum = null;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();

            while (line != null) {
                //blank line
                if (line.trim().length() == 0) {
                    yesNum = null;
                } else {
                    if (yesNum == null) {
                        yesNum = new YesNumberInGroup();
                        groups.add(yesNum);
                    }
                    if (yesNum.chars == null) {
                        yesNum.chars = new String();
                    }
                    yesNum.chars = yesNum.chars.concat(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groups;
    }

    static int sumYesAnswer(ArrayList<YesNumberInGroup> list) {
        int count = 0;
        for (YesNumberInGroup i : list) {
            i.yesSum = countYesInEachGroup(i.chars);
            count += i.yesSum;
        }
        return count;
    }

    public static void main(String [] args) {
        ArrayList<YesNumberInGroup> groups = getAllGrpYes("answer.txt");
        int count = sumYesAnswer(groups);
        System.out.println("There are " + count + " yes answers totally");
    }
}
