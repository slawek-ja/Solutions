package pl.coderstrust.figures;

public class Triangle implements Figure {
    private double a;
    private double h;

    public Triangle(double a, double h) {
        this.a = a;
        this.h = h;
        if (a <= 0 || h <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        return a / 2 * h;
    }
}
