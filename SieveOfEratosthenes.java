package pl.coderstrust.sieve;

public class SieveOfEratosthenes {
    public static int[] sieve(int maximumNumber) {
        if (maximumNumber <= 2) System.out.print(0);
        int tab[] = new int[maximumNumber];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i + 1;
        }
        tab[0] = 0;
        tab[tab.length - 1] = 0;
        int index = 0;
        int multiple = 0;
        int lastPosIndex = 0;
        while (lastPosIndex < tab.length) {
            for (int i = lastPosIndex; i < tab.length; i++) {
                if (tab[i] != 0) {
                    multiple = tab[i];
                    index++;
                    break;
                }
                index++;
            }
            lastPosIndex = index;
            index += multiple;
            multiple += multiple;
            for (int i = index; i <= tab.length; i += lastPosIndex, multiple += lastPosIndex) {
                if (tab[i - 1] == multiple) {
                    tab[i - 1] = 0;
                }
            }
            index = lastPosIndex;
        }
        int howManyNum = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != 0) {
                howManyNum++;
            }
        }
        int result[] = new int[howManyNum];
        index = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != 0) {
                result[index] = tab[i];
                index++;
            }
        }
        return result;
    }
}
