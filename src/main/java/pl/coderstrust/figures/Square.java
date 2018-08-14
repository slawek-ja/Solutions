package pl.coderstrust.figures;

public class Square extends Rectangle implements Figure {
    public Square(double a) {
        super(a, a);
        if (this.a <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }
    }

    @Override
    public double area() {
        return super.area();
    }
}
