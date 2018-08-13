package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrapezoidTest {

    @Test(expected = IllegalArgumentException.class)
    public void testForNegative() {
        //given
        Trapezoid example = new Trapezoid(-3, -5, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForZero() {
        //given
        Trapezoid example = new Trapezoid(3, 0, 9);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 72;
        Trapezoid example = new Trapezoid(5, 7, 12);

        //when
        double result = example.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
