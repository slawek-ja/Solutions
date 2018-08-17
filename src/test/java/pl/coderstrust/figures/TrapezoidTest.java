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

    @Test
    public void testForDefaultConstructorAndGetBase() {
        //when
        double expectedResult = 0;
        Trapezoid trapezoid = new Trapezoid();

        //then
        assertEquals(expectedResult, trapezoid.getBase(), 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetArm() {
        //when
        double expectedResult = 0;
        Trapezoid trapezoid = new Trapezoid();

        //then
        assertEquals(expectedResult, trapezoid.getArm(), 0.0001);
    }

    @Test
    public void testForDefaultConstructorAndGetHeight() {
        //when
        double expectedResult = 0;
        Trapezoid trapezoid = new Trapezoid();

        //then
        assertEquals(expectedResult, trapezoid.getHeight(), 0.0001);
    }

    @Test
    @Parameters({
            "9.5",
            "7",
            "18"})
    public void testForDefaultConstructorWithSetterAndGetBase(double base) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setBase(base);

        //then
        assertEquals(base, trapezoid.getBase(), 0.0001);
    }

    @Test
    @Parameters({
            "9.5",
            "7",
            "18"})
    public void testForDefaultConstructorWithSetterAndGetArm(double arm) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setArm(arm);

        //then
        assertEquals(arm, trapezoid.getArm(), 0.0001);
    }

    @Test
    @Parameters({
            "9.5",
            "7",
            "18"})
    public void testForDefaultConstructorWithSetterAndGetHeight(double height) {
        //when
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setHeight(height);

        //then
        assertEquals(height, trapezoid.getHeight(), 0.0001);
    }

    @Test
    @Parameters({
            "4.8",
            "10",
            "16"})
    public void testForParameterizedConstructorAndGetBase(double base) {
        //when
        Trapezoid trapezoid = new Trapezoid(base, 2, 3);

        //then
        assertEquals(base, trapezoid.getBase(), 0.0001);
    }

    @Test
    @Parameters({
            "4.8",
            "10",
            "16"})
    public void testForParameterizedConstructorAndGetArm(double arm) {
        //when
        Trapezoid trapezoid = new Trapezoid(1, arm, 3);

        //then
        assertEquals(arm, trapezoid.getArm(), 0.0001);
    }

    @Test
    @Parameters({
            "4.8",
            "10",
            "16"})
    public void testForParameterizedConstructorAndGetHeight(double height) {
        //when
        Trapezoid trapezoid = new Trapezoid(1, 2, height);

        //then
        assertEquals(height, trapezoid.getHeight(), 0.0001);
    }
}
