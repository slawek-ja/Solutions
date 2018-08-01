package pl.coderstrust.foobar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FooBar {
    public static List<String> getFooBar(int size) {
        if (size < 0){
            return new ArrayList<String>(Collections.singletonList("Invalid Value"));
        }
        List<String> result = new ArrayList<String>();
        StringBuilder storage = new StringBuilder();
        for (int i=0;i<=size;i++) {
            storage.append(i + " ");
            if(i % 3 == 0) storage.append("Foo");
            if(i % 5 == 0) storage.append("Bar");
            result.add(storage.toString());
            storage.delete(0,storage.length());
        }
        return result;
    }
}
