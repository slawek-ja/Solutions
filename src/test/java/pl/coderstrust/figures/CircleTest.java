package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void testForNegative() {
        //given
        double expected = -1;
        double result;
        Circle test = new Circle(-6);

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
        Circle test = new Circle(0);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }

    @Test
    public void testForPositiveNum() {
        //given
        double expected = 153.94D;
        double result;
        Circle test = new Circle(7);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }
}
