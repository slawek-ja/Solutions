package pl.coderstrust.fibonacci;

public class FibonacciIterative {
    public static long fibonacci(int fibonacciNumberInOrder){
        long firstNum = 0;
        long secondNum = 1;
        for(int i = 0; i < fibonacciNumberInOrder; i++)
        {
            secondNum += firstNum;
            firstNum = secondNum - firstNum;
        }
        return firstNum;
    }
}
