package pl.coderstrust.sort;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class SortingTestBase {

    public abstract SortingMethod getSortingMethod();

    @Test
    public void shouldSortSimpleArray(){
        // given
        int[] expected = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] array = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};

        // when
        long startTime = System.currentTimeMillis();
        int[] result = getSortingMethod().sort(array);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        // then
        assertArrayEquals(expected, result);
    }
}
