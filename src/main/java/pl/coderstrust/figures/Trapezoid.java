package pl.coderstrust.figures;

public class Trapezoid implements Figure {
    private double a;
    private double c;
    private double h;

    public Trapezoid(double a, double c, double h) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid first value");
        }
        if (c <= 0) {
            throw new IllegalArgumentException("Invalid second value");
        }
        if (h <= 0) {
            throw new IllegalArgumentException("Invalid third value");
        }
        this.a = a;
        this.c = c;
        this.h = h;
    }

    public double area() {
        return (a + c) / 2 * h;
    }
}
