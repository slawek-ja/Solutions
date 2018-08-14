package pl.coderstrust.figures;

public class Trapezoid implements Figure {
    private double a;
    private double c;
    private double h;

    public Trapezoid(double a, double c, double h) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        else if (c <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        else if (h <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        this.a = a;
        this.c = c;
        this.h = h;
    }

    public double area() {
        return (a + c) / 2 * h;
    }
}
