package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {

    @Test(expected = IllegalArgumentException.class)
    public void testForNegative() {
        //given
        Triangle example = new Triangle(-3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForZero() {
        //given
        Triangle example = new Triangle(2, 0);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 14;
        Triangle example = new Triangle(4, 7);

        //when
        double result = example.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
