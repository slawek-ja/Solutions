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
    public void testForDefaultConstructor() {
        //given
        double expectedArea = 0;

        //when
        Figure circle = new Circle();

        //then
        assertThat(circle.area(), is(expectedArea));
    }

    @Test
    @Parameters({
            "4, 50.27",
            "2, 12.57",
            "7, 153.94"})
    public void testForDefaultConstructorAndSetterWithValidArgument(double radius, double expectedArea) {
        //given
        Circle circle = new Circle();

        //when
        circle.setRadius(radius);
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0",
            "-8"})
    public void testForDefaultConstructorAndSetterWithInvalidArgument(double radius) {
        //given
        Circle circle = new Circle();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Radius must be greater than 0");

        //when
        circle.setRadius(radius);
    }

    @Test
    @Parameters({
            "12, 452.39",
            "4, 50.27",
            "6, 113.10" })
    public void testForParameterizedConstructorWithValidArgument(double radius, double expectedArea) {
        //given
        Figure circle = new Circle(radius);

        //when
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0",
            "-3"})
    public void testForParameterizedConstructorWithInvalidArgument(double radius) {
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Radius must be greater than 0");

        //when
        new Circle(radius);
    }

    @Test
    @Parameters({
            "0",
            "-9"})
    public void testForParameterizedConstructorAndSetterWithInvalidArgument(double radius) {
        //given
        Circle circle = new Circle(3);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Radius must be greater than 0");

        //when
        circle.setRadius(radius);
    }

    @Test
    @Parameters({
            "6, 113.1",
            "2, 12.57",
            "6, 113.1"})
    public void testForParameterizedConstructorAndSetterWithValidArgument(double radius, double expectedArea) {
        //given
        Circle circle = new Circle(3);

        //when
        circle.setRadius(radius);
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }
}
