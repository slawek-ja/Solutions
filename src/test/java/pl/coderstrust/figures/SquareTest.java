package pl.coderstrust.figures;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)

public class SquareTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters({
            "0"})
    public void testForDefaultConstructor(double expected) {
        Square square = new Square();
        assertThat(square.area(), is(expected));
    }

    @Test
    @Parameters({
            "4, 16",
            "2, 4",
            "7, 49"})
    public void testForDefaultConstructorWithSetter(double a, double expected) {
        Square square = new Square();
        square.setHeight(a);
        double result = square.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "4",
            "0",
            "-7"})
    public void testForDefaultConstructorWithSetterSetWidthIllegal(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Square can only be initialised by setHeight(a)");
        Square square = new Square();
        square.setWidth(a);
    }

    @Test
    @Parameters({
            "0",
            "-8"})
    public void testForDefaultConstructorWithSetterInvalidArgument(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Square square = new Square();
        square.setHeight(a);
    }

    @Test
    @Parameters({
            "2, 4",
            "4, 16",
            "6, 36" })
    public void testForPositiveArgumentsNoSetter(double a, double expected) {
        Figure square = new Square(a);
        double result = square.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "1, 25",
            "2, 25",
            "3, 25"})
    public void testForPositiveArgumentsWithSetter(double a, double expected) {
        Square square = new Square(a);
        square.setHeight(5);
        double result = square.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0",
            "-3"})
    public void testForInvalidArguments(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        new Square(a);
    }

    @Test
    @Parameters({
            "0",
            "-9"})
    public void testForInvalidArgumentsWithSetter(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Square square = new Square(3);
        square.setHeight(a);
    }
}
