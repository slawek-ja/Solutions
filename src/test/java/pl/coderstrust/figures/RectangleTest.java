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

public class RectangleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters({
            "0"})
    public void testForDefaultConstructor(double expected) {
        Figure rectangle = new Rectangle();
        assertThat(rectangle.area(), is(expected));
    }

    @Test
    @Parameters({
            "2, 4, 8",
            "1, 7, 7",
            "3, 3, 9"})
    public void testForDefaultConstructorWithSetter(double a , double b, double expected) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(a);
        rectangle.setWidth(b);
        double result = rectangle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 4, Invalid first value",
            "2, 0, Invalid second value",
            "-8, 3, Invalid first value",
            "7, -2, Invalid second value"})
    public void testForDefaultConstructorWithSetterInvalidArguments(double a , double b, String expected) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(expected);
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(a);
        rectangle.setWidth(b);
    }

    @Test
    @Parameters({
            "2, 5, 10",
            "7, 3, 21",
            "3, 4, 12" })
    public void testForPositiveArgumentsNoSetter(double a , double b, double expected) {
        Figure rectangle = new Rectangle(a, b);
        double result = rectangle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "1, 1, 12",
            "2, 2, 12",
            "3, 3, 12"})
    public void testForPositiveArgumentsWithSetter(double a , double b, double expected) {
        Rectangle rectangle = new Rectangle(a, b);
        rectangle.setHeight(4);
        rectangle.setWidth(3);
        double result = rectangle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 5, Invalid first value",
            "3, 0, Invalid second value",
            "-5, 8, Invalid first value",
            "5, -8, Invalid second value"
    })
    public void testForInvalidArguments(double a , double b, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        new Rectangle(a, b);
    }

    @Test
    @Parameters({
            "3, 0, Invalid second value",
            "0, 6, Invalid first value",
            "-1, 6, Invalid first value",
            "6, -3, Invalid second value"
    })
    public void testForInvalidArgumentsWithSetter(double a , double b, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        Rectangle rectangle = new Rectangle(3, 6);
        rectangle.setHeight(a);
        rectangle.setWidth(b);
    }
}
