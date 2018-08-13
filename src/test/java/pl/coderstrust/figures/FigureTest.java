package pl.coderstrust.figures;

import org.junit.Test;
import static org.junit.Assert.*;

public class FigureTest {

    @Test
    public void showPolymorphism() {
        //given
        double expected = 36;
        double result;
        //test is now Square object
        Figure test = new Square(6);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);

        //given, test is now Rectangle object
        expected = 21;
        test = new Rectangle(3, 7);

        //when
        result = test.calculateArea();

        //then
        assertEquals(expected, result, 2);
    }
}
