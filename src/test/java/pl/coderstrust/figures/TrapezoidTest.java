package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TrapezoidTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegativeFirsArg() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure trapezoid = new Trapezoid(-3, 5, 9);
    }

    @Test
    public void testForNegativeSecondArg() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure trapezoid = new Trapezoid(3, -5, 9);
    }

    @Test
    public void testForNegativeThirdArg() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure trapezoid = new Trapezoid(3, 5, -9);
    }

    @Test
    public void testForZero() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure trapezoid = new Trapezoid(3, 0, 9);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 72;
        Figure trapezoid = new Trapezoid(5, 7, 12);

        //when
        double result = trapezoid.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
