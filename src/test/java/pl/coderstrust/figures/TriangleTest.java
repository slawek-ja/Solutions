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

public class TriangleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters({
            "0"})
    public void testForDefaultConstructor(double expected) {
        Figure triangle = new Triangle();
        assertThat(triangle.area(), is(expected));
    }

    @Test
    @Parameters({
            "2, 7, 7",
            "1, 3, 1.5",
            "9, 5, 22.5"})
    public void testForDefaultConstructorWithSetter(double a, double h, double expected) {
        Triangle triangle = new Triangle();
        triangle.setBase(a);
        triangle.setHeight(h);
        double result = triangle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 4, Invalid first value",
            "2, 0, Invalid second value",
            "-3, 7, Invalid first value",
            "3, -5, Invalid second value"})
    public void testForDefaultConstructorWithSetterInvalidArguments(double a, double h, String expected) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(expected);
        Triangle triangle = new Triangle();
        triangle.setBase(a);
        triangle.setHeight(h);
    }

    @Test
    @Parameters({
            "3, 5, 7.5",
            "2, 7, 7",
            "7, 3, 10.5" })
    public void testForPositiveArgumentsNoSetter(double a, double h, double expected) {
        Figure triangle = new Triangle(a, h);
        double result = triangle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "1, 1, 18",
            "2, 2, 18",
            "3, 3, 18"})
    public void testForPositiveArgumentsWithSetter(double a, double h, double expected) {
        Triangle triangle = new Triangle(a, h);
        triangle.setBase(4);
        triangle.setHeight(9);
        double result = triangle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 8, Invalid first value",
            "2, 0, Invalid second value",
            "-6, 7, Invalid first value",
            "2, -7, Invalid second value"})
    public void testForInvalidArguments(double a , double h, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        new Triangle(a, h);
    }

    @Test
    @Parameters({
            "0, 3, Invalid first value",
            "1, 0, Invalid second value",
            "-6, 7, Invalid first value",
            "4, -7, Invalid second value"})
    public void testForInvalidArgumentsWithSetter(double a , double h, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        Triangle triangle = new Triangle(1, 2);
        triangle.setBase(a);
        triangle.setHeight(h);
    }
}
