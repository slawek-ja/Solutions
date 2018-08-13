package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class FigureTest {

    @Test
    public void showPolymorphism() {
        //given
        double expected = 36;
        Figure example = new Square(6);

        //when
        double result = example.area();

        //then
        assertEquals(expected, result, 0.0001);

        //given
        expected = 21;
        example = new Rectangle(3, 7);

        //when
        result = example.area();

        //then
        assertEquals(expected, result, 0.0001);
    }
}
