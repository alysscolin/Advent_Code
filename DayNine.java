import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayNine {

    final static int preambleLength = 5;

    public static ArrayList<Integer> readFile(String fileName) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        Integer integer = null;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        while (line != null) {
            integer = Integer.parseInt(line);
            list.add(integer);
            line = reader.readLine();
        }
        reader.close();
        return list;
    }

    /* find the first number which does not equal summary of two previous element before it
    * preamble length is 5 */
    public static int findFirstNumber(ArrayList<Integer> list) {
        boolean breakFlag = false;
        int value = -1;
        for (int i= preambleLength; i < list.size(); i++) {
            secondLoop:
            for (int j=0; j < i -1; j++) {
                for (int m = j + 1; m < i; m++) {
                    if ((list.get(j) + list.get(m)) == list.get(i)) {
                        breakFlag = true;
                        break secondLoop;
                    }
                }
            }
            if (!breakFlag) {
                return list.get(i);
            }
        }
        return -1;
    }

    public static  void main(String[] args)  {
        try {
            ArrayList<Integer> list = readFile("preamble.txt");

            int value = findFirstNumber(list);

            if (value == -1)
                System.out.println("This number does not exist");
            else
                System.out.println("The first value is " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
