package pl.coderstrust.figures;

public class Trapezoid implements Figure {
    private double a;
    private double c;
    private double h;

    public Trapezoid(double a, double c, double h) {
        this.a = a;
        this.c = c;
        this.h = h;
        if (a <= 0 || c <= 0 || h <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        return (a + c) / 2 * h;
    }
}
