package pl.coderstrust.figures;

public class Triangle implements Figure {
    private double base;
    private double height;

    public Triangle() {
        this.base = 0;
        this.height = 0;
    }

    public Triangle(double base, double height) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.base = base;
        this.height = height;
    }

    public void setBase(double base) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0");
        }
        this.base = base;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    public double area() {
        return base / 2 * height;
    }
}
