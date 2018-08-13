package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleTest {

    @Test(expected = IllegalArgumentException.class)
    public void testForNegative() {
        //given
        Circle example = new Circle(-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForZero() {
        //given
        Circle example = new Circle(0);
    }

    @Test
    public void testForPositiveNum() {
        //given
        double expected = 153.94D;
        Circle example = new Circle(7);

        //when
        double result = example.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expected, result, 0.0001);
    }
}
