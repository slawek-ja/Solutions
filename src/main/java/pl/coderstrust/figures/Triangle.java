package pl.coderstrust.figures;

public class Triangle implements Figure {
    private double a;
    private double h;

    public Triangle() {
        this.a = 0;
        this.h = 0;
    }

    public Triangle(double a, double h) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid first value");
        }
        if (h <= 0) {
            throw new IllegalArgumentException("Invalid second value");
        }
        this.a = a;
        this.h = h;
    }

    public void setBase(double a) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid first value");
        }
        this.a = a;
    }

    public void setHeight(double h) {
        if (h <= 0) {
            throw new IllegalArgumentException("Invalid second value");
        }
        this.h = h;
    }

    public double area() {
        return a / 2 * h;
    }
}
