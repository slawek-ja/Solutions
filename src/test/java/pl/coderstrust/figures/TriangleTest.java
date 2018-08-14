package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TriangleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegativeFirstArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Figure triangle = new Triangle(-3, 2);
    }

    @Test
    public void testForNegativeSecondArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid second value");
        Figure triangle = new Triangle(3, -2);
    }

    @Test
    public void testForZeroFirstArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid first value");
        Figure triangle = new Triangle(0, 4);
    }

    @Test
    public void testForZeroSecondArgument() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid second value");
        Figure triangle = new Triangle(4, 0);
    }

    @Test
    public void testForPositiveArguments() {
        //given
        double expected = 14;
        Figure triangle = new Triangle(4, 7);

        //when
        double result = triangle.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
