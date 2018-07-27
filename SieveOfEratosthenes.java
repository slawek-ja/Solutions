package pl.coderstrust.sieve;

public class SieveOfEratosthenes {
    private static int multipleMarker = 0;
    private static int index = 0; //needed to usage in methods
    public static int[] sieve(int maximumNumber) {
        if (maximumNumber <= 2){
            System.out.print(0);
        }
        int tab[] = new int[maximumNumber];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i + 1;
        }
        //erase first and last index
        tab[0] = 0;
        tab[tab.length - 1] = 0;
        int lastPosIndex = 0;
        while (lastPosIndex < tab.length) {
            //find next multiple to search
            int multiple = findNextMultiple(tab,lastPosIndex);
            lastPosIndex = index;
            //jump to next index by += index and multiply result to prevent erase form array
            index += multiple;
            multiple += multiple;
            //mark multiples and erase them by using last index
            markAsMultiple(tab,multiple,lastPosIndex);
            index = lastPosIndex;
        }
        int howManyNum = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != multipleMarker) {
                howManyNum++;
            }
        }
        //create new table and fill it with n != 0
        int result[] = new int[howManyNum];
        collectResults(tab,result);
        return result;
    }
    public static int findNextMultiple(int[] searchFrom ,int lastIndex){
        int multiple = 0;
        for (int i = lastIndex; i < searchFrom.length; i++) {
            if (searchFrom[i] != 0) {
                multiple = searchFrom[i];
                index++;
                return multiple;
            }
            index++;
        }
        return multiple;
    }
    public static void markAsMultiple(int[] numbers, int multiples, int lastPos){
        for (int i = index; i <= numbers.length; i += lastPos, multiples += lastPos) {
            if (numbers[i - 1] == multiples) {
                numbers[i - 1] = 0;
            }
        }
    }
    public static void collectResults(int[] pullFrom, int[] fill){
        int index = 0;
        for (int i = 0; i < pullFrom.length; i++) {
            if (pullFrom[i] != multipleMarker) {
                fill[index] = pullFrom[i];
                index++;
            }
        }
    }
}
