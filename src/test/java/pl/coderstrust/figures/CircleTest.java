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

public class CircleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters({
            "0"})
    public void testForDefaultConstructor(double expected) {
        Figure circle = new Circle();
        assertThat(circle.area(), is(expected));
    }

    @Test
    @Parameters({
            "4, 50.27",
            "2, 12.57",
            "7, 153.94"})
    public void testForDefaultConstructorWithSetter(double a, double expected) {
        Circle circle = new Circle();
        circle.setRadius(a);
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0",
            "-8"})
    public void testForDefaultConstructorWithSetterInvalidArgument(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value");
        Circle circle = new Circle();
        circle.setRadius(a);
    }

    @Test
    @Parameters({
            "12, 452.39",
            "4, 50.27",
            "6, 113.10" })
    public void testForPositiveArgumentsNoSetter(double a, double expected) {
        Figure circle = new Circle(a);
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "1, 201.06",
            "2, 201.06",
            "3, 201.06"})
    public void testForPositiveArgumentsWithSetter(double a, double expected) {
        Circle circle = new Circle();
        circle.setRadius(8);
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @Parameters({
            "0",
            "-3"})
    public void testForInvalidArguments(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value");
        new Circle(a);
    }

    @Test
    @Parameters({
            "0",
            "-9"})
    public void testForInvalidArgumentsWithSetter(double a) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value");
        Circle circle = new Circle(3);
        circle.setRadius(a);
    }
}
