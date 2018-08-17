package pl.coderstrust.figures;

public class Rectangle implements Figure {
    private double height;
    private double width;

    public Rectangle() {
        this.height = 0;
        this.width = 0;
    }

    public Rectangle(double height, double width) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }
            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }
            this.height = height;
            this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double area() {
        return height * width;
    }
}
