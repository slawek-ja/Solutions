package pl.coderstrust.MyArrayList;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import java.util.*;

@RunWith(JUnitParamsRunner.class)
public abstract class ArrayTestBase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public abstract List getArrayList();

    public Collection<String> split(Collection<String> givenArray, String argument) {
        if (argument.length() == 0) {
            return givenArray;
        }
        String[] storage;
        storage = argument.split(" ");
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals("null")) {
                storage[i] = null;
            }
            givenArray.add(storage[i]);
        }
        return givenArray;
    }

    public Object[] split(String argument) {
        if (argument.length() == 0) {
            return new Object[]{};
        }
        String[] storage;
        storage = argument.split(" ");
        Object[] givenArray = new Object[storage.length];
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals("null")) {
                storage[i] = null;
            }
            givenArray[i] = storage[i];
        }
        return givenArray;
    }

    public String checkArgument(String argument) {
        if (argument.equals("null")) {
            return argument = null;
        }
        return argument;
    }

    @Test
    public void testForAddMethod() {
        //given
        List<String> givenList = getArrayList();
        List<String> expectedString = Arrays.asList("example", null, "");

        //when
        givenList.add("example");
        givenList.add(null);
        givenList.add("");

        //then
        assertEquals(expectedString, expectedString);
    }

    @Test
    @Parameters({
            "-1 0 1 2 3, 2, 4, -1 0 4 1 2 3",
            "5 3 2 -5, 1, 3, 5 3 3 2 -5",
            "8 5 2 3, 0, 1, 1 8 5 2 3",
            "-4 2 -3, 0, -2, -2 -4 2 -3",
            "1 2 3 4, 3, 3, 1 2 3 3 4",
            "-2 3 -7 -2 1, 4, -3, -2 3 -7 -2 -3 1",
            "1 2 3, 1, null, 1 null 2 3"})
    public void testForAddMethodWithIndexAndValidArguments(String fillArray, int index, String element, String expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> expectedString = new ArrayList<>();
        split(expectedString, expected);

        //when
        givenList.add(index, checkArgument(element));

        //then
        assertEquals(expectedString, givenList);
    }

    @Test
    public void testForAddMethodWithInvalidIndex() {
        //given
        List<String> givenList = getArrayList();
        givenList.add("1");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 2, Size: 1");
        givenList.add(2, "2");
    }

    @Test
    @Parameters({
            "1 2 3, 4 5 6, 1 2 3 4 5 6, true",
            "-1 -2 -3, -4 -5 -6, -1 -2 -3 -4 -5 -6, true",
            "0 0 1, 2 0 0, 0 0 1 2 0 0, true",
            ", 4 5 6, 4 5 6, true",
            "1 2 3,, 1 2 3, false",
            "null 1 2, 3 null, null 1 2 3 null, true"})
    public void testForAddAllMethod(String fillArray, String collection, String expectedArray, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenList.addAll(collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenList);
    }

    @Test
    @Parameters({
            "1 4, 2 3, 1, 1 2 3 4, true",
            "-1 -4, -2 -3, 1, -1 -2 -3 -4, true",
            "-2 -1 1, 0, 2, -2 -1 0 1, true",
            "1 2 null, 3 null, 2, 1 2 3 null null, true",
            "1 2 3, 4 5 6, 3, 1 2 3 4 5 6, true"})
    public void testForAddAllMethodWithIndex(String fillArray, String collection, int index, String expectedArray, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenList.addAll(index, collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenList);
    }

    @Test
    public void testForAddAllMethodWithIndexWhenGivenIndexIsOutOfRange() {
        //given
        List<String> givenList = getArrayList();
        givenList.add("1");
        List<String> sampleArrayString = Arrays.asList("2");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 5, Size: 1");
        givenList.addAll(5, sampleArrayString);
    }

    @Test
    @Parameters({
            "1 2 3",
            "-1 -2 -3",
            "0 0 0",
            "null 1 null",
            "-1 null 0 2",
            ""})
    public void testForClearMethod(String fillArray) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> expected = new ArrayList<>();

        //when
        givenList.clear();

        //then
        assertEquals(expected, givenList);
    }

    @Test
    @Parameters({
            "1 2 3 4, 1, true",
            "-1 0 1, 0, true",
            "-3 -2 -1 1 2 3, -1, true",
            "1 2 3 4, 8, false",
            "-1 2 3, -2, false",
            "1 null 2 3, null, true",
            ", 2, false"})
    public void testForContainsMethod(String fillArray, String searched, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, checkArgument(fillArray));

        //when
        boolean result = givenList.contains(checkArgument(searched));

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 2 3 4, 2 3, true",
            "-1 2 -3 4, -1 -3, true",
            "1 0 2 -3, 0, true",
            "7 2 1, 7 5, false",
            "1 2 3, 4 5 6, false",
            "-1 -2, -3 -4, false",
            "1 null 2 3, null 3, true",
            ", 1 2 3, false"
    })
    public void testForContainsAllMethod(String fillArray, String givenCollection, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        Vector<String> collection = new Vector<>();
        split(collection, givenCollection);

        //when
        boolean result = givenList.containsAll(collection);

        //then
        assertEquals(expected, result);
    }

    @Test
    public void TestyDlaEquals() {
        //dodac
    }

    @Test
    @Parameters({
            "0, 1",
            "1, 2",
            "2, 3"})
    public void testForGetMethod(int index, String expectedValue) {
        //given
        List<String> givenList = getArrayList();
        givenList.add("1");
        givenList.add("2");
        givenList.add("3");

        //when
        String result = givenList.get(index);

        //then
        assertEquals(expectedValue, result);
    }

    @Test
    public void testForGetMethodWithInvalidIndex() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 2, Size: 1");
        List<String> givenList = getArrayList();
        givenList.add("1");

        //when
        String result = givenList.get(2);
    }

    @Test
    public void TestyDlaHashCode() {
        //dodac
    }

    @Test
    @Parameters({
            "1 2 3, 1, 0",
            "-1 -2 -3, -2, 1",
            "1 0 2, 0, 1",
            "1 1 1 2, 1, 0",
            "1 2 3, 4, -1",
            "1 2 3, -9, -1",
            "1 2 3 null, null, 3",
            ", 2, -1"})
    public void testForIndexOfObjectArgument(String fillArray, String givenArgument, int expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);

        //when
        int result = givenList.indexOf(checkArgument(givenArgument));

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 2 3 4, false",
            "-1 -2 -3 -4, false",
            "0 0 0 0, false",
            "null null, false",
            ", true"})
    public void testForIsEmptyMethod(String arguments, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, checkArgument(arguments));

        //when
        boolean result = givenList.isEmpty();

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 2 3 3, 3, 3",
            "-1 -2 -3 -3, -3, 3",
            "0 0 2, 0, 1",
            "1 2 3 3, 4, -1",
            "1 2 3 3, -9, -1",
            "1 2 null null, null, 3",
            ", 2, -1"})
    public void testForLastIndexOfObjectArgument(String fillArray, String givenArgument, int expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);

        //when
        int result = givenList.lastIndexOf(checkArgument(givenArgument));

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 3 2, 1, 1 2",
            "-1 -3 -2, 1, -1 -2",
            "1 2 0 3, 2, 1 2 3",
            "null 1 2, 0, 1 2",
            "1 2 4, 2, 1 2"})
    public void testForRemoveMethodIndex(String fillArray, int index, String expectedArray) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> expected = new ArrayList<>();
        split(expected, expectedArray);

        //when
        givenList.remove(index);

        //then
        assertEquals(expected, givenList);
    }

    @Test
    public void testForRemoveMethodIndexWhenArrayIsEmpty() {
        //given
        List<String> givenList = getArrayList();
        List<String> expectedString = Arrays.asList();

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 1, Size: 0");
        givenList.remove(1);
    }

    @Test
    public void testForRemoveMethodIndexWhenGivenIndexIsOutOfRange() {
        //given
        List<String> givenList = getArrayList();
        givenList.add("1");
        givenList.add("2");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 4, Size: 2");
        givenList.remove(4);
    }

    @Test
    @Parameters({
            "1 2 3 4, 2, 1 3 4, true",
            "-1 -2 -3 -4, -3, -1 -2 -4, true",
            "1 -3 0 2, 0, 1 -3 2, true",
            "1 2 3, 3, 1 2, true",
            "1 2 3, 1, 2 3, true",
            "1 2 null, null, 1 2, true",
            "1 2 3, 5, 1 2 3, false",
            "-1 -2 -3, -5, -1 -2 -3, false",
            ", 2,, false"})
    public void testForRemoveMethodObjectArgument(String fillArray, String removeValue, String expectedArray, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> expectedString = new ArrayList<>();
        split(expectedString, expectedArray);

        //when
        boolean result = givenList.remove(checkArgument(removeValue));

        //then
        assertEquals(expected, result);
        assertEquals(expectedString, givenList);
    }

    @Test
    @Parameters({
            "1 2 3 4, 3 4, 1 2, true",
            "-1 -2 -3 -4, -3 -4, -1 -2, true",
            "0 1 2 0, 0, 1 2, true",
            "1 2 3, 1 3, 2, true",
            "1 2 3, 4 5 6, 1 2 3, false",
            "1 null 2, null, 1 2, true",
            ", 1 2, , false",
            "1 2, , 1 2, false",
            "1 2 3, 1 2 3, , true"})
    public void testForRemoveAllMethod(String fillArray, String collection, String expectedArray, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenList.removeAll(collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenList);
    }

    @Test
    @Parameters({
            "1 2 3, 2, 2, true",
            "-1 -2 -3, -2, -2, true",
            "-1 0 1, 0, 0, true",
            "null 1 null, null, null null, true",
            "1 2 3, 4 5 6,, true",
            ", 1 2,, false",
            "1 2 3, 1 2 3, 1 2 3, false"})
    public void testForRetainAllMethod(String fillArray, String collection, String expectedArray, boolean expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenList.retainAll(collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenList);
    }

    @Test
    public void TestDlaSet() {
        //dodac
    }

    @Test
    @Parameters({
            "1 2 3 4 5, 5",
            "-1 2 0 -7, 4",
            "-4 -3 -2 -1, 4",
            "null null null 1, 4",
            ", 0"})
    public void testForSizeMethod(String arguments, int expectedSize) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, checkArgument(arguments));

        //when
        int result = givenList.size();

        //then
        assertEquals(expectedSize, result);
    }

    @Test
    @Parameters({
            "1 2 3, 0, 3, 1 2 3",
            "-1 -2 -3, 0, 3, -1 -2 -3",
            "-1 0 1, 1, 2, 0",
            "null 1 2, 0, 1, null",
            "1 2 3, 1, 1,"})
    public void testForSubListMethod(String fillArray, int indexStart, int howManyValues, String expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        List<String> expectedArray = new ArrayList<>();
        split(expectedArray, expected);

        //when
        List<String> result = givenList.subList(indexStart, howManyValues);

        //then
        assertEquals(expectedArray, result);
    }

    @Test
    public void testForSubListMethodWhenSecondArgumentIsOutOfRange() {
        //given
        List<String> givenList = getArrayList();
        givenList.add("1");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("toIndex = 2");
        List<String> resultString = givenList.subList(0, 2);
    }

    @Test
    public void testForSubListMethodWhenGivenArrayIsEmpty() {
        //given
        List<String> givenList = getArrayList();

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("toIndex = 2");
        List<String> resultString = givenList.subList(0, 2);
    }

    @Test
    @Parameters({
            "1 2 3, 1 2 3",
            "-1 -2 -3, -1 -2 -3",
            "0 0 0, 0 0 0",
            "null null null, null null null",
            ","})
    public void testForToArrayMethod(String fillArray, String expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, checkArgument(fillArray));
        Object[] expectedString = split(checkArgument(expected));

        //when
        Object[] resultString = givenList.toArray();

        //then
        assertEquals(expectedString, resultString);
    }

    @Test
    @Parameters({
            "1 2 3 4, 1 2 3 4",
            "-1 -2 -3 -4, -1 -2 -3 -4",
            "0 0 0, 0 0 0",
            "-5 -2 9 -2, -5 -2 9 -2",
            "null null null, null null null",
            ","})
    public void testForToArrayMethodWithTArgument(String fillArray, String expected) {
        //given
        List<String> givenList = getArrayList();
        split(givenList, fillArray);
        Object[] expectedString = split(checkArgument(expected));

        //when
        String[] resultString = new String[0];
        resultString = givenList.toArray(resultString);

        //then
        assertEquals(expectedString, resultString);
    }
}
