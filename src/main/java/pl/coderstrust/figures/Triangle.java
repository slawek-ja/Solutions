package pl.coderstrust.figures;

public class Triangle implements Figure {
    private double a;
    private double h;

    public Triangle(double a, double h) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        else if (h <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        this.a = a;
        this.h = h;
    }

    public double area() {
        return a / 2 * h;
    }
}
