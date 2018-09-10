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
        List<String> givenArrayString = getArrayList();
        List<String> expectedString = Arrays.asList("example", null, "");

        //when
        givenArrayString.add("example");
        givenArrayString.add(null);
        givenArrayString.add("");

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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> expectedString = new ArrayList<>();
        split(expectedString, expected);

        //when
        givenArrayString.add(index, checkArgument(element));

        //then
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddMethodWithInvalidIndex() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 2, Size: 1");
        givenArrayString.add(2, "2");
    }

    @Test
    @Parameters({
            "0, 1",
            "1, 2",
            "2, 3"})
    public void testForGetMethod(int index, String expectedValue) {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");

        //when
        String result = givenArrayString.get(index);

        //then
        assertEquals(expectedValue, result);
    }

    @Test
    public void testForGetMethodWithInvalidIndex() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 2, Size: 1");
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");

        //when
        String result = givenArrayString.get(2);
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
        List<String> givenStringArray = getArrayList();
        split(givenStringArray, checkArgument(arguments));

        //when
        int result = givenStringArray.size();

        //then
        assertEquals(expectedSize, result);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, checkArgument(arguments));

        //when
        boolean result = givenArrayString.isEmpty();

        //then
        assertEquals(expected, result);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, checkArgument(fillArray));

        //when
        boolean result = givenArrayString.contains(checkArgument(searched));

        //then
        assertEquals(expected, result);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, checkArgument(fillArray));
        Object[] expectedString = split(checkArgument(expected));

        //when
        Object[] resultString = givenArrayString.toArray();

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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        Object[] expectedString = split(checkArgument(expected));

        //when
        String[] resultString = new String[0];
        resultString = givenArrayString.toArray(resultString);

        //then
        assertEquals(expectedString, resultString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> expectedString = new ArrayList<>();
        split(expectedString, expectedArray);

        //when
        boolean result = givenArrayString.remove(checkArgument(removeValue));

        //then
        assertEquals(expected, result);
        assertEquals(expectedString, givenArrayString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        Vector<String> collection = new Vector<>();
        split(collection, givenCollection);

        //when
        boolean result = givenArrayString.containsAll(collection);

        //then
        assertEquals(expected, result);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenArrayString.addAll(collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenArrayString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenArrayString.addAll(index, collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenArrayString);
    }

    @Test
    public void testForAddAllMethodWithIndexWhenGivenIndexIsOutOfRange() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        List<String> sampleArrayString = Arrays.asList("2");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 5, Size: 1");
        givenArrayString.addAll(5, sampleArrayString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenArrayString.removeAll(collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenArrayString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> collectionArray = new ArrayList<>();
        split(collectionArray, collection);
        List<String> resultArray = new ArrayList<>();
        split(resultArray, expectedArray);

        //when
        boolean result = givenArrayString.retainAll(collectionArray);

        //then
        assertEquals(expected, result);
        assertEquals(resultArray, givenArrayString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> expected = new ArrayList<>();

        //when
        givenArrayString.clear();

        //then
        assertEquals(expected, givenArrayString);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> expected = new ArrayList<>();
        split(expected, expectedArray);

        //when
        givenArrayString.remove(index);

        //then
        assertEquals(expected, givenArrayString);
    }

    @Test
    public void testForRemoveMethodIndexWhenArrayIsEmpty() {
        //given
        List<String> givenArrayString = getArrayList();
        List<String> expectedString = Arrays.asList();

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 1, Size: 0");
        givenArrayString.remove(1);
    }

    @Test
    public void testForRemoveMethodIndexWhenGivenIndexIsOutOfRange() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        givenArrayString.add("2");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 4, Size: 2");
        givenArrayString.remove(4);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);

        //when
        int result = givenArrayString.indexOf(checkArgument(givenArgument));

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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);

        //when
        int result = givenArrayString.lastIndexOf(checkArgument(givenArgument));

        //then
        assertEquals(expected, result);
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
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        List<String> expectedArray = new ArrayList<>();
        split(expectedArray, expected);

        //when
        List<String> result = givenArrayString.subList(indexStart, howManyValues);

        //then
        assertEquals(expectedArray, result);
    }

    @Test
    public void testForSubListMethodWhenSecondArgumentIsOutOfRange() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("toIndex = 2");
        List<String> resultString = givenArrayString.subList(0, 2);
    }

    @Test
    public void testForSubListMethodWhenGivenArrayIsEmpty() {
        //given
        List<String> givenArrayString = getArrayList();

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("toIndex = 2");
        List<String> resultString = givenArrayString.subList(0, 2);
    }

    //listIterator() Tests

    @Test
    @Parameters({
            "1 2 3, true",
            "1, true",
            "-1 -2, true",
            "null null, true",
            ", false"})
    public void testForListIteratorHasNextMethod(String fillArray, boolean expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> iteratorString = null;

        //when
        iteratorString = givenArrayString.listIterator();
        boolean result = iteratorString.hasNext();

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 2, 2",
            "-1 -2, -2",
            "1 0, 0",
            "null null 3, null"})
    public void testForListIteratorNextMethod(String fillArray, String expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> listIterator = null;

        //when
        listIterator = givenArrayString.listIterator();
        listIterator.next();
        String result = listIterator.next();

        //then
        assertEquals(checkArgument(expected), result);
    }

    @Test
    public void testForListIteratorNextMethodWhenIteratorOutOfRange() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        ListIterator<String> iteratorString = null;

        //when
        thrown.expect(NoSuchElementException.class);
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        iteratorString.next();
    }

    @Test
    public void testForListIteratorValidArgumentsHasPreviousMethod() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<String> iteratorString = null;

        //when
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        iteratorString.next();
        boolean resultString = iteratorString.hasPrevious();
        boolean expected = true;

        //then
        assertEquals(expected, resultString);
    }

    @Test
    @Parameters({
            "1 2 3, false",
            "-1 -2 -3, false",
            "0 0, false",
            "null, false",
            ", false"})
    public void testForListIteratorHasPreviousMethodWhenIteratorIsNotMovedAndArrayIsEmpty(String fillArray, boolean expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> listIterator = null;

        //when
        listIterator = givenArrayString.listIterator();
        boolean result = listIterator.hasPrevious();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void testForListIteratorPreviousMethodWithValidArguments() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<String> iteratorString = null;
        String expectedString = "1";

        //when
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        iteratorString.next();
        iteratorString.previous();
        String resultString = iteratorString.previous();

        //then
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForListIteratorPreviousMethodWhenArrayIsEmpty() {
        //given
        List<String> givenArrayString = getArrayList();
        ListIterator<String> iteratorString = null;

        //when
        thrown.expect(NoSuchElementException.class);
        iteratorString = givenArrayString.listIterator();
        iteratorString.previous();
    }

    @Test
    @Parameters({
            "1 2 3, 0, true",
            "-1 -2 -3, 1, true",
            "-1 2 1 -3, 3, true",
            "1 2 3 4, 4, false",
            "null null, 0, true"})
    public void testForListIteratorHasNextMethodWithIndexArgumentAndValidArguments(String fillArray, int index, boolean expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> iteratorString = null;

        //when
        iteratorString = givenArrayString.listIterator(index);
        boolean result = iteratorString.hasNext();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void testForListIteratorHasNextMethodWithIndexArgumentWhenGivenIndexIsOutOfRange() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        ListIterator<String> iteratorString = null;

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 3");
        iteratorString = givenArrayString.listIterator(3);
    }

    @Test
    @Parameters({
            "1 2 3, 0, 1",
            "-1 -2 -3, 1, -2",
            "1 3 6, 2, 6",
            "null 1 null, 2, null"})
    public void testForListIteratorNextMethodWithIndexArgumentValidArguments(String fillArray, int index, String expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> listIterator = null;

        //when
        listIterator = givenArrayString.listIterator(index);
        String result = listIterator.next();

        //then
        assertEquals(checkArgument(expected), result);
    }

    @Test
    public void testForListIteratorNextMethodWithIndexArgumentWhenGivenArrayIsEmpty() {
        //given
        List<String> givenArrayString = getArrayList();
        ListIterator<String> iteratorString = null;

        //when
        thrown.expect(NoSuchElementException.class);
        iteratorString = givenArrayString.listIterator(0);
        iteratorString.next();
    }

    @Test
    @Parameters({
            "1 2 3, 2, true",
            "-1 -2 -3, 1, true",
            "1 2 3, 0, false",
            "1 2 3, 2, true",
            "null null null, 1, true",
            ", 0, false"})
    public void testForListIteratorHasPreviousMethodWithIndexArgumentValidArguments(String fillArray, int index, boolean expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> listIterator = null;

        //when
        listIterator = givenArrayString.listIterator(index);
        boolean result = listIterator.hasPrevious();

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 2 3, 1, 1",
            "-1 -2 -3, 2, -2",
            "0 1 2, 1, 0",
            "null 1 null 2, 3, null"})
    public void testForListIteratorPreviousMethodWithIndexArgumentAndValidArguments(String fillArray, int index, String expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        ListIterator<String> listIterator = null;

        //when
        listIterator = givenArrayString.listIterator(index);
        String result = listIterator.previous();

        //then
        assertEquals(checkArgument(expected), result);
    }

    @Test
    public void testForListIteratorPreviousMethodWithIndexArgumentWithoutMovingIterator() {
        //given
        List<String> givenArrayString = getArrayList();
        givenArrayString.add("1");
        ListIterator<String> listIterator = null;

        //when
        thrown.expect(NoSuchElementException.class);
        listIterator = givenArrayString.listIterator(0);
        listIterator.previous();
    }

    @Test
    public void testForListIteratorPreviousMethodWithIndexArgumentWhenArrayIsEmpty() {
        //given
        List<String> givenArrayString = getArrayList();
        ListIterator<String> iteratorString = null;

        //when
        thrown.expect(NoSuchElementException.class);
        iteratorString = givenArrayString.listIterator(0);
        iteratorString.previous();
    }

    @Test
    @Parameters({
            "-1, true",
            "1, true",
            "null, true",
            "0, true",
            ", false"})
    public void testForIteratorHasNextMethodWithValidArguments(String fillArray, boolean expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        Iterator<String> iterator = givenArrayString.iterator();

        //when
        boolean result = iterator.hasNext();

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "1 2, 1",
            "-1 -2, -1",
            "0, 0",
            "null 1, null"})
    public void testForIteratorNextMethodWithValidArguments(String fillArray, String expected) {
        //given
        List<String> givenArrayString = getArrayList();
        split(givenArrayString, fillArray);
        Iterator<String> iterator = givenArrayString.iterator();

        //when
        iterator = givenArrayString.iterator();
        String result = iterator.next();

        //then
        assertEquals(checkArgument(expected), result);
    }

    @Test
    public void testForIteratorNextMethodWhenArrayIsEmpty() {
        //given
        List<String> givenArrayString = getArrayList();
        Iterator<String> iterator = givenArrayString.iterator();

        //when
        thrown.expect(NoSuchElementException.class);
        iterator = givenArrayString.iterator();
        iterator.next();
    }
}
