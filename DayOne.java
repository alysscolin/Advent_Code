import java.util.Arrays;

class Result {
    boolean exist;
    int elementOne;
    int elementTwo;

    public boolean isExist() {
        return exist;
    }

    public int getElementOne() {
        return elementOne;
    }

    public int getElementTwo() {
        return elementTwo;
    }
}

public class DayOne {

    static Result twoElementsCandidates(
            int arr[], int sum)
    {
        Result result = new Result();

        int leftIdx, rightIdx;

        /* Sort the elements */
        Arrays.sort(arr);

        /* Now look for the two candidates
        in the sorted array*/
        leftIdx = 0;
        rightIdx = arr.length - 1;
        while (leftIdx < rightIdx) {
            if (arr[leftIdx] + arr[rightIdx] == sum)
            {
                result.exist = true;
                result.elementOne = arr[leftIdx];
                result.elementTwo = arr[rightIdx];
                return result;
            }
            else if (arr[leftIdx] + arr[rightIdx] < sum)
                leftIdx++;
            else
                rightIdx--;
        }
        return result;
    }

    public static void main(String args[])
    {
        int arr[] = { 1721, 979, 366, 299, 675, 1456 };
        int n = 2020;

        Result result = new Result();

        result = twoElementsCandidates(arr, n);

        if (result.isExist()) {
            System.out.println("find two elements in array "
                    + "with given sum: " + result.getElementOne() + " and " + result.getElementTwo());
            System.out.println("The multiple is: " + result.getElementOne() * result.getElementTwo());
        }



        else
            System.out.println("failed to find two elements in array "
                    + "with given sum");
    }
}
