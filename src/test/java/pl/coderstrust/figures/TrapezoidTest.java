package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TrapezoidTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegativeFirsArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Figure trapezoid = new Trapezoid(-3, 5, 9);
    }

    @Test
    public void testForNegativeSecondArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid second value");
        Figure trapezoid = new Trapezoid(3, -5, 9);
    }

    @Test
    public void testForNegativeThirdArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid third value");
        Figure trapezoid = new Trapezoid(3, 5, -9);
    }

    @Test
    public void testForZeroFirstArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Figure trapezoid = new Trapezoid(0, 3, 9);
    }

    @Test
    public void testForZeroSecondArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid second value");
        Figure trapezoid = new Trapezoid(3, 0, 9);
    }

    @Test
    public void testForZeroThirdArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid third value");
        Figure trapezoid = new Trapezoid(9, 3, 0);
    }

    @Test
    public void testForPositiveArguments() {
        //given
        double expected = 72;
        Figure trapezoid = new Trapezoid(5, 7, 12);

        //when
        double result = trapezoid.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
