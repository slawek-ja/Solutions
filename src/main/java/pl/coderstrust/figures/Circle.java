package pl.coderstrust.figures;

public class Circle implements Figure {
    private double radius;

    public Circle() {
        this.radius = 0;
    }

    public Circle(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.radius = r;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.radius = radius;
    }

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}
