package pl.coderstrust.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class SortingTestBase {

    @Test
    public void testBubbleSort() {
        //given
        int[] expected = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] array = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};
        BubbleSortTest test = new BubbleSortTest();

        //when
        long startTime = System.currentTimeMillis();
        array = test.getSortingMethod().sort(array);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testCollectionSort() {
        //given
        int[] expected = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] array = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};
        CollectionSortTest test = new CollectionSortTest();

        //when
        long startTime = System.currentTimeMillis();
        array = test.getSortingMethod().sort(array);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testInsertionSort() {
        //given
        int[] expected = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] array = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};
        InsertionSortTest test = new InsertionSortTest();

        //when
        long startTime = System.currentTimeMillis();
        array = test.getSortingMethod().sort(array);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSelectionSort() {
        //given
        int[] expected = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] array = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};
        SelectionSortTest test = new SelectionSortTest();

        //when
        long startTime = System.currentTimeMillis();
        array = test.getSortingMethod().sort(array);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        //then
        assertArrayEquals(expected, array);
    }

    @Test
    public void testShellSort() {
        //given
        int[] expected = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] array = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};
        ShellSortTest test = new ShellSortTest();

        //when
        long startTime = System.currentTimeMillis();
        array = test.getSortingMethod().sort(array);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        //then
        assertArrayEquals(expected, array);
    }
}