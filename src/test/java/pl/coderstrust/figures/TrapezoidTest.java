package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrapezoidTest {

    @Test
    public void testForNegative() {
        //given
        double expected = -1;
        double result;
        Trapezoid test = new Trapezoid(-3, -5, 9);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }

    @Test
    public void testForZero() {
        //given
        double expected = -1;
        double result;
        Trapezoid test = new Trapezoid(3, 0, 9);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 72;
        double result;
        Trapezoid test = new Trapezoid(5, 7, 12);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }
}
