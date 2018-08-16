package pl.coderstrust.figures;

public class Rectangle implements Figure {
    private double a;
    private double b;

    public Rectangle() {
        this.a = 0;
        this.b = 0;
    }

    public Rectangle(double a, double b) {
            if (a <= 0) {
                throw new IllegalArgumentException("Invalid first value");
            }
            if (b <= 0) {
                throw new IllegalArgumentException("Invalid second value");
            }
            this.a = a;
            this.b = b;
    }

    public void setHeight(double a) {
        if (a <= 0) {
            throw new IllegalArgumentException("Invalid first value");
        }
        this.a = a;
    }

    public void setWidth(double b) {
        if (b <= 0) {
            throw new IllegalArgumentException("Invalid second value");
        }
        this.b = b;
    }

    public double area() {
        return a * b;
    }
}
