package pl.coderstrust.fibonacci;

public class FibonacciRecursive {
    public static long fibonacci(int fibonacciNumberInOrder){
        if (fibonacciNumberInOrder < 2){
            return fibonacciNumberInOrder;
        }
        return (fibonacci (fibonacciNumberInOrder - 1) + fibonacci (fibonacciNumberInOrder - 2));
    }
}
