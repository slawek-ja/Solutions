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
    public void testForDefaultConstructor() {
        //when
        double expectedArea = 0;
        Figure trapezoid = new Trapezoid();

        //then
        assertThat(trapezoid.area(), is(expectedArea));
    }

    @Test
    @Parameters({
            "2, 5, 7, 24.50",
            "1, 3, 5, 10",
            "9, 5, 2, 14"})
    public void testAreaBasedOnDefaultConstructorAndSetterWithValidArgument(double base, double arm, double height, double expectedArea) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setBase(base);
        trapezoid.setArm(arm);
        trapezoid.setHeight(height);
        double result = trapezoid.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 4, 7, Base must be greater than 0",
            "2, 0, 3, Arm must be greater than 0",
            "8, 3, 0, Height must be greater than 0",
            "-3, 4, 7, Base must be greater than 0",
            "2, -9, 3, Arm must be greater than 0",
            "8, 3, -5, Height must be greater than 0"})
    public void testForDefaultConstructorAndSetterWithInvalidArgument(double base, double arm, double height, String exceptionMessage) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);

        //then
        trapezoid.setBase(base);
        trapezoid.setArm(arm);
        trapezoid.setHeight(height);
    }

    @Test
    @Parameters({
            "3, 5, 3, 12",
            "2, 7, 5, 22.50",
            "7, 3, 1, 5" })
    public void testAreaBasedOnParameterizedConstructorWithValidArgument(double base, double arm, double height, double expectedArea) {
        //when
        Figure trapezoid = new Trapezoid(base, arm, height);
        double result = trapezoid.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "4, 6, 3, 15",
            "2, 5, 1, 3.5",
            "8, 2, 10, 50"})
    public void testAreaBasedOnParameterizedConstructorAndSetterWithValidArgument(double base, double arm, double height, double expectedArea) {
        //when
        Trapezoid trapezoid = new Trapezoid(3, 7, 2);
        trapezoid.setBase(base);
        trapezoid.setArm(arm);
        trapezoid.setHeight(height);
        double result = trapezoid.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expectedArea, result, 0.0001);
    }

    @Test
    @Parameters({
            "0, 5, 7, Base must be greater than 0",
            "3, 0, 3, Arm must be greater than 0",
            "3, 6, 0, Height must be greater than 0",
            "-6, 5, 7, Base must be greater than 0",
            "3, -2, 7, Arm must be greater than 0",
            "2, 5, -7, Height must be greater than 0"})
    public void testForParameterizedConstructorWithInvalidArgument(double base, double arm, double height, String exceptionMessage) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        new Trapezoid(base, arm, height);
    }

    @Test
    @Parameters({
            "0, 5, 7, Base must be greater than 0",
            "3, 0, 3, Arm must be greater than 0",
            "3, 6, 0, Height must be greater than 0",
            "-6, 5, 7, Base must be greater than 0",
            "3, -2, 7, Arm must be greater than 0",
            "2, 5, -7, Height must be greater than 0"})
    public void testForParameterizedConstructorAndSetterWithInvalidArgument(double base, double arm, double height, String exceptionMessage) {
        //given
        Trapezoid trapezoid = new Trapezoid(1,2,3);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);

        //when
        trapezoid.setBase(base);
        trapezoid.setArm(arm);
        trapezoid.setHeight(height);
    }

    @Test//tu zaczac
    public void testForDefaultConstructorAndGetterOfBase() {
        //when
        double expectedResult = 0;
        Trapezoid trapezoid = new Trapezoid();
        double base = trapezoid.getBase();

        //then
        assertEquals(expectedResult, base, 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetterOfArm() {
        //when
        double expectedResult = 0;
        Trapezoid trapezoid = new Trapezoid();
        double arm = trapezoid.getArm();

        //then
        assertEquals(expectedResult, arm, 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetterOfHeight() {
        //when
        double expectedResult = 0;
        Trapezoid trapezoid = new Trapezoid();
        double height = trapezoid.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }

    @Test
    @Parameters({
            "9.5",
            "7",
            "18"})
    public void testForDefaultConstructorWithSetterAndGetterOfBase(double expectedResult) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setBase(expectedResult);
        double base = trapezoid.getBase();

        //then
        assertEquals(expectedResult, base, 0.0001);
    }

    @Test
    @Parameters({
            "9.5",
            "7",
            "18"})
    public void testForDefaultConstructorWithSetterAndGetterOfArm(double expectedResult) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setArm(expectedResult);
        double arm = trapezoid.getArm();

        //then
        assertEquals(expectedResult, arm, 0.0001);
    }

    @Test
    @Parameters({
            "9.5",
            "7",
            "18"})
    public void testForDefaultConstructorWithSetterAndGetterOfHeight(double expectedResult) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setHeight(expectedResult);
        double height = trapezoid.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }

    @Test
    @Parameters({
            "4.8",
            "10",
            "16"})
    public void testForParameterizedConstructorAndGetterOfBase(double expectedResult) {
        //when
        Trapezoid trapezoid = new Trapezoid(expectedResult, 2, 3);
        double base = trapezoid.getBase();

        //then
        assertEquals(expectedResult, base, 0.0001);
    }

    @Test
    @Parameters({
            "4.8",
            "10",
            "16"})
    public void testForParameterizedConstructorAndGetterOfArm(double expectedResult) {
        //when
        Trapezoid trapezoid = new Trapezoid(1, expectedResult, 3);
        double arm = trapezoid.getArm();

        //then
        assertEquals(expectedResult, arm, 0.0001);
    }

    @Test
    @Parameters({
            "4.8",
            "10",
            "16"})
    public void testForParameterizedConstructorAndGetterOfHeight(double expectedResult) {
        //when
        Trapezoid trapezoid = new Trapezoid(1, 2, expectedResult);
        double height= trapezoid.getHeight();

        //then
        assertEquals(expectedResult, height, 0.0001);
    }
}
