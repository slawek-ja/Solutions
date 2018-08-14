package pl.coderstrust.figures;

public class Rectangle implements Figure {
    protected double a;
    private double b;

    public Rectangle(double a, double b) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        else if (b <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        this.a = a;
        this.b = b;
    }

    public double area() {
        return a * b;
    }
}
