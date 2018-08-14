package pl.coderstrust.figures;

import java.util.concurrent.ExecutionException;

public class Rectangle implements Figure {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        try {
            if (a <= 0) {
                throw new IllegalArgumentException("Invalid first value");
            }
            if (b <= 0) {
                throw new IllegalArgumentException("Invalid second value");
            }
            this.a = a;
            this.b = b;
        } catch (Exception e) {
               throw new IllegalArgumentException("Invalid value");
        }
    }

    public double area() {
        return a * b;
    }
}
