package pl.coderstrust.sieve;

public class SieveOfEratosthenes {
    public static int[] sieve(int maximumNumber){
        int[] table = fillTable(maximumNumber);
        for (int i = 2; i*i < table.length; i++){
            if(table[i] != 1){
                for (int j = i*i; j < table.length; j+=i){
                    table[j] = 0;
                }
            }
        }
        int[] result = returnResult(table);
        return result;
    }
    private static int[] fillTable(int num){
        int[] fillWithNumbers = new int[num];
        for (int i = 0;i < fillWithNumbers.length; i++){
            fillWithNumbers[i] = i;
        }
        return fillWithNumbers;
    }
    private static int[] returnResult(int[] from){
        from[1] = 0;
        int countNum = 0;
        for (int i : from) {
            if(i != 0){
                countNum++;
            }
        }
        int[] result = new int[countNum];
        int index = 0;
        for (int i = 0; i < from.length; i++){
            if(from[i] != 0){
                result[index] = from[i];
                index++;
            }
        }
            return result;
    }
}
