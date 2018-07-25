package pl.coderstrust.sieve;

public class SieveOfEratosthenes
{
    public static int[] sieve(int maximumNumber) {
        if (maximumNumber <= 2) System.out.print(0);

        int tab[] = new int[maximumNumber - 2];
        int remainder = 2;
        int findIndex = 1;

        for (int i = 0; i < tab.length; i++) {
            tab[i] = i + 2;
        }

        while (remainder < maximumNumber) {
            for (int i = findIndex + 1; i < tab.length; i++) {
                if (tab[i] % remainder == 0) {
                    tab[i] = 0;
                }
            }
            remainder++;
            for (int i = 0; i < tab.length; i++) {
                if (tab[i] >= remainder) {
                    remainder = tab[i];
                    findIndex = i;
                    break;
                }
            }
        }
        int newTableLength = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != 0) {
                newTableLength++;
            }
        }
        int result[] = new int[newTableLength];
        findIndex = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != 0) {
                result[findIndex] = tab[i];
                findIndex++;
            }
        }
        return result;
    }
}
