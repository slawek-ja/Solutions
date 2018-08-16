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

public class TrapezoidTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters({
            "0"})
    public void testForDefaultConstructor(double expected) {
        Figure trapezoid = new Trapezoid();
        assertThat(trapezoid.area(), is(expected));
    }

    @Test
    @Parameters({
            "2, 5, 7, 24.50",
            "1, 3, 5, 10",
            "9, 5, 2, 14"})
    public void testForDefaultConstructorWithSetter(double a, double c, double h, double expected) {
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setBase(a);
        trapezoid.setArm(c);
        trapezoid.setHeight(h);
        double result = trapezoid.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 4, 7, Invalid first value",
            "2, 0, 3, Invalid second value",
            "8, 3, 0, Invalid third value",
            "-3, 4, 7, Invalid first value",
            "2, -9, 3, Invalid second value",
            "8, 3, -5, Invalid third value"})
    public void testForDefaultConstructorWithSetterInvalidArguments(double a, double c, double h, String expected) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(expected);
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setBase(a);
        trapezoid.setArm(c);
        trapezoid.setHeight(h);
    }

    @Test
    @Parameters({
            "3, 5, 3, 12",
            "2, 7, 5, 22.50",
            "7, 3, 1, 5" })
    public void testForPositiveArgumentsNoSetter(double a, double c, double h, double expected) {
        Figure trapezoid = new Trapezoid(a, c, h);
        double result = trapezoid.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "1, 1, 1, 31.50",
            "2, 2, 2, 31.50",
            "3, 3, 3, 31.50"})
    public void testForPositiveArgumentsWithSetter(double a, double c, double h, double expected) {
        Trapezoid trapezoid = new Trapezoid(a, c, h);
        trapezoid.setBase(4);
        trapezoid.setArm(5);
        trapezoid.setHeight(7);
        double result = trapezoid.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 5, 7, Invalid first value",
            "3, 0, 3, Invalid second value",
            "3, 6, 0, Invalid third value",
            "-6, 5, 7, Invalid first value",
            "3, -2, 7, Invalid second value",
            "2, 5, -7, Invalid third value"})
    public void testForInvalidArguments(double a , double c, double h, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        new Trapezoid(a, c, h);
    }

    @Test
    @Parameters({
            "0, 5, 7, Invalid first value",
            "3, 0, 3, Invalid second value",
            "3, 6, 0, Invalid third value",
            "-6, 5, 7, Invalid first value",
            "3, -2, 7, Invalid second value",
            "2, 5, -7, Invalid third value"})
    public void testForInvalidArgumentsWithSetter(double a , double c, double h, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        Trapezoid trapezoid = new Trapezoid(1,2,3);
        trapezoid.setBase(a);
        trapezoid.setArm(c);
        trapezoid.setHeight(h);
    }
}
