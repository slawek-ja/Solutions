package pl.coderstrust.search;

import org.junit.Test;
import static org.junit.Assert.*;

public class SpeedTestForSearchAlgorithmsTest {

    //Test it without line #7 in BinarySearch class

    @Test
    public void testSpeedOfLinearSearch() {
        //given
        int[] arrayForSpeedTest = giveArrayForTests();
        int expectedIndex = 9999999;
        int searchNumber = 2;

        //when

        long startTime = System.nanoTime();
        int result = LinearSearch.search(arrayForSpeedTest, searchNumber);
        long endTime = System.nanoTime();

        System.out.println("Linear: " + (endTime - startTime));

        //then
        assertEquals(expectedIndex, result);
    }

    @Test
    public void testSpeedOfBinarySearch() {
        //given
        int[] arrayForSpeedTest = giveArrayForTests();
        int expectedIndex = 9999999;
        int searchNumber = 2;

        //when

        long startTime = System.nanoTime();
        int result = BinarySearch.search(arrayForSpeedTest, searchNumber);
        long endTime = System.nanoTime();

        System.out.println("Binary: " + (endTime - startTime));

        //then
        assertEquals(expectedIndex, result);
    }

    //array for speed test
    public int[] giveArrayForTests() {
        int[] array = new int[10000000];
        for (int index : array) {
            index = 1;
        }
        array[9999999] = 2;
        return array;
    }
}
