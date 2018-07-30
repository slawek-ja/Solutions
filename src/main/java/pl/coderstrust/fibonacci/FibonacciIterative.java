package pl.coderstrust.fibonacci;

public class FibonacciIterative {
    public static long fibonacci(int fibonacciNumberInOrder){
        long a = 0;
        long b = 1;

        for(int i=0;i<fibonacciNumberInOrder;i++)
        {
            b += a;
            a = b - a;
        }
        return a;
    }
}
