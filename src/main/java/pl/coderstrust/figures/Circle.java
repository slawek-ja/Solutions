package pl.coderstrust.figures;

public class Circle implements Figure {
    private double radius;

    public Circle(double r) {
        this.radius = r;
    }

    public double calculateArea() {
        if (radius <= 0) {
            return -1;
        }
        return Math.PI * Math.pow(radius, 2);
    }
}
