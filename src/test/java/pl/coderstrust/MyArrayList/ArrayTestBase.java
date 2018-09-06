package pl.coderstrust.MyArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(JUnitParamsRunner.class)
public abstract class ArrayTestBase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public abstract List getArraysMethod();

    //add() Tests

    @Test
    public void testForAddMethod() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        List<Integer> expectedInt = Arrays.asList(-1, null, 0);
        List<String> expectedString = Arrays.asList("example", null, "");

        //when
        givenArrayInt.add(-1);
        givenArrayInt.add(null);
        givenArrayInt.add(0);
        givenArrayString.add("example");
        givenArrayString.add(null);
        givenArrayString.add("");

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, expectedString);
    }

    //add(int index, T element) Tests

    @Test
    public void testForAddMethodWithIndexAndValidMethods() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("3");
        List<Integer> expectedInt = Arrays.asList(1,2,3);
        List<String> expectedString = Arrays.asList("1", "2", "3");

        //when
        givenArrayInt.add(1, 2);
        givenArrayString.add(1, "2");

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddMethodWithIndexWhenIndexIsOnLastPositionOfArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> expectedInt = Arrays.asList(1,2,3);
        List<String> expectedString = Arrays.asList("1", "2", "3");

        //when
        givenArrayInt.add(2, 3);
        givenArrayString.add(2, "3");

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddMethodWithIndexWhenAddedElementIsNull() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        List<Integer> expectedInt = Arrays.asList(1, null);
        List<String> expectedString = Arrays.asList("1", null);

        //when
        givenArrayInt.add(1, null);
        givenArrayString.add(1, null);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddMethodWithIndexWhenGivenIndexIsOutOfRange() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 2, Size: 1");
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");

        //when
        givenArrayInt.add(2, 2);
        givenArrayString.add(2, "2");
    }

    //get(int index) Tests

    @Test
    @Parameters({
            "0, 1",
            "1, 2",
            "2, 3"})
    public void testForParametrisedGetMethodWithValidArgument(Integer givenArgument, Integer expected) {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");

        //when
        Integer resultInt = givenArrayInt.get(givenArgument);
        String resultString = givenArrayString.get(givenArgument);

        //then
        assertEquals(expected, resultInt);
        assertEquals(Integer.toString(expected), resultString);
    }

    @Test
    public void testForGetMethodWhenIndexIsOutOfRange() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 2, Size: 1");
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");

        //when
        Integer resultInt = givenArrayInt.get(2);
        String resultString = givenArrayString.get(2);
    }

    //size() Tests

    @Test
    public void testForSizeMethodArrayEmpty() {
        //given
        List<Integer> givenIntArray = getArraysMethod();
        List<String> givenStringArray = getArraysMethod();
        int expected = 0;

        //when
        int resultInt = givenIntArray.size();
        int resultString = givenStringArray.size();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForSizeMethodArrayIsNull() {
        //when
        thrown.expect(NullPointerException.class);
        List<Integer> givenArray = null;

        //then
        int result = givenArray.size();
    }

    @Test
    public void testForSizeMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        int expected = 3;

        //when
        int resultInt = givenArrayInt.size();
        int resultString = givenArrayString.size();

        //then
        assertEquals(resultInt, expected);
        assertEquals(resultString, expected);
    }

    //isEmpty() Tests

    @Test
    public void testForIsEmptyMethodWithArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        Boolean expected = false;

        //when
        givenArrayInt.add(1);
        givenArrayString.add("1");
        Boolean resultInt = givenArrayInt.isEmpty();
        Boolean resultString = givenArrayString.isEmpty();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForIsEmptyMethodWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        Boolean expected = true;

        //when
        Boolean resultInt = givenArrayInt.isEmpty();
        Boolean resultString = givenArrayString.isEmpty();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    //contains() Tests

    @Test
    @Parameters({
            "1, true",
            "0, true",
            "-1, true",
            "8, false",
            "-2, false"})
    public void testForContainsMethod(Integer givenArgument, Boolean expected) {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(0);
        givenArrayInt.add(-1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("0");
        givenArrayString.add("-1");

        //when
        Boolean resultInt = givenArrayInt.contains(givenArgument);
        Boolean resultString = givenArrayString.contains(Integer.toString(givenArgument));

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForContainsMethodWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        boolean expected = false;

        //when
        Boolean resultInt = givenArrayInt.contains(3);
        Boolean resultString = givenArrayString.contains("argument");

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    //iterator() Tests

    @Test
    @Parameters({
            "-1, true",
            "1, true",
            "0, true"})
    public void testForParametrisedIteratorMethodValidArgumentsHasNextMethod(Integer givenArgument, Boolean expected) {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(givenArgument);
        Iterator<Integer> iteratorInt = givenArrayInt.iterator();
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add(Integer.toString(givenArgument));
        Iterator<String> iteratorString = givenArrayString.iterator();

        //when
        Boolean resultInt = iteratorInt.hasNext();
        Boolean resultString = iteratorString.hasNext();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForIteratorMethodHasNextMethodWithEmptyList() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        Iterator<Integer> iteratorInt = givenArrayInt.iterator();
        List<String> givenArrayString = getArraysMethod();
        Iterator<String> iteratorString = givenArrayString.iterator();
        boolean expected = false;

        //when
        Boolean resultInt = iteratorInt.hasNext();
        Boolean resultString = iteratorString.hasNext();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    //toArray() Tests

    @Test
    public void testForToArrayMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("example");
        Object[] expectedInt = new Object[]{1};
        Object[] expectedString = new Object[]{"example"};

        //when
        Object[] resultInt = givenArrayInt.toArray();
        Object[] resultString = givenArrayString.toArray();

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    //toArray(T[] a) Tests

    @Test
    public void testForToArrayMethodWithTArgument() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("example");
        givenArrayString.add("example two");
        Integer[] expectedInt = new Integer[]{1, 2};
        String[] expectedString = new String[]{"example", "example two"};

        //when
        Integer[] resultInt = new Integer[0];
        String[] resultString = new String[0];
        resultInt = givenArrayInt.toArray(resultInt);
        resultString = givenArrayString.toArray(resultString);

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    //remove(T obj) Tests

    @Test
    public void testForRemoveMethodObjectArgumentWithValidArguments() {
        //given
        List<Long> givenArrayLong = getArraysMethod();
        givenArrayLong.add(1L);
        givenArrayLong.add(-8L);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("-8");
        List<Object> expectedLong = Arrays.asList(1L);
        List<String> expectedString = Arrays.asList("1");
        boolean expected = true;

        //when
        boolean resultLong = givenArrayLong.remove(-8L);
        boolean resultString = givenArrayString.remove("-8");

        //then
        assertEquals(expected, resultLong);
        assertEquals(expectedLong, givenArrayLong);
        assertEquals(expected, resultString);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForRemoveMethodObjectArgumentWhenArgumentIsNotInArray() {
        //given
        List<Long> givenArrayLong = getArraysMethod();
        givenArrayLong.add(3L);
        givenArrayLong.add(8L);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("example");
        givenArrayString.add("example two");
        List<Object> expectedLong = Arrays.asList(3L, 8L);
        List<String> expectedString = Arrays.asList("example", "example two");
        boolean expected = false;

        //when
        boolean resultLong = givenArrayLong.remove(1L);
        boolean resultString = givenArrayString.remove("not in array");

        //then
        assertEquals(expected, resultLong);
        assertEquals(expectedLong, givenArrayLong);
        assertEquals(expected, resultString);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForRemoveMethodObjectArgumentWhenArrayIsEmpty() {
        //given
        List<Long> givenArrayLong = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        boolean expected = false;

        //when
        boolean resultLong = givenArrayLong.remove(1L);
        boolean resultString = givenArrayString.remove("empty");

        //then
        assertEquals(expected, resultLong);
        assertEquals(expected, resultString);
    }

    //containsAll(Collection<?> c) Tests

    @Test
    public void testForContainsAllMethodWhenArgumentsAreInArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> sampleArrayInt = Arrays.asList(1, 2, 3);
        List<String> sampleArrayString = Arrays.asList("1", "2", "3");
        boolean expected = true;

        //when
        boolean resultInt = givenArrayInt.containsAll(sampleArrayInt);
        boolean resultString = givenArrayString.containsAll(sampleArrayString);

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForContainsAllMethodWhenArgumentsAreNotInArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> sampleArrayInt = Arrays.asList(4, 5, 6);
        List<String> sampleArrayString = Arrays.asList("4", "5", "6");
        boolean expected = false;

        //when
        boolean resultInt = givenArrayInt.containsAll(sampleArrayInt);
        boolean resultString = givenArrayString.containsAll(sampleArrayString);

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForContainsAllMethodWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        List<Integer> sampleArrayInt = Arrays.asList(1, 2, 3);
        List<String> sampleArrayString = Arrays.asList("1", "2", "3");
        boolean expected = false;

        //when
        boolean resultInt = givenArrayInt.containsAll(sampleArrayInt);
        boolean resultString = givenArrayString.containsAll(sampleArrayString);

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForContainsAllMethodWhenArgumentsAreNull() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(null);
        givenArrayInt.add(null);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add(null);
        givenArrayString.add(null);
        List<Integer> sampleArrayInt = Arrays.asList(null, null);
        List<String> sampleArrayString = Arrays.asList(null, null);
        boolean expected = true;

        //when
        boolean resultInt = givenArrayInt.containsAll(sampleArrayInt);
        boolean resultString = givenArrayString.containsAll(sampleArrayString);

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    //addAll(Collection<? extends T> c) Tests

    @Test
    public void testForAddAllMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList(3, 4);
        List<String> sampleArrayString = Arrays.asList("3", "4");
        List<Integer> expectedInt = Arrays.asList(1, 2, 3, 4);
        List<String> expectedString = Arrays.asList("1", "2", "3", "4");

        //when
        givenArrayInt.addAll(sampleArrayInt);
        givenArrayString.addAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddAllMethodWhenGivenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        List<Integer> sampleArrayInt = Arrays.asList(1, null);
        List<String> sampleArrayString = Arrays.asList("1", null);
        List<Integer> expectedInt = Arrays.asList(1, null);
        List<String> expectedString = Arrays.asList("1", null);

        //when
        givenArrayInt.addAll(sampleArrayInt);
        givenArrayString.addAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddAllMethodWhenAddedArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList();
        List<String> sampleArrayString = Arrays.asList();
        List<Integer> expectedInt = Arrays.asList(1, 2);
        List<String> expectedString = Arrays.asList("1", "2");

        //when
        givenArrayInt.addAll(sampleArrayInt);
        givenArrayString.addAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    //addAll(int index, Collection<? extends T> c) Tests

    @Test
    public void testForAddAllMethodWithIndexWhenArgumentsAreValid() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(4);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("4");
        List<Integer> sampleArrayInt = Arrays.asList(2, 3);
        List<String> sampleArrayString = Arrays.asList("2", "3");
        List<Integer> expectedInt = Arrays.asList(1, 2, 3, 4);
        List<String> expectedString = Arrays.asList("1", "2", "3", "4");

        //when
        givenArrayInt.addAll(1, sampleArrayInt);
        givenArrayString.addAll(1, sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForAddAllMethodWithIndexWhenArgumentsAreNull() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(null);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add(null);
        List<Integer> sampleArrayInt = Arrays.asList(null, null);
        List<String> sampleArrayString = Arrays.asList(null, null);
        List<Integer> expectedInt = Arrays.asList(null, null, null);
        List<String> expectedString = Arrays.asList(null, null, null);

        //when
        boolean expected = true;
        boolean resultInt = givenArrayInt.addAll(1, sampleArrayInt);
        boolean resultString = givenArrayString.addAll(1, sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForAddAllMethodWithIndexWhenGivenIndexIsOutOfRange() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 5, Size: 1");
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        List<Integer> sampleArrayInt = Arrays.asList(2);
        List<String> sampleArrayString = Arrays.asList("2");

        //when
        givenArrayInt.addAll(5, sampleArrayInt);
        givenArrayString.addAll(5, sampleArrayString);
    }

    @Test
    public void testForAddAllMethodWithIndexWhenGivenIndexIsOnLastPositionOfArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList(3, 4);
        List<String> sampleArrayString = Arrays.asList("3", "4");
        List<Integer> expectedInt = Arrays.asList(1, 2, 3, 4);
        List<String> expectedString = Arrays.asList("1", "2", "3", "4");

        //when
        boolean expected = true;
        boolean resultInt = givenArrayInt.addAll(2, sampleArrayInt);
        boolean resultString = givenArrayString.addAll(2, sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    //removeAll(Collection<?> c) Tests

    @Test
    public void testForRemoveAllMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> sampleArrayInt = Arrays.asList(1, 3, 5);
        List<String> sampleArrayString = Arrays.asList("1", "3", "5");
        List<Integer> expectedInt = Arrays.asList(2);
        List<String> expectedString = Arrays.asList("2");

        //when
        boolean expected = true;
        boolean resultInt = givenArrayInt.removeAll(sampleArrayInt);
        boolean resultString = givenArrayString.removeAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForRemoveAllMethodWhenAllArgumentsAreEqual() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList(1, 2);
        List<String> sampleArrayString = Arrays.asList("1", "2");
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        boolean expected = true;
        boolean resultInt = givenArrayInt.removeAll(sampleArrayInt);
        boolean resultString = givenArrayString.removeAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForRemoveAllMethodWhenNoneOfArgumentsAreEqual() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList(3, 4);
        List<String> sampleArrayString = Arrays.asList("3", "4");
        List<Integer> expectedInt = Arrays.asList(1, 2);
        List<String> expectedString = Arrays.asList("1", "2");

        //when
        boolean expected = false;
        boolean resultInt = givenArrayInt.removeAll(sampleArrayInt);
        boolean resultString = givenArrayString.removeAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    //retainAll(Collection<?> c) Tests

    @Test
    public void testForRetainAllMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> sampleArrayInt = Arrays.asList(1);
        List<String> sampleArrayString = Arrays.asList("1");
        List<Integer> expectedInt = Arrays.asList(1);
        List<String> expectedString = Arrays.asList("1");

        //when
        boolean expected = true;
        boolean resultInt = givenArrayInt.retainAll(sampleArrayInt);
        boolean resultString = givenArrayString.retainAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForRetainAllMethodWhenArraysAreEqual() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList(1, 2);
        List<String> sampleArrayString = Arrays.asList("1", "2");
        List<Integer> expectedInt = Arrays.asList(1, 2);
        List<String> expectedString = Arrays.asList("1", "2");

        //when
        boolean expected = false;
        boolean resultInt = givenArrayInt.retainAll(sampleArrayInt);
        boolean resultString = givenArrayString.retainAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForRetainAllMethodWhenNoneOfArgumentsAreEqual() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> sampleArrayInt = Arrays.asList(3);
        List<String> sampleArrayString = Arrays.asList("3");
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        boolean expected = true;
        boolean resultInt = givenArrayInt.retainAll(sampleArrayInt);
        boolean resultString = givenArrayString.retainAll(sampleArrayString);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expected, resultInt);
        assertEquals(expectedString, givenArrayString);
        assertEquals(expected, resultString);
    }

    //clear() Tests

    @Test
    public void testForClearMethodWhenArrayStoresData() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        givenArrayInt.clear();
        givenArrayString.clear();

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForClearMethodWhenArrayStoresNull() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(null);
        givenArrayInt.add(null);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add(null);
        givenArrayString.add(null);
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        givenArrayInt.clear();
        givenArrayString.clear();

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForClearMethodWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        givenArrayInt.clear();
        givenArrayString.clear();

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    //remove(int index) Tests

    @Test
    public void testForRemoveMethodIndexWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> expectedInt = Arrays.asList(2, 3);
        List<String> expectedString = Arrays.asList("2", "3");

        //when
        givenArrayInt.remove(0);
        givenArrayString.remove(0);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForRemoveMethodIndexWhenRemovedIsLastIndex() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> expectedInt = Arrays.asList(1, 2);
        List<String> expectedString = Arrays.asList("1", "2");

        //when
        givenArrayInt.remove(2);
        givenArrayString.remove(2);

        //then
        assertEquals(expectedInt, givenArrayInt);
        assertEquals(expectedString, givenArrayString);
    }

    @Test
    public void testForRemoveMethodIndexWhenArrayIsEmpty() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 1, Size: 0");
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        givenArrayInt.remove(1);
        givenArrayString.remove(1);
    }

    @Test
    public void testForRemoveMethodIndexWhenGivenIndexIsOutOfRange() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 4, Size: 2");
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        givenArrayInt.remove(4);
        givenArrayString.remove(4);
    }

    //indexOf(Object o) Tests

    @Test
    @Parameters({
            "1, 2",
            "-1, 0",
            "0, 1",
            "4, -1",
            "-9, -1"})
    public void testForParametrisedIndexOfObjectArgument(Integer givenArgument, int expected) {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(-1);
        givenArrayInt.add(0);
        givenArrayInt.add(1);
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("-1");
        givenArrayString.add("0");
        givenArrayString.add("1");
        givenArrayString.add("1");

        //when
        int resultInt = givenArrayInt.indexOf(givenArgument);
        int resultString = givenArrayString.indexOf(Integer.toString(givenArgument));

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForIndexOfObjectWhenSearchedArgumentIsNull() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(-1);
        givenArrayInt.add(null);
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("-1");
        givenArrayString.add(null);
        givenArrayString.add("1");
        int expected = 1;

        //when
        int resultInt = givenArrayInt.indexOf(null);
        int resultString = givenArrayString.indexOf(null);

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForIndexOfObjectWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        int expected = -1;

        //when
        int resultInt = givenArrayInt.indexOf(2);
        int resultString = givenArrayString.indexOf("2");

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    //lastIndexOf(Object o) Tests

    @Test
    @Parameters({
            "1, 4",
            "-1, 1",
            "0, 2",
            "4, -1",
            "-9, -1"})
    public void testForParametrisedLastIndexOfObjectArgument(Integer givenArgument, int expected) {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(-1);
        givenArrayInt.add(-1);
        givenArrayInt.add(0);
        givenArrayInt.add(1);
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("-1");
        givenArrayString.add("-1");
        givenArrayString.add("0");
        givenArrayString.add("1");
        givenArrayString.add("1");

        //when
        int resultInt = givenArrayInt.lastIndexOf(givenArgument);
        int resultString = givenArrayString.lastIndexOf(Integer.toString(givenArgument));

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForLastIndexOfObjectWhenSearchedArgumentIsNull() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(-1);
        givenArrayInt.add(null);
        givenArrayInt.add(null);
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("-1");
        givenArrayString.add(null);
        givenArrayString.add(null);
        givenArrayString.add("1");
        int expected = 2;

        //when
        int resultInt = givenArrayInt.lastIndexOf(null);
        int resultString = givenArrayString.lastIndexOf(null);

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForLastIndexOfObjectWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        int expected = -1;

        //when
        int resultInt = givenArrayInt.indexOf(2);
        int resultString = givenArrayString.indexOf("2");

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    //listIterator() Tests

    @Test
    public void testForListIteratorValidArgumentsHasNextMethod() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        boolean resultInt = iteratorInt.hasNext();
        iteratorString = givenArrayString.listIterator();
        boolean resultString = iteratorString.hasNext();
        boolean expected = true;

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorHasNextMethodWhenEndOfArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        iteratorInt.next();
        boolean resultInt = iteratorInt.hasNext();
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        boolean resultString = iteratorString.hasNext();
        boolean expected = false;

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorHasNextWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        boolean resultInt = iteratorInt.hasNext();
        iteratorString = givenArrayString.listIterator();
        boolean resultString = iteratorString.hasNext();
        boolean expected = false;

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorValidArgumentsNextMethod() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        int resultInt = iteratorInt.next();
        iteratorString = givenArrayString.listIterator();
        String resultString = iteratorString.next();
        int expectedInt = 1;
        String expectedString = "1";

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForListIteratorNextMethodWhenIteratorOutOfRange() {
        //given
        thrown.expect(NoSuchElementException.class);
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        iteratorInt.next();
        iteratorInt.next();
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        iteratorString.next();
    }

    @Test
    public void testForListIteratorValidArgumentsHasPreviousMethod() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        iteratorInt.next();
        iteratorInt.next();
        boolean resultInt = iteratorInt.hasPrevious();
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        iteratorString.next();
        boolean resultString = iteratorString.hasPrevious();
        boolean expected = true;

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorHasPreviousMethodWhenIteratorIsNotMoved() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        boolean resultInt = iteratorInt.hasPrevious();
        iteratorString = givenArrayString.listIterator();
        boolean resultString = iteratorString.hasPrevious();
        boolean expected = false;

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorHasPreviousMethodWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        iteratorString = givenArrayString.listIterator();
        boolean resultInt = iteratorInt.hasPrevious();
        boolean resultString = iteratorString.hasPrevious();
        boolean expected = false;

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorPreviousMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        Integer expectedInt = 1;
        String expectedString = "1";

        //when
        iteratorInt = givenArrayInt.listIterator();
        iteratorInt.next();
        iteratorInt.next();
        iteratorInt.previous();
        iteratorString = givenArrayString.listIterator();
        iteratorString.next();
        iteratorString.next();
        iteratorString.previous();
        Integer resultInt = iteratorInt.previous();
        String resultString = iteratorString.previous();

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForListIteratorPreviousMethodWhenArrayIsEmpty() {
        //given
        thrown.expect(NoSuchElementException.class);
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator();
        iteratorString = givenArrayString.listIterator();
        iteratorInt.previous();
        iteratorString.previous();
    }

    //listIterator(int index) tests

    @Test
    public void testForListIteratorIndexArgumentValidArgumentsHasNextMethod() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        boolean expected = true;

        //when
        iteratorInt = givenArrayInt.listIterator(1);
        iteratorString = givenArrayString.listIterator(1);
        boolean resultInt = iteratorInt.hasNext();
        boolean resultString = iteratorString.hasNext();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentHasNextMethodWhenGivenIndexIsLastElementOfArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        boolean expected = false;

        //when
        iteratorInt = givenArrayInt.listIterator(1);
        iteratorString = givenArrayString.listIterator(1);
        boolean resultInt = iteratorInt.hasNext();
        boolean resultString = iteratorString.hasNext();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentHasNextMethodWhenGivenIndexIsOutOfRange() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 3");
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        boolean expected = false;

        //when
        iteratorInt = givenArrayInt.listIterator(3);
        iteratorString = givenArrayString.listIterator(3);
    }

    @Test
    public void testForListIteratorIndexArgumentValidArgumentsNextMethod() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        int expectedInt = 2;
        String expectedString = "2";

        //when
        iteratorInt = givenArrayInt.listIterator(1);
        iteratorString = givenArrayString.listIterator(1);
        String resultString = iteratorString.next();
        int resultInt = iteratorInt.next();

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentNextMethodWhenGivenIndexIsLast() {
        //given
        thrown.expect(NoSuchElementException.class);
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator(2);
        iteratorString = givenArrayString.listIterator(2);
        iteratorInt.next();
        iteratorString.next();
    }

    @Test
    public void testForListIteratorIndexArgumentNextMethodWhenGivenArrayIsEmpty() {
        //given
        thrown.expect(NoSuchElementException.class);
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator(0);
        iteratorString = givenArrayString.listIterator(0);
        iteratorInt.next();
        iteratorString.next();
    }

    @Test
    public void testForListIteratorIndexArgumentHasPreviousMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        boolean expected = true;

        //when
        iteratorInt = givenArrayInt.listIterator(2);
        iteratorString = givenArrayString.listIterator(2);
        boolean resultInt = iteratorInt.hasPrevious();
        boolean resultString = iteratorString.hasPrevious();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentHasPreviousMethodWhenIndexIsAtBeginning() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        boolean expected = false;

        //when
        iteratorInt = givenArrayInt.listIterator(0);
        iteratorString = givenArrayString.listIterator(0);
        boolean resultInt = iteratorInt.hasPrevious();
        boolean resultString = iteratorString.hasPrevious();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentHasPreviousMethodWhenArrayIsEmpty() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        boolean expected = false;

        //when
        iteratorInt = givenArrayInt.listIterator(0);
        iteratorString = givenArrayString.listIterator(0);
        boolean resultInt = iteratorInt.hasPrevious();
        boolean resultString = iteratorString.hasPrevious();

        //then
        assertEquals(expected, resultInt);
        assertEquals(expected, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentPreviousMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;
        int expectedInt = 2;
        String expectedString = "2";

        //when
        iteratorInt = givenArrayInt.listIterator(2);
        iteratorString = givenArrayString.listIterator(2);
        int resultInt = iteratorInt.previous();
        String resultString = iteratorString.previous();

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForListIteratorIndexArgumentPreviousMethodWithoutMovingIterator() {
        //given
        thrown.expect(NoSuchElementException.class);
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator(0);
        iteratorString = givenArrayString.listIterator(0);
        iteratorInt.previous();
        iteratorString.previous();
    }

    @Test
    public void testForListIteratorIndexArgumentPreviousMethodWhenArrayIsEmpty() {
        //given
        thrown.expect(NoSuchElementException.class);
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();
        ListIterator<Integer> iteratorInt = null;
        ListIterator<String> iteratorString = null;

        //when
        iteratorInt = givenArrayInt.listIterator(0);
        iteratorString = givenArrayString.listIterator(0);
        iteratorInt.previous();
        iteratorString.previous();
    }

    //subList(int fromIndex, int toIndex) Tests

    @Test
    public void testForSubListMethodWithValidArguments() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> expectedInt = Arrays.asList(2);
        List<String> expectedString = Arrays.asList("2");

        //when
        List<Integer> resultInt = givenArrayInt.subList(1, 2);
        List<Integer> resultString = givenArrayInt.subList(1, 2);

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForSubListMethodWhenCopyingFullArray() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        givenArrayInt.add(3);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        givenArrayString.add("3");
        List<Integer> expectedInt = Arrays.asList(1, 2, 3);
        List<String> expectedString = Arrays.asList("1", "2", "3");

        //when
        List<Integer> resultInt = givenArrayInt.subList(0, givenArrayInt.size());
        List<String> resultString = givenArrayString.subList(0, givenArrayString.size());

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForSubListMethodWhenSecondArgumentIsOutOfRange() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("toIndex = 2");
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");

        //when
        List<Integer> resultInt = givenArrayInt.subList(0, 2);
        List<String> resultString = givenArrayString.subList(0, 2);
    }

    @Test
    public void testForSubListMethodWhenBothArgumentsAreEqual() {
        //given
        List<Integer> givenArrayInt = getArraysMethod();
        givenArrayInt.add(1);
        givenArrayInt.add(2);
        List<String> givenArrayString = getArraysMethod();
        givenArrayString.add("1");
        givenArrayString.add("2");
        List<Integer> expectedInt = Arrays.asList();
        List<String> expectedString = Arrays.asList();

        //when
        List<Integer> resultInt = givenArrayInt.subList(1, 1);
        List<String> resultString = givenArrayString.subList(1, 1);

        //then
        assertEquals(expectedInt, resultInt);
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testForSubListMethodWhenGivenArrayIsEmpty() {
        //given
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("toIndex = 2");
        List<Integer> givenArrayInt = getArraysMethod();
        List<String> givenArrayString = getArraysMethod();

        //when
        List<Integer> resultInt = givenArrayInt.subList(0, 2);
        List<String> resultString = givenArrayString.subList(0, 2);
    }
}