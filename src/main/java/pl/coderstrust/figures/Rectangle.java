package pl.coderstrust.figures;

public class Rectangle implements Figure {
    protected double a;
    private double b;

    protected Rectangle (double a) {
        this.a = a;
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
        if (this.a <= 0 || this.b <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        return a * b;
    }
}
