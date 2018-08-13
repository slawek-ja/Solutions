package pl.coderstrust.figures;

public class Rectangle extends Square implements Figure {
    private int b;

    public Rectangle(double a, int b) {
        super(a);
        this.b = b;
    }

    @Override
    public double calculateArea() {
        if (a <= 0 || b <= 0) {
            return -1;
        }
        return this.a * this.b;
    }
}
