package pl.coderstrust.MyArrayList;

import java.util.List;

public class MyArrayListTest extends ArrayTestBase {
    @Override
    public List getArrayList() {
        return new MyArrayList();
    }
}
