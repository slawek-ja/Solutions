package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SquareTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegative() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure square = new Square(-12);
    }

    @Test
    public void testForZero() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure square = new Square(0);
    }

    @Test
    public void testForPositiveNum() {
        //given
        double expected = 25;
        Figure square = new Square(5);

        //when
        double result = square.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
