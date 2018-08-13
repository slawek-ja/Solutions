package pl.coderstrust.figures;

public class Circle implements Figure {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    public double calculateArea() {
        if (r <= 0) {
            return -1;
        }
        return Math.PI * Math.pow(r, 2);
    }
}
