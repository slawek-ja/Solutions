package pl.coderstrust.figures;

public class Square extends Rectangle implements Figure {

    public Square() {
        super();
    }

    public Square(double a) {
        super(a, a);
    }

    @Override
    public void setHeight(double a) {
        super.setHeight(a);
        super.setWidth(a);
    }

    @Override
    public void setWidth(double b) {
        throw new IllegalArgumentException("Square can only be initialised by setHeight(a)");
    }
}
