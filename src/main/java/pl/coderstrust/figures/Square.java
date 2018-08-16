package pl.coderstrust.figures;

public class Square extends Rectangle implements Figure {

    public Square() {
        super();
    }

    public Square(double height) {
        super(height, height);
    }

    @Override
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void setWidth(double width) {
        throw new UnsupportedOperationException("Square can only be initialised by setHeight(height)");
    }
}
