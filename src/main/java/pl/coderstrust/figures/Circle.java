package pl.coderstrust.figures;

public class Circle implements Figure {
    private double radius;

    public Circle(double r) {
        this.radius = r;
        if (this.radius <=0) {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}
