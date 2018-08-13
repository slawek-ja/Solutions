package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void testForNegative() {
        //given
        double expected = -1;
        double result;
        Rectangle test = new Rectangle(-3, 6);

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
        Rectangle test = new Rectangle(3, 0);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 20;
        double result;
        Rectangle test = new Rectangle(4, 5);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }
}
