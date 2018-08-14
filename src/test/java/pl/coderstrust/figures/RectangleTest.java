package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class RectangleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegativeFirstArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Figure rectangle = new Rectangle(-3, 6);
    }

    @Test
    public void testForNegativeSecondArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid second value");
        Figure rectangle = new Rectangle(4, -2);
    }

    @Test
    public void testForZero() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid second value");
        Figure rectangle = new Rectangle(3, 0);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 20;
        Figure rectangle = new Rectangle(4, 5);

        //when
        double result = rectangle.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
