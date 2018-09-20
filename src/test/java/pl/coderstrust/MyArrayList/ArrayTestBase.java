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

    private List<String> createArrayListWithValues(List<String> values){
        List<String> result = getArrayList();
        result.addAll(values);
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
    @Parameters(method = "argumentsForAddMethodWithIndex")
    public void testForAddMethodWithIndex(List<String> givenList, int index, String element, Object[] expected) {
        //when
        givenList.add(index, element);
        Object[] result = givenList.toArray();

        //then
        assertArrayEquals(expected, result);
    }

    private Object[] argumentsForAddMethodWithIndex() {
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","3","4")), 1, "2", new Object[]{"1","2","3","4"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-3","-4")), 1, "-2", new Object[]{"-1","-2","-3","-4"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","1")), 1, "0", new Object[]{"-1","0","1"}},
            new Object[]{createArrayListWithValues(Arrays.asList("2","3")), 0, "1", new Object[]{"1","2","3"}},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), 2, "3", new Object[]{"1","2","3"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","1")), 1, null, new Object[]{"-1",null,"1"}}
        };
    }

    @Test
    @Parameters(method = "argumentsForAddMethodWithInvalidIndex")
    public void testForAddMethodWithInvalidIndex(List<String> givenList, int index) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(String.format("Index: %d, Size: %d", index, givenList.size()));
        givenList.add(index, "4");
    }

    private Object[] argumentsForAddMethodWithInvalidIndex() {
        return new Object[] {
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 5},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), -1}
        };
    }

    @Test
    @Parameters(method = "argumentsForAddAllMethodWithGenerics")
    public void testForAddAllMethodWithGenerics(List<String> givenList, Collection<String> collectionToAdd, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.addAll(collectionToAdd);
        Object[] resultArray = givenList.toArray();

        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForAddAllMethodWithGenerics() {
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Arrays.asList("3","4"), new Object[]{"1","2","3","4"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2")), Arrays.asList("-3","-4"), new Object[]{"-1","-2","-3","-4"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("0","0")), Collections.singletonList("0"), new Object[]{"0","0","0"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Arrays.asList("1","2"), new Object[]{"1","2","1","2"}, true},
            new Object[]{createArrayListWithValues(Collections.singletonList(null)), Collections.singletonList(null), new Object[]{null, null}, true},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Arrays.asList("1","2"), new Object[]{"1","2"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Collections.emptyList(), new Object[]{"1","2"}, false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Arrays.asList(1, 2), new Object[]{"1","2", 1, 2}, true}
        };
    }

    @Test
    public void testForAddAllMethodWithGenericsWhenGivenArrayIsNull(){
        //given
        List<String> givenList = getArrayList();
        List<String> collectionToAdd = null;

        //when
        thrown.expect(NullPointerException.class);
        givenList.addAll(collectionToAdd);
    }

    @Test
    @Parameters(method = "argumentsForAddAllMethodWithIndex")
    public void testForAddAllMethodWithIndex(List<String> givenList, Collection<String> collectionToAdd, int index, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.addAll(index, collectionToAdd);
        Object[] resultArray = givenList.toArray();

        //then
        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForAddAllMethodWithIndex() {
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1", "2")), Arrays.asList("3","4"), 2, new Object[]{"1","2","3","4"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1", "-2")), Arrays.asList("-3","-4"), 2, new Object[]{"-1","-2","-3","-4"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("0", "0")), Collections.singletonList("0"), 1, new Object[]{"0","0","0"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList(null, null)), Collections.singletonList(null), 1, new Object[]{null, null, null}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("2","3")), Collections.singletonList("1"), 0, new Object[]{"1","2","3"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","3")), Collections.singletonList("2"), 1, new Object[]{"1","2","3"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Collections.emptyList(), 0, new Object[]{"1","2"}, false},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Arrays.asList("1","2"), 0, new Object[]{"1","2"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","4")), Arrays.asList(2,3), 1, new Object[]{"1",2,3,"4"}, true}
        };
    }

    @Test
    @Parameters(method = "argumentsForAddAllMethodWithInvalidIndex")
    public void testForAddAllMethodWithInvalidIndex(List<String> givenList, int index) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(String.format("Index: %d, Size: %d", index, givenList.size()));
        givenList.addAll(index, new ArrayList<>());
    }

    private Object[] argumentsForAddAllMethodWithInvalidIndex(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 5},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), -5}
        };
    }

    @Test
    public void testForAddAllMethodWhenAddedCollectionIsNull() {
        //given
        List<String> givenList = getArrayList();
        givenList.addAll(Arrays.asList("1","2","3"));
        List<String> collectionToAdd = null;

        //when
        thrown.expect(NullPointerException.class);
        givenList.addAll(2, collectionToAdd);
    }

    @Test
    @Parameters(method = "argumentsForClearMethod")
    public void testForClearMethod(List<String> givenList) {
        //given
        Object[] expectedArray = new Object[0];

        //when
        givenList.clear();
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForClearMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2"))},
            new Object[]{createArrayListWithValues(Collections.singletonList("1"))},
            new Object[]{createArrayListWithValues(Collections.singletonList("0"))},
            new Object[]{createArrayListWithValues(Arrays.asList(null, null))},
            new Object[]{createArrayListWithValues(Collections.emptyList())}
        };
    }

    @Test
    @Parameters(method = "argumentsForContainsMethod")
    public void testForContainsMethod(List<String> givenList, Object element, boolean expected) {
        //when
        boolean result = givenList.contains(element);

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForContainsMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), "2", true},
                new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), "-2", true},
                new Object[]{createArrayListWithValues(Arrays.asList("-1","0","1")), "0", true},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), null, true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), "1", false},
                new Object[]{createArrayListWithValues(Collections.singletonList("1")), "0", false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), "4", false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 3, false}
        };
    }

    @Test
    @Parameters(method = "argumentsForContainsAllMethod")
    public void testForContainsAllMethod(List<String> givenList, Collection<Object> containsCollection, boolean expected) {
        //when
        boolean result = givenList.containsAll(containsCollection);

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForContainsAllMethod() {
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Arrays.asList("2","3"), true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), Arrays.asList("-2","-3"), true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Collections.singletonList("2"), true},
            new Object[]{createArrayListWithValues(Arrays.asList("1",null,"3")), Collections.singletonList(null), true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","0","1")), Arrays.asList("0", "-1"), true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","0","1")), Collections.singletonList("5"), false},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.singletonList("3"), false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Collections.emptyList(), true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Arrays.asList(1, 2, 3), false}
        };
    }

    @Test
    @Parameters(method = "argumentsForEqualsMethod")
    public void testForEqualsMethod(List<String> givenList, Object objectToCompare, boolean expected) {
        //when
        boolean result = givenList.equals(objectToCompare);

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForEqualsMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Arrays.asList("1","2","3"), true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), Arrays.asList("-1","-2","-3"), true},
            new Object[]{createArrayListWithValues(Collections.singletonList("0")), Collections.singletonList("0"), true},
            new Object[]{createArrayListWithValues(Arrays.asList(null, null)), Arrays.asList(null, null), true},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.emptyList(), true},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Arrays.asList("1", "2"), false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Collections.emptyList(), false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Arrays.asList(1, 2), false}
        };
    }

    @Test
    @Parameters(method = "argumentsForGetMethod")
    public void testForGetMethod(List<String> givenList, int index, String expected) {
        //when
        String result = givenList.get(index);

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForGetMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 1, "2"},
                new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), 0, "-1"},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 2, "3"},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), 1, null}
        };
    }

    @Test
    @Parameters(method = "argumentsForGetMethodWithInvalidIndex")
    public void testForGetMethodWithInvalidIndex(List<String> givenList, int index, String exceptionMessage) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(exceptionMessage);
        String result = givenList.get(index);
    }

    private Object[] argumentsForGetMethodWithInvalidIndex(){
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 5, "Index: 5, Size: 3"},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 3, "Index: 3, Size: 3"},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), -5, "-5"}
        };
    }

    @Test
    @Parameters(method = "argumentsForHashCodeMethod")
    public void testForHashCodeMethod(List<String> givenList, List<Object> listToCompare, boolean equal) {
        //when
        int resultGivenList = givenList.hashCode();
        int resultListToCompare = listToCompare.hashCode();
        boolean result = resultGivenList == resultListToCompare;

        //then
        assertEquals(equal, result);
    }

    private Object[] argumentsForHashCodeMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Arrays.asList("1","2","3"), true},
            new Object[]{createArrayListWithValues(Collections.singletonList("-1")), Collections.singletonList("-1"), true},
            new Object[]{createArrayListWithValues(Arrays.asList(null, null)), Arrays.asList(null, null), true},
            new Object[]{createArrayListWithValues(Arrays.asList("0","0")), Arrays.asList("0","0"), true},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.emptyList(), true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Arrays.asList("1","2"), false},
            new Object[]{createArrayListWithValues(Collections.singletonList("-1")), Arrays.asList("-1","-2"), false},
            new Object[]{createArrayListWithValues(Collections.singletonList(null)), Arrays.asList(null, null), false},
            new Object[]{createArrayListWithValues(Arrays.asList("0","0")), Arrays.asList("0","0","0"), false},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.singletonList("1"), false},
            new Object[]{createArrayListWithValues(Collections.singletonList("1")), Collections.emptyList(), false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Arrays.asList(1,2,3), false}
        };
    }

    @Test
    @Parameters(method = "argumentsForIndexOfMethod")
    public void testForIndexOfMethod(List<String> givenList, String elementToFind, int expectedIndex) {
        //when
        int result = givenList.indexOf(elementToFind);

        //then
        assertEquals(expectedIndex, result);
    }

    private Object[] argumentsForIndexOfMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), "2", 1},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), "-1", 0},
            new Object[]{Collections.singletonList("0"), "0", 0},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","2")), "2", 1},
            new Object[]{createArrayListWithValues(Arrays.asList("1", null,"2")), null, 1},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), "4", -1},
            new Object[]{createArrayListWithValues(Collections.emptyList()), "2", -1}
        };
    }

    @Test
    @Parameters(method = "argumentsForIsEmptyMethod")
    public void testForIsEmptyMethod(List<String> givenList, boolean expected) {
        //when
        boolean result = givenList.isEmpty();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForIsEmptyMethod() {
        return new Object[]{
            new Object[]{createArrayListWithValues(Collections.emptyList()), true},
            new Object[]{createArrayListWithValues(Collections.singletonList("1")), false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), false},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","2","-3")), false},
            new Object[]{createArrayListWithValues(Collections.singletonList(null)), false}
        };
    }

    @Test
    @Parameters(method = "argumentsForLastIndexOfMethod")
    public void testForLastIndexOfMethod(List<String> givenList, String elementToFind, int expectedIndex) {
        //when
        int result = givenList.lastIndexOf(elementToFind);

        //then
        assertEquals(expectedIndex, result);
    }

    private Object[] argumentsForLastIndexOfMethod(){
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","2")), "2", 2},
                new Object[]{createArrayListWithValues(Arrays.asList("-1","-1","-3")), "-1", 1},
                new Object[]{Collections.singletonList("0"), "0", 0},
                new Object[]{createArrayListWithValues(Arrays.asList("2","2","2")), "2", 2},
                new Object[]{createArrayListWithValues(Arrays.asList("1", null,null)), null, 2},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), "4", -1},
                new Object[]{createArrayListWithValues(Collections.emptyList()), "2", -1}
        };
    }

    @Test
    @Parameters(method = "argumentsForRemoveMethodWithIndex")
    public void testForRemoveMethodWithIndex(List<String> givenList, int index, Object[] expectedArray) {
        //when
        givenList.remove(index);
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForRemoveMethodWithIndex(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","3","2")), 1, new Object[]{"1","2"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-2")), 1, new Object[]{"-1","-2"}},
            new Object[]{createArrayListWithValues(Arrays.asList("0","1","2")), 0, new Object[]{"1","2"}},
            new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), 2, new Object[]{null, null}},
            new Object[]{createArrayListWithValues(Collections.singletonList("1")), 0, new Object[0]}
        };
    }

    @Test
    @Parameters(method = "argumentsForRemoveMethodWithInvalidIndex")
    public void testForRemoveMethodWithInvalidIndex(List<String> givenList, int index, Class expectedException, String exceptionMessage) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(exceptionMessage);
        givenList.remove(index);
    }

    private Object[] argumentsForRemoveMethodWithInvalidIndex() {
        return new Object[] {
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 4, IndexOutOfBoundsException.class, "Index: 4, Size: 3"},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), -4, IndexOutOfBoundsException.class, "-4"},
                new Object[]{createArrayListWithValues(Collections.emptyList()), 0, IndexOutOfBoundsException.class, ""}
        };
    }

    @Test
    @Parameters(method = "argumetnsForRemoveMethodWithObject")
    public void testForRemoveMethodWithObject(List<String> givenList, String elementToRemove, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.remove(elementToRemove);
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
        assertEquals(expectedResult, resultBoolean);
    }

    private Object[] argumetnsForRemoveMethodWithObject() {
        return new Object[] {
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), "2", new Object[]{"1","3"}, true},
                new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), "-3", new Object[]{"-1","-2"}, true},
                new Object[]{createArrayListWithValues(Arrays.asList("0","1","2")), "0", new Object[]{"1","2"}, true},
                new Object[]{createArrayListWithValues(Collections.singletonList(null)), null, new Object[0], true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), "1", new Object[0], false},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), "4", new Object[]{"1","2","3"}, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3","1","5","1")), "1", new Object[]{"2","3","1","5","1"}, true}
        };
    }

    @Test
    @Parameters(method = "argumentsForRemoveAllMethod")
    public void testForRemoveAllMethod(List<String> givenList, Collection<String> collectionToRemove, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.removeAll(collectionToRemove);
        Object[] resultArray = givenList.toArray();

        //then
        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForRemoveAllMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","3","4","2")), Arrays.asList("3","4"), new Object[]{"1","2"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-3","-4","-2")), Arrays.asList("-3","-4"), new Object[]{"-1","-2"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","0","2")), Collections.singletonList("0"), new Object[]{"1","2"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2", null)), Collections.singletonList(null), new Object[]{"1","2"}, true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Arrays.asList("1","2"), new Object[0], true},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Collections.emptyList(), new Object[]{"1","2"}, false},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.emptyList(), new Object[0], false},
            new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.singletonList("1"), new Object[0], false},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2")), Arrays.asList(1,2), new Object[]{"1","2"}, false}
        };
    }

    @Test
    @Parameters(method = "argumentsForRetainAllMethod")
    public void testForRetainAllMethod(List<String> givenList, Collection<String> collectionToRetain, Object[] expectedArray, boolean expectedResult) {
        //when
        boolean resultBoolean = givenList.retainAll(collectionToRetain);
        Object[] resultArray = givenList.toArray();

        //then
        assertEquals(expectedResult, resultBoolean);
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForRetainAllMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1","3","4","2")), Arrays.asList("1","2"), new Object[]{"1","2"}, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1","-3","2")), Arrays.asList("1","2"), new Object[]{"1","2"}, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1",null,"2")), Collections.singletonList(null), new Object[]{null}, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1","0","2")), Collections.singletonList("0"), new Object[]{"0"}, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), Collections.emptyList(), new Object[0], true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), Collections.singletonList("1"), new Object[0], false},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2")),Arrays.asList("3","4"), new Object[0], true},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2")),Arrays.asList("1","2"), new Object[]{"1","2"}, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2")),Arrays.asList(1, 2), new Object[0], true}
        };
    }

    @Test
    @Parameters(method = "argumentsForSetMethod")
    public void testForSetMethod(List<String> givenList, int index, String element, Object[] expectedArray) {
        //when
        givenList.set(index, element);
        Object[] resultArray = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForSetMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","3")), 1, "2", new Object[]{"1","2"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-5","-2")), 0, "-1", new Object[]{"-1","-2"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","3","1")), 1, "0", new Object[]{"-1","0","1"}},
            new Object[]{createArrayListWithValues(Arrays.asList("1","-3")), 1, null, new Object[]{"1", null}},
            new Object[]{createArrayListWithValues(Arrays.asList("1","-3")), 1, 1, new Object[]{"1", "1"}}
        };
    }

    @Test
    @Parameters(method = "argumentsForSetMethodWithInvalidArguments")
    public void testForSetMethodWithInvalidArguments(List<String> givenList, int index, String element, Class expectedExceptionType, String exceptionMessage) {
        //when
        thrown.expect(expectedExceptionType);
        thrown.expectMessage(exceptionMessage);
        givenList.set(index, element);
    }

    private Object[] argumentsForSetMethodWithInvalidArguments(){
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1","2")), -7, "3", ArrayIndexOutOfBoundsException.class, "-7"},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2")), 7, "3", IndexOutOfBoundsException.class, "Index: 7, Size: 2"},
                new Object[]{createArrayListWithValues(Arrays.asList("1","2")), 2, "3", IndexOutOfBoundsException.class, "Index: 2, Size: 2"}
        };
    }

    @Test
    @Parameters(method = "argumentsForSizeMethod")
    public void testForSizeMethod(List<String> givenList, int expectedSize) {
        //when
        int result = givenList.size();

        //then
        assertEquals(expectedSize, result);
    }

    private Object[] argumentsForSizeMethod() {
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 3},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","0","1")), 3},
            new Object[]{createArrayListWithValues(Collections.singletonList("0")), 1},
            new Object[]{createArrayListWithValues(Arrays.asList(null, null)), 2},
            new Object[]{createArrayListWithValues(Collections.emptyList()), 0}
        };
    }

    @Test
    @Parameters(method = "argumentsForSubListMethod")
    public void testForSubListMethod(List<String> givenList, int fromIndex, int toIndex, Object[] expectedArray) {
        //when
        List<String> subList = givenList.subList(fromIndex, toIndex);
        Object[] resultArray = subList.toArray();

        //then
        assertArrayEquals(expectedArray, resultArray);
    }

    private Object[] argumentsForSubListMethod(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 1, 3, new Object[]{"2","3"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), 1, 2, new Object[]{"-2"}},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 0, 3, new Object[]{"1","2","3"}},
            new Object[]{createArrayListWithValues(Arrays.asList("0","0","0")), 0, 3, new Object[]{"0","0","0"}},
            new Object[]{createArrayListWithValues(Collections.singletonList(null)), 0, 1, new Object[]{null}},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 1, 1, new Object[0]},
            new Object[]{createArrayListWithValues(Collections.emptyList()), 0, 0, new Object[0]}
        };
    }

    @Test
    @Parameters(method = "argumentsForSubListMethodWithInvalidIndexes")
    public void testForSubListMethodWithInvalidIndexes(List<String> givenList, int fromIndex, int toIndex, Class expectedExceptionType, String exceptionMessage) {
        //when
        thrown.expect(expectedExceptionType);
        thrown.expectMessage(exceptionMessage);
        List<String> resultString = givenList.subList(fromIndex, toIndex);
    }

    private Object[] argumentsForSubListMethodWithInvalidIndexes(){
        return new Object[]{
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), -1, 2, IndexOutOfBoundsException.class, "fromIndex = -1"},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 0, 5, IndexOutOfBoundsException.class, "toIndex = 5"},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), 5, 2, IllegalArgumentException.class, "fromIndex(5) > toIndex(2)"},
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), -7, 5, IndexOutOfBoundsException.class, "fromIndex = -7"}
        };
    }

    @Test
    @Parameters(method = "argumentsForToArrayMethod")
    public void testForToArrayMethod(List<String> givenList, Object[] expectedArray) {
        //when
        Object[] result = givenList.toArray();

        //then
        assertArrayEquals(expectedArray, result);
    }

    private Object[] argumentsForToArrayMethod() {
        return new Object[] {
            new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), new Object[]{"1","2","3"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), new Object[]{"-1","-2","-3"}},
            new Object[]{createArrayListWithValues(Arrays.asList("-1","0","1")), new Object[]{"-1","0","1"}},
            new Object[]{createArrayListWithValues(Collections.singletonList(null)), new Object[]{null}},
            new Object[]{createArrayListWithValues(Collections.emptyList()), new Object[0]}
        };
    }

    @Test
    @Parameters(method = "argumentsForToArrayMethodWithGenerics")
    public void testForToArrayMethodWithGenerics(List<String> givenList, String[] expected) {
        //when
        String[] result = givenList.toArray(expected);

        //then
        assertArrayEquals(expected, result);
    }

    private Object[] argumentsForToArrayMethodWithGenerics() {
        return new Object[] {
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), new String[]{"1","2","3"}},
                new Object[]{createArrayListWithValues(Arrays.asList("-1","-2","-3")), new String[]{"-1","-2","-3"}},
                new Object[]{createArrayListWithValues(Arrays.asList("0","0","0")), new String[]{"0","0","0"}},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null)), new String[]{null, null}},
                new Object[]{createArrayListWithValues(Collections.emptyList()), new String[0]}
        };
    }

    @Test
    @Parameters(method = "argumentsForToArrayMethodWithInvalidGenerics")
    public void testForToArrayMethodWithInvalidGenerics(List<String> givenList, Object[] destinationArray ,Class expectedExceptionType) {
        //when
        thrown.expect(expectedExceptionType);
        destinationArray = givenList.toArray(destinationArray);
    }

    private Object[] argumentsForToArrayMethodWithInvalidGenerics() {
        return new Object[] {
                new Object[]{createArrayListWithValues(Arrays.asList("1","2","3")), null, NullPointerException.class}
        };
    }
}
