package pl.coderstrust.figures;

public class Trapezoid implements Figure {
    private double a;
    private double c;
    private double h;

    public Trapezoid(double a, double c, double h) {
        this.a = a;
        this.c = c;
        this.h = h;
    }

    public double calculateArea() {
        if (a <= 0 || c <= 0 || h <= 0) {
            return -1;
        }
        return (a + c) / 2 * h;
    }
}
