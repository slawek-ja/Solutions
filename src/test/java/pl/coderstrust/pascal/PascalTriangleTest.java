package pl.coderstrust.pascal;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PascalTriangleTest {
    @Test
    public void testForNegative() {
        //given
        int size = -10;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Invalid Value");

        //when
        ArrayList<String> array = PascalTriangle.getPascalTriangle(size);

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForZero() {
        //given
        int size = 0;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Invalid Value");

        //when
        ArrayList<String> array = PascalTriangle.getPascalTriangle(size);

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForOne() {
        //given
        int size = 1;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("           1");

        //when
        ArrayList<String> array = PascalTriangle.getPascalTriangle(size);

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForFirstFour() {
        //given
        int size = 4;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("                    1");
        expected.add("                 1     1");
        expected.add("              1     2     1");
        expected.add("           1     3     3     1");

        //when
        ArrayList<String> array = PascalTriangle.getPascalTriangle(size);

        //then
        assertTrue(expected.equals(array));
    }
}