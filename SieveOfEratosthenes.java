package pl.coderstrust.sieve;

public class SieveOfEratosthenes {
    public static int[] sieve(int maximumNumber){
        if(maximumNumber <= 2){
            return new int[0];
        }
        int[] table = initNumbers(maximumNumber);
        for (int i = 2; i*i < table.length; i++){
                for (int j = i*i; j < table.length; j+=i){
                    table[j] = 0;
                }
        }
        int[] result = collectResults(table);
        return result;
    }
    private static int[] initNumbers(int num){
        int[] result = new int[num];
        for (int i = 0; i < result.length; i++){
            result[i] = i;
        }
        return result;
    }
    private static int[] collectResults(int[] numbers){
        numbers[1] = 0;
        int numberOfPrimes = 0;
        for (int i : numbers) {
            if(i != 0){
                numberOfPrimes++;
            }
        }
        int[] result = new int[numberOfPrimes];
        int j = 0;
        for (int i = 0; i < numbers.length; i++){
            if(numbers[i] != 0){
                result[j] = numbers[i];
                j++;
            }
        }
        return result;
    }
}
