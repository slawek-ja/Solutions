package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {

    @Test(expected = IllegalArgumentException.class)
    public void testForNegative() {
        //given
        Square example = new Square(-12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForZero() {
        //given
        Square example = new Square(0);
    }

    @Test
    public void testForPositiveNum() {
        //given
        double expected = 25;
        Square example = new Square(5);

        //when
        double result = example.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
