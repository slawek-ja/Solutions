package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void testForNegative() {
        //given
        double expected = -1;
        double result;
        Triangle test = new Triangle(-3, 2);

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
        Triangle test = new Triangle(2, 0);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 14;
        double result;
        Triangle test = new Triangle(4, 7);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }
}
