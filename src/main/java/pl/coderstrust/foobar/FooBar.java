package pl.coderstrust.foobar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FooBar {
    public static List<String> printFooBar(int size) {
        if (size < 0){
            return new ArrayList<String>(Collections.singletonList("Invalid Value"));
        }
        List<String> result = new ArrayList<String>();
        String storage;
        for (int i=0;i<=size;i++) {
            storage = i + " ";//System.out.print(i + " ");
            if(i % 3 == 0) storage += "Foo";//System.out.print("Foo");
            if(i % 5 == 0) storage += "Bar";//System.out.print("Bar");
            result.add(storage);
        }
        return result;
    }
}
