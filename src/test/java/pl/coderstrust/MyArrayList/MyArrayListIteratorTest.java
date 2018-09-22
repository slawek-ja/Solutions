package pl.coderstrust.MyArrayList;

import java.util.List;

public class MyArrayListIteratorTest extends ArrayIteratorTestBase {
    @Override
    public List getArrayList() {
        return new MyArrayList();
    }
}
