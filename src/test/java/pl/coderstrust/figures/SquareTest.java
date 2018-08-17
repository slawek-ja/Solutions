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
    public void testAreaBasedOnDefaultConstructor() {
        //when
        double expectedArea = 0;
        Square square = new Square();

        //then
        assertThat(square.area(), is(expectedArea));
    }

    @Test
    @Parameters({
            "4, 16",
            "2, 4",
            "7, 49"})
    public void testAreaBasedOnDefaultConstructorAndSetterWithValidArgument(double height, double expectedArea) {
        //when
        Square square = new Square();
        square.setHeight(height);
        double result = square.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "4",
            "0",
            "-7"})
    public void testForDefaultConstructorAndInvalidSetter(double width) {
        //when
        Square square = new Square();
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Square can only be initialised by setHeight(height)");

        //then
        square.setWidth(width);
    }

    @Test
    @Parameters({
            "3",
            "6",
            "5"})
    public void testForParameterizedConstructorAndInvalidSetter(double height) {
        //when
        Square square = new Square(height);
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Square can only be initialised by setHeight(height)");

        //then
        square.setWidth(3);
    }

    @Test
    @Parameters({
            "0",
            "-8"})
    public void testForDefaultConstructorAndSetterWithInvalidArgument(double height) {
        //given
        Square square = new Square();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Height must be greater than 0");

        //when
        square.setHeight(height);
    }

    @Test
    @Parameters({
            "2, 4",
            "4, 16",
            "6, 36" })
    public void testAreaBasedOnParameterizedConstructorWithValidArgument(double height, double expectedArea) {
        //when
        Figure square = new Square(height);
        double result = square.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "4, 16",
            "2, 4",
            "3, 9"})
    public void testAreaBasedOnParameterizedConstructorAndSetterWithValidArgument(double height, double expectedArea) {
        //when
        Square square = new Square(4);
        square.setHeight(height);
        double result = square.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0",
            "-3"})
    public void testForParameterizedConstructorWithInvalidArgument(double height) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Height must be greater than 0");
        new Square(height);
    }

    @Test
    @Parameters({
            "0",
            "-9"})
    public void testForParameterizedConstructorAndSetterWithInvalidArgument(double height) {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Height must be greater than 0");
        Square square = new Square(3);

        //when
        square.setHeight(height);
    }

    @Test
    public void testForDefaultConstructorAndGetterOfHeight() {
        //when
        double expectedResult = 0;
        Square square = new Square();
        double height = square.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetterOfWidth() {
        //given
        Square square = new Square();
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Square parameters can only be accessed by getHeight()");

        //when
        square.getWidth();
    }

    @Test
    @Parameters({
            "3",
            "7.2",
            "5"})
    public void testForDefaultConstructorWithSetterAndGetterOfHeight(double expectedResult) {
        //when
        Square square = new Square();
        square.setHeight(expectedResult);
        double height = square.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }

    @Test
    @Parameters({
            "3",
            "7",
            "9"})
    public void testForParameterizedConstructorAndGetterOfHeight(double expectedResult) {
        //when
        Square square = new Square(expectedResult);
        double height = square.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }
}
