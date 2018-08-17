package pl.coderstrust.figures;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
@RunWith(JUnitParamsRunner.class)

public class RectangleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAreaBasedOnDefaultConstructor() {
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
    public void testAreaBasedOnDefaultConstructorAndSetterWithValidArgument(double height , double width, double expectedArea) {
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
    public void testForDefaultConstructorAndSetterWithInvalidArgument(double height , double width, String exceptionMessage) {
        //given
        Rectangle rectangle = new Rectangle();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);

        //when
        rectangle.setHeight(height);
        rectangle.setWidth(width);
    }

    @Test
    @Parameters({
            "2, 5, 10",
            "7, 3, 21",
            "3, 4, 12" })
    public void testAreaBasedOnParameterizedConstructorWithValidArgument(double height , double width, double expectedArea) {
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
    public void testAreaBasedOnParameterizedConstructorAndSetterWithValidArgument(double height, double width, double expectedArea) {
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
    public void testForParameterizedConstructorWithInvalidArgument(double height , double width, String exceptionMessage) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        new Rectangle(height, width);
    }

    @Test
    @Parameters({
            "3, 0, Width must be greater than 0",
            "0, 6, Height must be greater than 0",
            "-1, 6, Height must be greater than 0",
            "6, -3, Width must be greater than 0"})
    public void testForParameterizedConstructorAndSetterWithInvalidArgument(double height, double width, String exceptionMessage) {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        Rectangle rectangle = new Rectangle(3, 6);

        //when
        rectangle.setHeight(height);
        rectangle.setWidth(width);
    }

    @Test
    public void testForDefaultConstructorAndGetHeight() {
        //when
        double expectedResult = 0;
        Rectangle rectangle = new Rectangle();

        //then
        assertEquals(expectedResult, rectangle.getHeight(), 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetWidth() {
        //when
        double expectedResult = 0;
        Rectangle rectangle = new Rectangle();

        //then
        assertEquals(expectedResult, rectangle.getWidth(), 0.0001);
    }

    @Test
    @Parameters({
            "3",
            "6",
            "8"})
    public void testForDefaultConstructorWithSetterAndGetHeight(double height) {
        //when
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);

        //then
        assertEquals(height, rectangle.getHeight(), 0.0001);
    }

    @Test
    @Parameters({
            "3.7",
            "2",
            "4"})
    public void testForDefaultConstructorWithSetterAndGetWidth(double width) {
        //when
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);

        //then
        assertEquals(width, rectangle.getWidth(), 0.0001);
    }

    @Test
    @Parameters({
            "6",
            "7",
            "1.8"})
    public void testForParameterizedConstructorAndGetHeight(double height) {
        //when
        Rectangle rectangle = new Rectangle(height, 3);

        //then
        assertEquals(height, rectangle.getHeight(), 0.0001);
    }

    @Test
    @Parameters({
            "6",
            "7",
            "1.8"})
    public void testForParameterizedConstructorAndGetWidth(double width) {
        //when
        Rectangle rectangle = new Rectangle(3, width);

        //then
        assertEquals(width, rectangle.getWidth(), 0.0001);
    }
}
