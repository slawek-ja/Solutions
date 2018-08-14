package pl.coderstrust.figures;

public class Circle implements Figure {
    private double radius;

    public Circle(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
        this.radius = r;
    }

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}
