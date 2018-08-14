package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CircleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegative() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value");
        Figure circle = new Circle(-6);
    }

    @Test
    public void testForZero() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value");
        Figure circle = new Circle(0);
    }

    @Test
    public void testForPositiveNum() {
        //given
        double expected = 153.94D;
        Figure circle = new Circle(7);

        //when
        double result = circle.area();
        result = Math.round(result *100.0D) / 100.0D;

        //then
        assertEquals(expected, result, 0.0001);
    }
}
