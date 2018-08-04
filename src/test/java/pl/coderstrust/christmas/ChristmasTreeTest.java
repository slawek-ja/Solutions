package pl.coderstrust.christmas;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ChristmasTreeTest {
    @Test
    public void testForLessThenThree() {
        //given
        int size = 2;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Invalid Value");

        //when
        ArrayList<String> array = ChristmasTree.getChristmasTree(size);

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForNegative() {
        //given
        int size = -2;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Invalid Value");

        //when
        ArrayList<String> array = ChristmasTree.getChristmasTree(size);

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForThree() {
        //given
        int size = 3;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("   *");
        expected.add("  ***");
        expected.add(" *****");
        expected.add("  **");


        //when
        ArrayList<String> array = ChristmasTree.getChristmasTree(size);

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForFirstFive() {
        //given
        int size = 5;
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("     *");
        expected.add("    ***");
        expected.add("   *****");
        expected.add("  *******");
        expected.add(" *********");
        expected.add("    **");

        //when
        ArrayList<String> array = ChristmasTree.getChristmasTree(size);

        //then
        assertTrue(expected.equals(array));
    }
}
