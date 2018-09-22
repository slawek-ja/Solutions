package pl.coderstrust.MyArrayList;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import java.util.*;

@RunWith(JUnitParamsRunner.class)
public abstract class ArrayIteratorTestBase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public abstract List<String> getArrayList();

    private List<String> createArrayListWithValues(List<String> values) {
        List<String> result = getArrayList();
        result.addAll(values);
        return result;
    }

    private ListIterator<String> moveListIteratorForward(List<String> givenList, int howManyTimes) {
        ListIterator<String> listIterator = givenList.listIterator();
        for (int i = 0; i < howManyTimes; i++) {
            listIterator.next();
        }
        return listIterator;
    }

    private Iterator<String> moveIteratorForward(List<String> givenList, int howManyTimes) {
        Iterator<String> iterator = givenList.iterator();
        for (int i = 0; i < howManyTimes; i++) {
            iterator.next();
        }
        return iterator;
    }

    @Test
    @Parameters(method = "argumentsForListIteratorHasNextMethod")
    public void testForListIteratorHasNextMethod(List<String> givenList, int forwardTimes, boolean expected) {
        //given
        ListIterator<String> listIterator;

        //when
        listIterator = moveListIteratorForward(givenList, forwardTimes);
        boolean result = listIterator.hasNext();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorHasNextMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3", "4")), 2, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3", "4")), 0, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3", "4")), 3, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3", "4")), 4, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", null)), 1, true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), 0, false},
                new Object[]{createArrayListWithValues(Collections.singletonList("1")), 0, true},
                new Object[]{createArrayListWithValues(Collections.singletonList("1")), 1, false}
        };
    }

    @Test
    @Parameters(method = "argumentsForListIteratorNextMethod")
    public void testForListIteratorNextMethod(List<String> givenList, int forwardTimes, Object expected) {
        //given
        ListIterator<String> listIterator;

        //when
        listIterator = moveListIteratorForward(givenList, forwardTimes);
        Object result = listIterator.next();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorNextMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, "1"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 1, "2"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 2, "3"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", null)), 1, null},
                new Object[]{createArrayListWithValues(Collections.singletonList("1")), 0, "1"}
        };
    }

    @Test
    @Parameters(method = "argumentsForListIteratorNextMethodWhenIteratorIsMovedTooManyTimes")
    public void testForListIteratorNextMethodWhenIteratorOutOfRange(List<String> givenList, int forwardTimes) {
        //given
        ListIterator<String> listIterator = givenList.listIterator();

        //when
        thrown.expect(NoSuchElementException.class);
        listIterator = moveListIteratorForward(givenList, forwardTimes);
    }

    private Object[] argumentsForListIteratorNextMethodWhenIteratorIsMovedTooManyTimes() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 4},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null)), 3},
                new Object[]{createArrayListWithValues(Collections.emptyList()), 1}
        };
    }

    @Test
    @Parameters(method = "argumentsForListIteratorHasPreviousMethod")
    public void testForListIteratorHasPreviousMethod(List<String> givenList, int forwardTimes, boolean expected) {
        //given
        ListIterator<String> listIterator;

        //when
        listIterator = moveListIteratorForward(givenList, forwardTimes);
        boolean result = listIterator.hasPrevious();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorHasPreviousMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 2, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2")), 1, true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), 0, false}
        };
    }

    @Test
    @Parameters(method = "argumentsForListIteratorPreviousMethod")
    public void testForListIteratorPreviousMethod(List<String> givenList, int forwardTimes, Object expected) {
        //given
        ListIterator<String> listIterator;

        //when
        listIterator = moveListIteratorForward(givenList, forwardTimes);
        Object result = listIterator.previous();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorPreviousMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 2, "2"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 3, "3"},
                new Object[]{createArrayListWithValues(Collections.singletonList("1")), 1, "1"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", null, "2")), 2, null}
        };
    }

    @Test
    public void testForListIteratorPreviousMethodWhenArrayIsEmpty() {
        //given
        List<String> givenList = getArrayList();
        ListIterator<String> listIterator = givenList.listIterator();

        //when
        thrown.expect(NoSuchElementException.class);
        listIterator.previous();
    }

    @Test
    @Parameters(method = "argumentsForListIteratorHasNextMethodWithIndex")
    public void testForListIteratorHasNextMethodWithIndex(List<String> givenList, int index, boolean expected) {
        //given
        ListIterator<String> listIterator = givenList.listIterator(index);

        //when
        boolean result = listIterator.hasNext();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorHasNextMethodWithIndex() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, true},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 3, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 1, true},
                new Object[]{createArrayListWithValues(Collections.singletonList("1")), 0, true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), 0, false},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), 1, true}
        };
    }

    @Test
    @Parameters(method = "argumentsForListIteratorNextMethodWithIndex")
    public void testForListIteratorNextMethodWithIndex(List<String> givenList, int index, Object expected) {
        //given
        ListIterator<String> listIterator = givenList.listIterator(index);

        //when
        Object result = listIterator.next();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorNextMethodWithIndex() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, "1"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 2, "3"},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, "1")), 1, null}
        };
    }

    @Test
    public void testForListIteratorNextMethodWithIndexWhenIteratorIsOutOfArray() {
        //given
        List<String> givenList = getArrayList();
        ListIterator<String> listIterator = givenList.listIterator(0);

        //when
        thrown.expect(NoSuchElementException.class);
        listIterator.next();
    }

    @Test
    @Parameters(method = "argumentsForListIteratorHasPreviousMethodWithIndex")
    public void testForListIteratorHasPreviousMethodWithIndex(List<String> givenList, int index, boolean expected) {
        //given
        ListIterator<String> listIterator = givenList.listIterator(index);

        //when
        boolean result = listIterator.hasPrevious();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorHasPreviousMethodWithIndex() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 2, true},
                new Object[]{createArrayListWithValues(Collections.emptyList()), 0, false},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), 1, true}
        };
    }

    @Test
    @Parameters(method = "argumentsForListIteratorPreviousMethodWithIndex")
    public void testForListIteratorPreviousMethodWithIndex(List<String> givenList, int index, Object expected) {
        //given
        ListIterator<String> listIterator = givenList.listIterator(index);

        //when
        Object result = listIterator.previous();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForListIteratorPreviousMethodWithIndex() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 1, "1"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 3, "3"},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null)), 1, null}
        };
    }

    @Test
    public void testForListIteratorPreviousMethodWithIndexWithoutMovingIterator() {
        //given
        List<String> givenList = getArrayList();
        ListIterator<String> listIterator = givenList.listIterator(0);

        //when
        thrown.expect(NoSuchElementException.class);
        listIterator.previous();
    }

    @Test
    @Parameters(method = "argumentsForListIteratorWithIndexWhenGivenIndexIsInvalid")
    public void testForListIteratorWhenGivenIndexIsInvalid(List<String> givenList, int index) {
        //when
        thrown.expect(IndexOutOfBoundsException.class);
        ListIterator<String> listIterator = givenList.listIterator(index);
    }

    private Object[] argumentsForListIteratorWithIndexWhenGivenIndexIsInvalid() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), -5},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 7}
        };
    }

    @Test
    @Parameters(method = "argumentsForIteratorHasNextMethod")
    public void testForIteratorHasNextMethod(List<String> givenList, int forwardTimes, boolean expected) {
        //given
        Iterator<String> iterator;

        //when
        iterator = moveIteratorForward(givenList, forwardTimes);
        boolean result = iterator.hasNext();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForIteratorHasNextMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 3, false},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, true},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), 1, true}
        };
    }

    @Test
    @Parameters(method = "argumentsForIteratorNextMethod")
    public void testForIteratorNextMethod(List<String> givenList, int forwardTimes, Object expected) {
        //given
        Iterator<String> iterator;

        //when
        iterator = moveIteratorForward(givenList, forwardTimes);
        Object result = iterator.next();

        //then
        assertEquals(expected, result);
    }

    private Object[] argumentsForIteratorNextMethod() {
        return new Object[]{
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 0, "1"},
                new Object[]{createArrayListWithValues(Arrays.asList("1", "2", "3")), 2, "3"},
                new Object[]{createArrayListWithValues(Arrays.asList(null, null, null)), 1, null}
        };
    }

    @Test
    public void testForIteratorNextMethodWithoutMovingIterator() {
        //given
        List<String> givenList = getArrayList();
        Iterator<String> iterator = givenList.iterator();

        //when
        thrown.expect(NoSuchElementException.class);
        iterator.next();
    }
}
