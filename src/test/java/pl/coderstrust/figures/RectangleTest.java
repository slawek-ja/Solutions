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
    public void testForDefaultConstructor() {
        //when
        double expectedArea = 0;
        Figure rectangle = new Rectangle();

        //then
        assertThat(rectangle.area(), is(expectedArea));
    }

    @Test
    @Parameters({
            "2, 4, 8",
            "1, 7, 7",
            "3, 3, 9"})
    public void testForDefaultConstructorAndSetterWithValidArgument(double height , double width, double expectedArea) {
        //when
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        double result = rectangle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 4, Height must be greater than 0",
            "2, 0, Width must be greater than 0",
            "-8, 3, Height must be greater than 0",
            "7, -2, Width must be greater than 0"})
    public void testForDefaultConstructorAndSetterWithInvalidArgument(double height , double width, String expectedArea) {
        //given
        Rectangle rectangle = new Rectangle();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(expectedArea);

        //when
        rectangle.setHeight(height);
        rectangle.setWidth(width);
    }

    @Test
    @Parameters({
            "2, 5, 10",
            "7, 3, 21",
            "3, 4, 12" })
    public void testForParameterizedConstructorWithValidArgument(double height , double width, double expectedArea) {
        //when
        Figure rectangle = new Rectangle(height, width);
        double result = rectangle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "3, 7, 21",
            "2, 4, 8",
            "3, 8, 24"})
    public void testForParameterizedConstructorAndSetterWithValidArgument(double height, double width, double expectedArea) {
        //when
        Rectangle rectangle = new Rectangle(3, 6);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        double result = rectangle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 5, Height must be greater than 0",
            "3, 0, Width must be greater than 0",
            "-5, 8, Height must be greater than 0",
            "5, -8, Width must be greater than 0"})
    public void testForParameterizedConstructorWithInvalidArgument(double height , double width, String message) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        new Rectangle(height, width);
    }

    @Test
    @Parameters({
            "3, 0, Width must be greater than 0",
            "0, 6, Height must be greater than 0",
            "-1, 6, Height must be greater than 0",
            "6, -3, Width must be greater than 0"})
    public void testForParameterizedConstructorAndSetterWithInvalidArgument(double height, double width, String message) {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        Rectangle rectangle = new Rectangle(3, 6);

        //when
        rectangle.setHeight(height);
        rectangle.setWidth(width);
    }
}
