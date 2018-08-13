package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest {

    @Test(expected = IllegalArgumentException.class)
    public void testForNegative() {
        //given
        Rectangle example = new Rectangle(-3, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForZero() {
        //given
        Rectangle example = new Rectangle(3, 0);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 20;
        Rectangle example = new Rectangle(4, 5);

        //when
        double result = example.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
