package pl.coderstrust.sort;

import static org.junit.Assert.*;

public class SelectionSortTest extends SortingTestBase{
    public SortingMethod getSortingMethod() {
        return new SelectionSort();
    }
}
