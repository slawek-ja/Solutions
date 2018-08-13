package pl.coderstrust.figures;

public class Square implements Figure {
    protected double a;

    public Square(double a) {
        this.a = a;
    }

    public double calculateArea() {
        if (a <= 0) {
            return -1;
        }
        return a * a;
    }
}
