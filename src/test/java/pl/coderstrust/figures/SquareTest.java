package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void testForNegative() {
        //given
        double expected = -1;
        double result;
        Square test = new Square(-12);

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
        Square test = new Square(0);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }

    @Test
    public void testForPositiveNum() {
        //given
        double expected = 25;
        double result;
        Square test = new Square(5);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }
}
