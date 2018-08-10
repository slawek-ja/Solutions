package pl.coderstrust.sort;

import static org.junit.Assert.*;

public class InsertionSortTest extends SortingTestBase{
    public SortingMethod getSortingMethod() {
        return new InsertionSort();
    }
}
