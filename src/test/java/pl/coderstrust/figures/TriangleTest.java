package pl.coderstrust.figures;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TriangleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegativeFirstArg() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure triangle = new Triangle(-3, 2);
    }

    @Test
    public void testForNegativeSecondArg() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure triangle = new Triangle(3, -2);
    }

    @Test
    public void testForZero() {
        //given
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Value");
        Figure triangle = new Triangle(2, 0);
    }

    @Test
    public void testForPositive() {
        //given
        double expected = 14;
        Figure triangle = new Triangle(4, 7);

        //when
        double result = triangle.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
