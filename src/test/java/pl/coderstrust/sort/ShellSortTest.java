package pl.coderstrust.sort;

import static org.junit.Assert.*;

public class ShellSortTest extends SortingTestBase{
    public SortingMethod getSortingMethod() {
        return new ShellSort();
    }
}
