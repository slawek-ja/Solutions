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
        //when
        double expectedArea = 0;
        Figure circle = new Circle();

        //then
        assertThat(circle.area(), is(expectedArea));
    }

    @Test
    @Parameters({
            "4, 50.27",
            "2, 12.57",
            "7, 153.94"})
    public void testAreaBasedOnDefaultConstructorAndSetterWithValidArgument(double radius, double expectedArea) {
        //when
        Circle circle = new Circle();
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
    public void testAreaBasedOnParameterizedConstructorWithValidArgument(double radius, double expectedArea) {
        //when
        Figure circle = new Circle(radius);
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
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Radius must be greater than 0");
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
    public void testAreaBasedOnParameterizedConstructorAndSetterWithValidArgument(double radius, double expectedArea) {
        //when
        Circle circle = new Circle(3);
        circle.setRadius(radius);
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetter() {
        //when
        double expectedResult = 0;
        Circle circle = new Circle();
        double radius = circle.getRadius();

        //then
        assertEquals(expectedResult, radius, 0.0001);
    }

    @Test
    @Parameters({
            "1.5",
            "2",
            "6"})
    public void testForDefaultConstructorWithSetterAndGetterOfRadius(double expectedResult) {
        //when
        Circle circle = new Circle();
        circle.setRadius(expectedResult);
        double radius = circle.getRadius();

        //then
        assertEquals(expectedResult, radius, 0.0001);
    }

    @Test
    @Parameters({
            "6",
            "7",
            "18"})
    public void testForParameterizedConstructorAndGetterOfRadius(double expectedResult) {
        //when
        Circle circle = new Circle(expectedResult);
        double radius = circle.getRadius();

        //then
        assertEquals(expectedResult, radius, 0.0001);
    }
}
