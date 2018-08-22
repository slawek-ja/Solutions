package pl.coderstrust.search;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public abstract class SearchTestBase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public abstract SearchingMethod getSearchingMethod();

    private int[] getSortedArrayForTests() {
        return new int[]{-98, -97, -95, -83, -81, -78, -77, -73, -71, -64, -61, -60, -55, -54,
                -52, -51, -49, -46, -42, -41, -39, -38, -36, -34, -31, -26, -23, -21, -20, -19,
                -3, 0, 2, 3, 3, 3, 3, 3, 25, 26, 27, 28, 29, 34, 37, 38, 39, 45, 47, 52, 54,
                58, 58, 63, 66, 68, 69, 72, 76, 79, 83, 84, 88, 92, 93, 93, 95, 96, 99, 102, 105,
                106, 108, 109, 114, 117, 120, 122, 123, 124, 125, 126, 131, 132, 133, 135, 138,
                145, 148, 149, 150, 159, 164, 167, 171, 180, 182, 191, 198, 199};
    }

    @Test
    @Parameters({"-98, 0",
            "-71, 8",
            "-19, 29",
            "0, 31",
            "37, 44",
            "400, -1",
            "1, -1",
            "199, 99"})
    public void testForSearchingIndexOfValue(int value, int expectedIndex) {
        //given
        int[] array = getSortedArrayForTests();

        //when
        int result = getSearchingMethod().search(array, value);

        //then
        assertEquals(expectedIndex, result);
    }

    @Test
    public void testForSearchingFirstAppearanceOfElement() {
        //given
        int[] array = new int[]{-1, 0, 1, 2, 3, 3, 3, 3, 3, 4, 5, 6};
        int searchValue = 3;
        int expectedIndex = 4;

        //when
        int result = getSearchingMethod().search(array, searchValue);

        //then
        assertEquals(expectedIndex, result);
    }

    @Test
    public void testForEmptyArray() {
        //given
        int[] array = new int[0];
        int searchValue = 3;

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Array is empty. Cannot use searching method");
        int result = getSearchingMethod().search(array, searchValue);
    }

    @Test
    public void testForNullAsArray() {
        //given
        int[] array = null;
        int searchValue = 3;

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Array cannot be null");
        int result = getSearchingMethod().search(array, searchValue);
    }

    @Test
    public void testForSpeedOfSorting() {
        //given
        int[] array = getArrayForSpeedTest();
        int expected = array.length - 1;
        int searchValue = 2;

        //when
        long startTime = System.nanoTime();
        int result = getSearchingMethod().search(array, searchValue);
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

        //then
        assertEquals(expected, result);
    }

    private int[] getArrayForSpeedTest() {
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        array[array.length - 1] = 2;
        return array;
    }
}
