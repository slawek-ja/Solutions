package pl.coderstrust.search;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinarySearchTest {

    //array used for tests
    public int[] giveArrayForTests() {
        return new int[]{
                95, 44, -75, 12, -85, 89, 22, -97, 33, 32, -79, 26, 95, -85, 53,
                83, -26, 47, 28, 33, -19, 37, 22, 68, 37, 42, 89, -27, 95, 32, 83,
                95, 19, 12, 19, -88, -32, 28, 34, 30, -25, 30, 75, 12, -43, -8, 53,
                28, 46, 86, 21, -64, 40, 97, 1, 98, 16, 87, 26, 69, -56, 3, -34, 61,
                71, 69, 70, -71, 32, 68, 4, 22, 44, -66, -93, 42, 7, 47, 24, 45, 15,
                98, 33, 35, 10, 74, 30, 92, 0, 97, -53, 10, 86, 55, 59, -7, 84, 60, 30, 8,};
    }

    @Test
    public void testSearchPositionOfGivenNegative() {
        //given
        int[] array = giveArrayForTests();
        int expectedIndex = 8;
        int searchNumber = -66;

        //when
        int result  = BinarySearch.search(array, searchNumber);

        //then
        assertEquals(expectedIndex, result);
    }

    @Test
    public void testSearchPositionOfGivenZero() {
        //given
        int[] array = giveArrayForTests();
        int expectedIndex = 21;
        int searchNumber = 0;

        //when
        int result  = BinarySearch.search(array, searchNumber);

        //then
        assertEquals(expectedIndex, result);
    }

    @Test
    public void testSearchPositionOfGivenPositive() {
        //given
        int[] array = giveArrayForTests();
        int expectedIndex = 27;
        int searchNumber = 10;

        //when
        int result  = BinarySearch.search(array, searchNumber);

        //then
        assertEquals(expectedIndex, result);
    }

    @Test
    public void testSearchPositionWhenGivenNumberIsNotInArray() {
        //given
        int[] array = giveArrayForTests();
        int expectedIndex = -1;
        int searchNumber = -20;

        //when
        int result  = BinarySearch.search(array, searchNumber);

        //then
        assertEquals(expectedIndex, result);
    }
}
