package pl.coderstrust.sieve;

public class SieveOfEratosthenes {
    private static int multipleMarker = 0;
    private static int markCurrentIndex = 0;
    public static int[] sieve(int maximumNumber) {
        if (maximumNumber <= 2){
            System.out.print(0);
        }
        //fill table with potential numbers
        int tab[] = new int[maximumNumber];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i + 1;
        }
        //erase first and last markCurrentIndex
        tab[0] = 0;
        tab[tab.length - 1] = 0;
        int lastPosIndex = 0;
        while (lastPosIndex < tab.length) {
            int multiple = findNextMultiple(tab, lastPosIndex);
            lastPosIndex = markCurrentIndex;
            markCurrentIndex += multiple;
            multiple += multiple;
            markAsMultiple(tab,multiple,lastPosIndex);
            markCurrentIndex = lastPosIndex;
        }
        //create new table and fill it with n != 0
        int result[] = new int[calculateMultiplies(tab)];
        collectResults(tab,result);
        return result;
    }
    private static int findNextMultiple(int[] searchFrom ,int lastIndex){
        int multiple = 0;
        for (int i = lastIndex; i < searchFrom.length; i++) {
            if (searchFrom[i] != 0) {
                multiple = searchFrom[i];
                markCurrentIndex++;
                return multiple;
            }
            markCurrentIndex++;
        }
        return multiple;
    }
    private static void markAsMultiple(int[] numbers, int multiples, int lastPos){
        for (int i = markCurrentIndex; i <= numbers.length; i += lastPos, multiples += lastPos) {
            if (numbers[i - 1] == multiples) {
                numbers[i - 1] = 0;
            }
        }
    }
    private static int calculateMultiplies(int[] numbers){
        int howManyNum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != multipleMarker) {
                howManyNum++;
            }
        }
        return howManyNum;
    }
    private static void collectResults(int[] pullFrom, int[] fill){
        int index = 0;
        for (int i = 0; i < pullFrom.length; i++) {
            if (pullFrom[i] != multipleMarker) {
                fill[index] = pullFrom[i];
                index++;
            }
        }
    }
}
