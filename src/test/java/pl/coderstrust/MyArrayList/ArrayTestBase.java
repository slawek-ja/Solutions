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

    public abstract List<String> getArrayList();

    private List<String> getArrayWithValues(List<String> add){
        List<String> result = getArrayList();
        result.addAll(add);
        return result;
    }

    @Test
    public void testForAddMethod() {
        //given
        List<String> givenList = getArrayList();
        Object[] expectedArray = new Object[]{"example", null, ""};

        //when
        givenList.add("example");
        givenList.add(null);
        givenList.add("");
        Object[] actualArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    @Parameters(method = "addValuesForAddMethodWithIndex")
    public void testForAddMethodWithIndex(List<String> givenList, int index, String element, Object[] expected) {
        //when
        givenList.add(index, element);

        //then
        assertArrayEquals(expected, givenList.toArray());
    }

    private Object[] addValuesForAddMethodWithIndex() {
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","3","4")), 1, "2", new Object[]{"1","2","3","4"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-3","-4")), 1, "-2", new Object[]{"-1","-2","-3","-4"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","1")), 1, "0", new Object[]{"-1","0","1"}},
            new Object[]{getArrayWithValues(Arrays.asList("2","3")), 0, "1", new Object[]{"1","2","3"}},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), 2, "3", new Object[]{"1","2","3"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","1")), 1, null, new Object[]{"-1",null,"1"}}
        };
    }

    @Test
    @Parameters(method = "getInvalidArgumentsForAddMethodWithIndex")
    public void testForAddMethodWithInvalidIndex(List<String> givenList, int index, String element) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(String.format("Index: %d, Size: %d", index, givenList.size()));
        givenList.add(index, element);
    }

    private Object[] getInvalidArgumentsForAddMethodWithIndex() {
        return new Object[] {
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 5, "4"},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), -1, "4"}
        };
    }

    @Test
    @Parameters(method = "getArgumentsForAddAllMethodWithGenerics")
    public void testForAddAllMethodWithGenerics(List<String> givenList, Collection<String> collectionToAdd, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.addAll(collectionToAdd);
        Object[] resultArray = givenList.toArray();

        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getArgumentsForAddAllMethodWithGenerics() {
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Arrays.asList("3","4"), new Object[]{"1","2","3","4"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2")), Arrays.asList("-3","-4"), new Object[]{"-1","-2","-3","-4"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("0","0")), Collections.singletonList("0"), new Object[]{"0","0","0"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Arrays.asList("1","2"), new Object[]{"1","2","1","2"}, true},
            new Object[]{getArrayWithValues(Collections.singletonList(null)), Collections.singletonList(null), new Object[]{null, null}, true},
            new Object[]{getArrayWithValues(Collections.emptyList()), Arrays.asList("1","2"), new Object[]{"1","2"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Collections.emptyList(), new Object[]{"1","2"}, false},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Arrays.asList(1, 2), new Object[]{"1","2", 1, 2}, true}
        };
    }

    @Test
    @Parameters(method = "getArgumentsForAddAllMethodWithIndex")
    public void testForAddAllMethodWithIndex(List<String> givenList, Collection<String> collectionToAdd, int index, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.addAll(index, collectionToAdd);
        Object[] resultArray = givenList.toArray();

        //then
        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getArgumentsForAddAllMethodWithIndex() {
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1", "2")), Arrays.asList("3","4"), 2, new Object[]{"1","2","3","4"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("-1", "-2")), Arrays.asList("-3","-4"), 2, new Object[]{"-1","-2","-3","-4"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("0", "0")), Collections.singletonList("0"), 1, new Object[]{"0","0","0"}, true},
            new Object[]{getArrayWithValues(Arrays.asList(null, null)), Collections.singletonList(null), 1, new Object[]{null, null, null}, true},
            new Object[]{getArrayWithValues(Arrays.asList("2","3")), Collections.singletonList("1"), 0, new Object[]{"1","2","3"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","3")), Collections.singletonList("2"), 1, new Object[]{"1","2","3"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Collections.emptyList(), 0, new Object[]{"1","2"}, false},
            new Object[]{getArrayWithValues(Collections.emptyList()), Arrays.asList("1","2"), 0, new Object[]{"1","2"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","4")), Arrays.asList(2,3), 1, new Object[]{"1",2,3,"4"}, true}
        };
    }

    @Test
    @Parameters(method = "getArgumentsForAddAllMethodWithInvalidIndex")
    public void testForAddAllMethodWithInvalidIndex(List<String> givenList, Collection<String> collectionToAdd, int index, Class expectedExceptionType) {
        //when
        thrown.expect(expectedExceptionType);
        thrown.expectMessage(String.format("Index: %d, Size: %d", index, givenList.size()));
        givenList.addAll(index, collectionToAdd);
    }

    private Object[] getArgumentsForAddAllMethodWithInvalidIndex(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Arrays.asList("4","5"), 5, IndexOutOfBoundsException.class},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Arrays.asList("4","5"), -5, IndexOutOfBoundsException.class}
        };
    }

    @Test
    @Parameters(method = "getValuesForClearMethod")
    public void testForClearMethod(List<String> givenList) {
        //given
        Object[] expectedArray = new Object[0];

        //when
        givenList.clear();
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getValuesForClearMethod(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2"))},
            new Object[]{getArrayWithValues(Collections.singletonList("1"))},
            new Object[]{getArrayWithValues(Collections.singletonList("0"))},
            new Object[]{getArrayWithValues(Arrays.asList(null, null))},
            new Object[]{getArrayWithValues(Collections.emptyList())}
        };
    }

    @Test
    @Parameters(method = "addValuesForContainsMethod")
    public void testForContainsMethod(List<String> givenList, String element, boolean expected) {
        assertEquals(expected, givenList.contains(element));
    }

    private Object[] addValuesForContainsMethod() {
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), "2", true},
                new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), "-2", true},
                new Object[]{getArrayWithValues(Arrays.asList("-1","0","1")), "0", true},
                new Object[]{getArrayWithValues(Arrays.asList(null, null, null)), null, true},
                new Object[]{getArrayWithValues(Collections.emptyList()), "1", false},
                new Object[]{getArrayWithValues(Collections.singletonList("1")), "0", false},
                new Object[]{getArrayWithValues(Arrays.asList("1", "2", "3")), "4", false}
        };
    }

    @Test
    @Parameters(method = "getValuesForContainsAllMethod")
    public void testForContainsAllMethod(List<String> givenList, Collection<String> containsCollection, boolean expected) {
        //when
        boolean result = givenList.containsAll(containsCollection);

        //then
        assertEquals(expected, result);
    }

    private Object[] getValuesForContainsAllMethod() {
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Arrays.asList("2","3"), true},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), Arrays.asList("-2","-3"), true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Collections.singletonList("2"), true},
            new Object[]{getArrayWithValues(Arrays.asList("1",null,"3")), Collections.singletonList(null), true},
            new Object[]{getArrayWithValues(Arrays.asList("-1","0","1")), Arrays.asList("0", "-1"), true},
            new Object[]{getArrayWithValues(Arrays.asList("-1","0","1")), Collections.singletonList("5"), false},
            new Object[]{getArrayWithValues(Collections.emptyList()), Collections.singletonList("3"), false},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Collections.emptyList(), true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Arrays.asList(1, 2, 3), false}
        };
    }

    @Test
    @Parameters(method = "getValuesForEqualsMethod")
    public void testForEqualsMethod(List<String> givenList, List<String> arrayToCompare, boolean expected) {
        assertEquals(expected, givenList.equals(arrayToCompare));
    }

    private Object[] getValuesForEqualsMethod(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Arrays.asList("1","2","3"), true},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), Arrays.asList("-1","-2","-3"), true},
            new Object[]{getArrayWithValues(Collections.singletonList("0")), Collections.singletonList("0"), true},
            new Object[]{getArrayWithValues(Arrays.asList(null, null)), Arrays.asList(null, null), true},
            new Object[]{getArrayWithValues(Collections.emptyList()), Collections.emptyList(), true},
            new Object[]{getArrayWithValues(Collections.emptyList()), Arrays.asList("1", "2"), false},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Collections.emptyList(), false},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Arrays.asList(1, 2), false}
        };
    }

    @Test
    @Parameters(method = "addValuesForGetMethod")
    public void testForGetMethod(List<String> givenList, int index, Object[] expected) {
        //when
        String result = givenList.get(index);

        //then
        assertEquals(expected[index], result);
    }

    private Object[] addValuesForGetMethod() {
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 1, new Object[]{"1","2","3"}},
                new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), 0, new Object[]{"-1","-2","-3"}},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 2, new Object[]{"1","2","3"}},
                new Object[]{getArrayWithValues(Arrays.asList(null, null, null)), 1, new Object[]{null, null, null}}
        };
    }

    @Test
    @Parameters(method = "getInvalidArgumentsForGetMethod")
    public void testForGetMethodWithInvalidIndex(List<String> givenList, int index, String exceptionMessage) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(exceptionMessage);
        String result = givenList.get(index);
    }

    private Object[] getInvalidArgumentsForGetMethod(){
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 5, "Index: 5, Size: 3"},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 3, "Index: 3, Size: 3"},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), -5, "-5"}
        };
    }

    @Test
    @Parameters(method = "getValuesForHashCodeMethod")
    public void testForHashCodeMethod(List<String> givenList, Object[] arrayToCompare) {
        //when
        Object[] resultArray = givenList.toArray();
        int result = Arrays.hashCode(resultArray);
        int expected = Arrays.hashCode(arrayToCompare);

        //then
        assertEquals(expected, result);
    }

    private Object[] getValuesForHashCodeMethod(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), new Object[]{"1","2","3"}},
            new Object[]{getArrayWithValues(Collections.singletonList("-1")), new Object[]{"-1"}},
            new Object[]{getArrayWithValues(Arrays.asList(null, null)), new Object[]{null, null}},
            new Object[]{getArrayWithValues(Arrays.asList("0","0")), new Object[]{"0","0"}},
            new Object[]{getArrayWithValues(Collections.emptyList()), new Object[0]}
        };
    }

    @Test
    @Parameters(method = "getValuesForHashCodeMethodWithInvalidArguments")
    public void testForHashCodeMethodWithInvalidArguments(List<String> givenList, Object[] arrayToCompare) {
        //when
        Object[] resultArray = givenList.toArray();
        int result = Arrays.hashCode(resultArray);
        int expected = Arrays.hashCode(arrayToCompare);

        //then
        assertNotEquals(expected, result);
    }

    private Object[] getValuesForHashCodeMethodWithInvalidArguments(){
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), new Object[]{"1","2"}},
                new Object[]{getArrayWithValues(Collections.singletonList("-1")), new Object[]{"-1","-2"}},
                new Object[]{getArrayWithValues(Collections.singletonList(null)), new Object[]{null, null}},
                new Object[]{getArrayWithValues(Arrays.asList("0","0")), new Object[]{"0","0","0"}},
                new Object[]{getArrayWithValues(Collections.emptyList()), new Object[]{"1"}},
                new Object[]{getArrayWithValues(Collections.singletonList("1")), new Object[0]},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), new Object[]{1, 2 , 3}}
        };
    }

    @Test
    @Parameters(method = "getValuesForIndexOfMethod")
    public void testForIndexOfMethod(List<String> givenList, String elementToFind, int expectedIndex) {
        assertEquals(expectedIndex, givenList.indexOf(elementToFind));
    }

    private Object[] getValuesForIndexOfMethod(){
        List<String>w = null;
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), "2", 1},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), "-1", 0},
            new Object[]{Collections.singletonList("0"), "0", 0},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","2")), "2", 1},
            new Object[]{getArrayWithValues(Arrays.asList("1", null,"2")), null, 1},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), "4", -1},
            new Object[]{getArrayWithValues(Collections.emptyList()), "2", -1}
        };
    }

    @Test
    @Parameters(method = "addValuesForIsEmptyMethod")
    public void testForIsEmptyMethod(List<String> givenList, boolean expected) {
        assertEquals(givenList.isEmpty(), expected);
    }

    private Object[] addValuesForIsEmptyMethod() {
        return new Object[]{
            new Object[]{getArrayWithValues(Collections.emptyList()), true},
            new Object[]{getArrayWithValues(Collections.singletonList("1")), false},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), false},
            new Object[]{getArrayWithValues(Arrays.asList("-1","2","-3")), false},
            new Object[]{getArrayWithValues(Collections.singletonList(null)), false}
        };
    }

    @Test
    @Parameters(method = "getValuesForLastIndexOfMethod")
    public void testForLastIndexOfMethod(List<String> givenList, String elementToFind, int expectedIndex) {
        assertEquals(expectedIndex, givenList.lastIndexOf(elementToFind));
    }

    private Object[] getValuesForLastIndexOfMethod(){
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","2","2")), "2", 2},
                new Object[]{getArrayWithValues(Arrays.asList("-1","-1","-3")), "-1", 1},
                new Object[]{Collections.singletonList("0"), "0", 0},
                new Object[]{getArrayWithValues(Arrays.asList("2","2","2")), "2", 2},
                new Object[]{getArrayWithValues(Arrays.asList("1", null,null)), null, 2},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), "4", -1},
                new Object[]{getArrayWithValues(Collections.emptyList()), "2", -1}
        };
    }

    @Test
    @Parameters(method = "getValuesForRemoveMethodWithIndex")
    public void testForRemoveMethodWithIndex(List<String> givenList, int index, Object[] expectedArray) {
        //when
        givenList.remove(index);
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getValuesForRemoveMethodWithIndex(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","3","2")), 1, new Object[]{"1","2"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-2")), 1, new Object[]{"-1","-2"}},
            new Object[]{getArrayWithValues(Arrays.asList("0","1","2")), 0, new Object[]{"1","2"}},
            new Object[]{getArrayWithValues(Arrays.asList(null, null, null)), 2, new Object[]{null, null}},
            new Object[]{getArrayWithValues(Collections.singletonList("1")), 0, new Object[0]}
        };
    }

    @Test
    @Parameters(method = "getValuesForRemoveMethodWithInvalidIndex")
    public void testForRemoveMethodWithInvalidIndex(List<String> givenList, int index, Class expectedException, String exceptionMessage) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(exceptionMessage);
        givenList.remove(index);
    }

    private Object[] getValuesForRemoveMethodWithInvalidIndex() {
        return new Object[] {
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 4, IndexOutOfBoundsException.class, "Index: 4, Size: 3"},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), -4, IndexOutOfBoundsException.class, "-4"},
                new Object[]{getArrayWithValues(Collections.emptyList()), 0, IndexOutOfBoundsException.class, ""}
        };
    }

    @Test
    @Parameters(method = "getValuesForRemoveMethodWithObject")
    public void testForRemoveMethodWithObject(List<String> givenList, String elementToRemove, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.remove(elementToRemove);
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
        assertEquals(expectedResult, resultBoolean);
    }

    private Object[] getValuesForRemoveMethodWithObject() {
        return new Object[] {
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), "2", new Object[]{"1","3"}, true},
                new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), "-3", new Object[]{"-1","-2"}, true},
                new Object[]{getArrayWithValues(Arrays.asList("0","1","2")), "0", new Object[]{"1","2"}, true},
                new Object[]{getArrayWithValues(Collections.singletonList(null)), null, new Object[0], true},
                new Object[]{getArrayWithValues(Collections.emptyList()), "1", new Object[0], false},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), "4", new Object[]{"1","2","3"}, false},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3","1","5","1")), "1", new Object[]{"2","3","1","5","1"}, true}
        };
    }

    @Test
    @Parameters(method = "ValuesForRemoveAllMethod")
    public void testForRemoveAllMethod(List<String> givenList, Collection<String> collectionToRemove, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.removeAll(collectionToRemove);
        Object[] resultArray = givenList.toArray();

        //then
        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] ValuesForRemoveAllMethod(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","3","4","2")), Arrays.asList("3","4"), new Object[]{"1","2"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-3","-4","-2")), Arrays.asList("-3","-4"), new Object[]{"-1","-2"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","0","2")), Collections.singletonList("0"), new Object[]{"1","2"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2", null)), Collections.singletonList(null), new Object[]{"1","2"}, true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Arrays.asList("1","2"), new Object[0], true},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Collections.emptyList(), new Object[]{"1","2"}, false},
            new Object[]{getArrayWithValues(Collections.emptyList()), Collections.emptyList(), new Object[0], false},
            new Object[]{getArrayWithValues(Collections.emptyList()), Collections.singletonList("1"), new Object[0], false},
            new Object[]{getArrayWithValues(Arrays.asList("1","2")), Arrays.asList(1,2), new Object[]{"1","2"}, false}
        };
    }

    @Test
    @Parameters(method = "getValuesForRetainAllMethod")
    public void testForRetainAllMethod(List<String> givenList, Collection<String> collectionToRetain, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.retainAll(collectionToRetain);
        Object[] resultArray = givenList.toArray();

        //then
        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getValuesForRetainAllMethod() {
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","3","4","2")), Arrays.asList("1","2"), new Object[]{"1","2"}, true},
                new Object[]{getArrayWithValues(Arrays.asList("1","-3","2")), Arrays.asList("1","2"), new Object[]{"1","2"}, true},
                new Object[]{getArrayWithValues(Arrays.asList("1",null,"2")), Collections.singletonList(null), new Object[]{null}, true},
                new Object[]{getArrayWithValues(Arrays.asList("1","0","2")), Collections.singletonList("0"), new Object[]{"0"}, true},
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), Collections.emptyList(), new Object[0], true},
                new Object[]{getArrayWithValues(Collections.emptyList()), Collections.singletonList("1"), new Object[0], false},
                new Object[]{getArrayWithValues(Arrays.asList("1","2")),Arrays.asList("3","4"), new Object[0], true},
                new Object[]{getArrayWithValues(Arrays.asList("1","2")),Arrays.asList("1","2"), new Object[]{"1","2"}, false},
                new Object[]{getArrayWithValues(Arrays.asList("1","2")),Arrays.asList(1, 2), new Object[0], true}
        };
    }

    @Test
    @Parameters(method = "getValuesForSetMethod")
    public void testForSetMethod(List<String> givenList, int index, String element, Object[] expectedArray) {
        //when
        givenList.set(index, element);
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getValuesForSetMethod(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","3")), 1, "2", new Object[]{"1","2"}},
            new Object[]{getArrayWithValues(Arrays.asList("-5","-2")), 0, "-1", new Object[]{"-1","-2"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","3","1")), 1, "0", new Object[]{"-1","0","1"}},
            new Object[]{getArrayWithValues(Arrays.asList("1","-3")), 1, null, new Object[]{"1", null}},
            new Object[]{getArrayWithValues(Arrays.asList("1","-3")), 1, 1, new Object[]{"1", "1"}}
        };
    }

    @Test
    @Parameters(method = "getValuesForSetMethodWithInvalidArguments")
    public void testForSetMethodWithInvalidArguments(List<String> givenList, int index, String element, Class expectedExceptionType, String exceptionMessage) {
        //when
        thrown.expect(expectedExceptionType);
        thrown.expectMessage(exceptionMessage);
        givenList.set(index, element);
    }

    private Object[] getValuesForSetMethodWithInvalidArguments(){
        return new Object[]{
                new Object[]{getArrayWithValues(Arrays.asList("1","2")), -7, "3", ArrayIndexOutOfBoundsException.class, "-7"},
                new Object[]{getArrayWithValues(Arrays.asList("1","2")), 7, "3", IndexOutOfBoundsException.class, "Index: 7, Size: 2"},
                new Object[]{getArrayWithValues(Arrays.asList("1","2")), 2, "3", IndexOutOfBoundsException.class, "Index: 2, Size: 2"}
        };
    }

    @Test
    @Parameters(method = "getValuesForSizeMethod")
    public void testForSizeMethod(List<String> givenList, int expectedSize) {
        assertEquals(givenList.size(), expectedSize);
    }

    private Object[] getValuesForSizeMethod() {
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 3},
            new Object[]{getArrayWithValues(Arrays.asList("-1","0","1")), 3},
            new Object[]{getArrayWithValues(Collections.singletonList("0")), 1},
            new Object[]{getArrayWithValues(Arrays.asList(null, null)), 2},
            new Object[]{getArrayWithValues(Collections.emptyList()), 0}
        };
    }

    @Test
    @Parameters(method = "getValuesForSubListMethod")
    public void testForSubListMethod(List<String> givenList, int fromIndex, int toIndex, Object[] expectedArray) {
        //when

        Object[] resultArray = (givenList.subList(fromIndex, toIndex)).toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] getValuesForSubListMethod(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 1, 3, new Object[]{"2","3"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), 1, 2, new Object[]{"-2"}},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 0, 3, new Object[]{"1","2","3"}},
            new Object[]{getArrayWithValues(Arrays.asList("0","0","0")), 0, 3, new Object[]{"0","0","0"}},
            new Object[]{getArrayWithValues(Collections.singletonList(null)), 0, 1, new Object[]{null}},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 1, 1, new Object[0]},
            new Object[]{getArrayWithValues(Collections.emptyList()), 0, 0, new Object[0]}
        };
    }

    @Test
    @Parameters(method = "getValuesForSubListMethodWithInvalidIndexes")
    public void testForSubListMethodWithInvalidIndexes(List<String> givenList, int fromIndex, int toIndex, Class expectedExceptionType, String exceptionMessage) {
        //when
        thrown.expect(expectedExceptionType);
        thrown.expectMessage(exceptionMessage);
        List<String> resultString = givenList.subList(fromIndex, toIndex);
    }

    private Object[] getValuesForSubListMethodWithInvalidIndexes(){
        return new Object[]{
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), -1, 2, IndexOutOfBoundsException.class, "fromIndex = -1"},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 0, 5, IndexOutOfBoundsException.class, "toIndex = 5"},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), 5, 2, IllegalArgumentException.class, "fromIndex(5) > toIndex(2)"},
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), -7, 5, IndexOutOfBoundsException.class, "fromIndex = -7"}
        };
    }

    @Test
    @Parameters(method = "addValuesForToArrayMethod")
    public void testForToArrayMethod(List<String> givenList, Object[] expectedArray) {
        //when
        Object[] result = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, result);
    }

    private Object[] addValuesForToArrayMethod() {
        return new Object[] {
            new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), new Object[]{"1","2","3"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), new Object[]{"-1","-2","-3"}},
            new Object[]{getArrayWithValues(Arrays.asList("-1","0","1")), new Object[]{"-1","0","1"}},
            new Object[]{getArrayWithValues(Collections.singletonList(null)), new Object[]{null}},
            new Object[]{getArrayWithValues(Collections.emptyList()), new Object[0]}
        };
    }

    @Test
    @Parameters(method = "addValuesForToArrayMethodWithGenerics")
    public void testForToArrayMethodWithGenerics(List<String> givenList, String[] expected) {
        //when
        String[] result = givenList.toArray(expected);

        //then
        assertArrayEquals(expected, result);
    }

    private Object[] addValuesForToArrayMethodWithGenerics() {
        return new Object[] {
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), new String[]{"1","2","3"}},
                new Object[]{getArrayWithValues(Arrays.asList("-1","-2","-3")), new String[]{"-1","-2","-3"}},
                new Object[]{getArrayWithValues(Arrays.asList("0","0","0")), new String[]{"0","0","0"}},
                new Object[]{getArrayWithValues(Arrays.asList(null, null)), new String[]{null, null}},
                new Object[]{getArrayWithValues(Collections.emptyList()), new String[0]}
        };
    }

    @Test
    @Parameters(method = "addValuesForToArrayMethodWithInvalidGenerics")
    public void testForToArrayMethodWithInvalidGenerics(List<String> givenList, Object[] destinationArray ,Class expectedExceptionType) {
        //when
        thrown.expect(expectedExceptionType);
        destinationArray = givenList.toArray(destinationArray);
    }

    private Object[] addValuesForToArrayMethodWithInvalidGenerics() {
        return new Object[] {
                new Object[]{getArrayWithValues(Arrays.asList("1","2","3")), null, NullPointerException.class}
        };
    }
}
