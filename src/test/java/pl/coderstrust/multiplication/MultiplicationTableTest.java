package pl.coderstrust.multiplication;

import org.junit.Test;
import static org.junit.Assert.*;

public class MultiplicationTableTest {
    @Test
    public void testForNegative() {
        //given
        int size = -10;
        int[][] expected = new int[0][0];

        //when
        int[][] array = MultiplicationTable.getMultiplicationTable(size);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testForZero() {
        //given
        int size = 0;
        int[][] expected = new int[0][0];

        //when
        int[][] array = MultiplicationTable.getMultiplicationTable(size);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testForOne() {
        //given
        int size = 1;
        int[][] expected = {{1}};

        //when
        int[][] array = MultiplicationTable.getMultiplicationTable(size);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testFirstFour() {
        //given
        int size = 4;
        int[][] expected = {{1, 2, 3, 4},{2, 4, 6, 8},{3, 6, 9, 12}, {4, 8, 12, 16}};

        //when
        int[][] array = MultiplicationTable.getMultiplicationTable(size);

        //then
        assertArrayEquals(expected, array);
    }
}
