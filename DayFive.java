import java.util.Arrays;

public class DayFive {
    static int rowCharNum = 7;
    static int columnCharNum = 3;

    /* the first 7 characters represents the row information */
    static int[] getRowNum(String line) {
        int rows = (int)Math.pow(2, rowCharNum);
        int [] rowNum = new int[rows];

        /* initialize this array */
        for (int i = 0; i < rows; i++) {
            rowNum[i] = i;
        }

        int [] rowNumCopy = rowNum;

        for (int i = 0; i < rowCharNum; i++) {
            char ch = line.charAt(i);
            int arrLength = (int)Math.pow(2, rowCharNum - i - 1);
            int [] newArr = new int[arrLength];
            if (ch == 'B') {
                newArr = Arrays.copyOfRange(rowNumCopy, rowNumCopy.length/2, rowNumCopy.length);
            }
            else if (ch == 'F') {
                newArr = Arrays.copyOfRange(rowNumCopy, 0, rowNumCopy.length/2);
            }
            rowNumCopy = newArr;
        }
        return rowNumCopy;  //It is definitely one element array
    }

    static int[] getColumnNum(String line) {
        int columns = (int)Math.pow(2, columnCharNum);
        int [] columnNum = new int[columns];

        /* initialize this array */
        for (int i = 0; i < columns; i++) {
            columnNum[i] = i;
        }

        int [] columnNumCopy = columnNum;

        for (int i = 0; i < columnCharNum; i++) {
            char ch = line.charAt(i + rowCharNum);
            int arrLength = (int)Math.pow(2, columnCharNum - i - 1);
            int [] newArr = new int[arrLength];
            if (ch == 'R') {
                newArr = Arrays.copyOfRange(columnNumCopy, columnNumCopy.length/2, columnNumCopy.length);
            }
            else if (ch == 'L') {
                newArr = Arrays.copyOfRange(columnNumCopy, 0, columnNumCopy.length/2);
            }
            columnNumCopy = newArr;
        }
        return columnNumCopy;  //It is definitely one element array
    }

    public static void main(String[] args) {
        String line = "BFFFBBFRRR";
        int [] row = getRowNum(line);
        int [] column = getColumnNum(line);

        System.out.println("The seat is in row : " + row[0] + " column: " + column[0]);
    }
}
