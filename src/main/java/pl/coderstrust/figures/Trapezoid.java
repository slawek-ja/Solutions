package pl.coderstrust.figures;

public class Trapezoid implements Figure {
    private double base;
    private double arm;
    private double height;

    public Trapezoid() {
        this.base = 0;
        this.arm = 0;
        this.height = 0;
    }

    public Trapezoid(double base, double arm, double height) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0");
        }
        if (arm <= 0) {
            throw new IllegalArgumentException("Arm must be greater than 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.base = base;
        this.arm = arm;
        this.height = height;
    }

    public void setBase(double base) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0");
        }
        this.base = base;
    }

    public void setArm(double arm) {
        if (arm <= 0) {
            throw new IllegalArgumentException("Arm must be greater than 0");
        }
        this.arm = arm;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    public double area() {
        return (base + arm) / 2 * height;
    }
}
