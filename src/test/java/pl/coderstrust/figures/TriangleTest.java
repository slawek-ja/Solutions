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
    public void testForDefaultConstructor() {
        //when
        double expectedArea = 0;
        Figure triangle = new Triangle();

        //then
        assertThat(triangle.area(), is(expectedArea));
    }

    @Test
    @Parameters({
            "2, 7, 7",
            "1, 3, 1.5",
            "9, 5, 22.5"})
    public void testAreaBasedOnDefaultConstructorAndSetterWithValidArgument(double base, double height, double expectedArea) {
        //when
        Triangle triangle = new Triangle();
        triangle.setBase(base);
        triangle.setHeight(height);
        double result = triangle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 4, Base must be greater than 0",
            "2, 0, Height must be greater than 0",
            "-3, 7, Base must be greater than 0",
            "3, -5, Height must be greater than 0"})
    public void testForDefaultConstructorAndSetterWithInvalidArgument(double base, double height, String exceptionMessage) {
        //when
        Triangle triangle = new Triangle();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);

        //then
        triangle.setBase(base);
        triangle.setHeight(height);
    }

    @Test
    @Parameters({
            "3, 5, 7.5",
            "2, 7, 7",
            "7, 3, 10.5" })
    public void testAreaBasedOnParameterizedConstructorWithValidArgument(double base, double height, double expectedArea) {
        //when
        Figure triangle = new Triangle(base, height);
        double result = triangle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "4, 12, 24",
            "3, 6, 9",
            "6, 8, 24"})
    public void testAreaBasedOnParameterizedConstructorAndSetterWithValidArgument(double base, double height, double expectedArea) {
        //when
        Triangle triangle = new Triangle(4, 3);
        triangle.setBase(base);
        triangle.setHeight(height);
        double result = triangle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 8, Base must be greater than 0",
            "2, 0, Height must be greater than 0",
            "-6, 7, Base must be greater than 0",
            "2, -7, Height must be greater than 0"})
    public void testForParameterizedConstructorWithInvalidArgument(double base, double height, String exceptionMessage) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        new Triangle(base, height);
    }

    @Test
    @Parameters({
            "0, 3, Base must be greater than 0",
            "1, 0, Height must be greater than 0",
            "-6, 7, Base must be greater than 0",
            "4, -7, Height must be greater than 0"})
    public void testForParameterizedConstructorAndSetterWithInvalidArgument(double base, double height, String exceptionMessage) {
        //given
        Triangle triangle = new Triangle(1, 2);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);

        //when
        triangle.setBase(base);
        triangle.setHeight(height);
    }

    @Test
    public void testForDefaultConstructorAndGetterOfBase() {
        //when
        double expectedResult = 0;
        Triangle triangle = new Triangle();
        double base = triangle.getBase();

        //then
        assertEquals(expectedResult, base, 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetterOfHeight() {
        //when
        double expectedResult = 0;
        Triangle triangle = new Triangle();
        double height = triangle.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }

    @Test
    @Parameters({
            "3.5",
            "7",
            "2"})
    public void testForDefaultConstructorWithSetterAndGetterOfBase(double expectedResult) {
        //when
        Triangle triangle = new Triangle();
        triangle.setBase(expectedResult);
        double base = triangle.getBase();

        //then
        assertEquals(expectedResult, base, 0.0001);
    }

    @Test
    @Parameters({
            "3.5",
            "7",
            "2"})
    public void testForDefaultConstructorWithSetterAndGetterOfHeight(double expectedResult) {
        //when
        Triangle triangle = new Triangle();
        triangle.setHeight(expectedResult);
        double height = triangle.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }

    @Test
    @Parameters({
            "6",
            "7",
            "18"})
    public void testForParameterizedConstructorAndGetterOfBase(double expectedResult) {
        //when
        Triangle triangle = new Triangle(expectedResult, 3);
        double base = triangle.getBase();

        //then
        assertEquals(expectedResult, base, 0.0001);
    }

    @Test
    @Parameters({
            "6",
            "7",
            "18"})
    public void testForParameterizedConstructorAndGetterOfHeight(double expectedResult) {
        //when
        Triangle triangle = new Triangle(3, expectedResult);
        double height = triangle.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }
}
