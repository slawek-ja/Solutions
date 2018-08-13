package pl.coderstrust.figures;

public class Triangle implements Figure {
    private double a;
    private double h;

    public Triangle(double a, double h) {
        this.a = a;
        this.h = h;
    }

    public double calculateArea() {
        if (a <= 0 || h <= 0) {
            return -1;
        }
        return a / 2 * h;
    }
}
