package pl.coderstrust.figures;

public class Square extends Rectangle implements Figure {
    public Square(double a) {
        super(a);
        if (this.a <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        return this.a * this.a;
    }
}
