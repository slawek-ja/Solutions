package pl.coderstrust.MyArrayList;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private int realSize = 10;
    private int size = 0;
    private Object[] array = new Object[]{};

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : array) {
            if (areEqual(element, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = -1;

            @Override
            public boolean hasNext() {
                return cursor + 2 <= size;
            }

            @Override
            public T next() {
                if (cursor + 1 >= size) {
                    throw new NoSuchElementException();
                }
                return (T) array[++cursor];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] result = Arrays.copyOf(a, size);
        for (int i = 0; i < size; i++) {
            result[i] = (T) array[i];
        }
        return result;
    }

    @Override
    public boolean add(T e) {
        if (isEmpty()) {
            array = new Object[realSize];
        }
        size++;
        if (isArrayFull()) {
            Object[] storage = Arrays.copyOf(array, size - 1);
            array = new Object[increaseArrayRealSize()];
            for (int i = 0; i < size - 1; i++) {
                array[i] = storage[i];
            }
        }
        array[size - 1] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        int indexFound;
        boolean isFound = false;
        for (indexFound = 0; indexFound < size; indexFound++) {
            if (areEqual(array[indexFound], o)) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return isFound;
        }
        Object[] storage = Arrays.copyOf(array, size);
        size -= 1;
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        int skipIndex = 0;
        for (int i = 0; i < size; i++) {
            if (i == indexFound) {
                skipIndex = 1;
            }
            array[i] = storage[i + skipIndex];
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        LinkedHashSet<T> storage = returnSet(c);
        boolean numbersMatched;
        Iterator<T> iterator = storage.iterator();
        T searchedNumber = null;
        while (iterator.hasNext()) {
            numbersMatched = false;
            searchedNumber = iterator.next();
            for (int i = 0; i < size; i++) {
                if (areEqual(searchedNumber, array[i])) {
                    numbersMatched = true;
                    break;
                }
            }
            if (!numbersMatched) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        Object[] storage = Arrays.copyOf(array, size);
        Object[] givenContainer = c.toArray();
        size += c.size();
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        for (int i = 0; i < storage.length; i++) {
            array[i] = storage[i];
        }
        for (int i = storage.length, j = 0; i < size; i++, j++) {
            array[i] = givenContainer[j];
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        Object[] givenContainer = c.toArray();
        Object[] storage = Arrays.copyOf(array, size);
        int arrayIndex = 0;
        size += c.size();
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        boolean added = false;
        for (int i = 0; i < size; i++, arrayIndex++) {
            if (!added && index == i) {
                for (int j = 0; j < givenContainer.length; j++) {
                    array[i++] = givenContainer[j];
                }
                added = true;
            }
            if (i >= size) {
                return added;
            }
            array[i] = storage[arrayIndex];
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        LinkedHashSet<T> copyC = returnSet(c);
        Iterator<T> iterator = copyC.iterator();
        List<T> storage = new ArrayList<>();
        copyObjectsToCollections(array, this.size, storage);
        T actualNumber = null;
        int howManyToRemove = 0;
        while (iterator.hasNext()) {
            actualNumber = iterator.next();
            for (int i = 0; i < storage.size() - howManyToRemove; i++) {
                if (areEqual(actualNumber, storage.get(i))) {
                    for (int j = i; j < storage.size() - 1; j++) {
                        Collections.swap(storage, j, j + 1);
                    }
                    howManyToRemove++;
                }
            }
        }
        size = storage.size() - howManyToRemove;
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        for (int i = 0; i < size; i++) {
            array[i] = storage.get(i);
        }
        if (howManyToRemove > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] compare = c.toArray();
        Object[] arrayMirror = Arrays.copyOfRange(array, 0, this.size);
        if (Arrays.equals(arrayMirror, compare)) {
            return false;
        }
        LinkedHashSet<T> copyC = returnSet(c);
        List<T> storage = new ArrayList<>();
        copyObjectsToCollections(array, this.size, storage);
        Iterator<T> iterator = copyC.iterator();
        T actualArgument;
        boolean equal;
        int howManyToRemove = 0;
        while (iterator.hasNext()) {
            actualArgument = iterator.next();
            for (int i = 0; i < storage.size() - howManyToRemove; i++) {
                equal = areEqual(actualArgument, storage.get(i));
                if (!equal) {
                    for (int j = i; j < storage.size() - 1; j++) {
                        Collections.swap(storage, j, j + 1);
                    }
                    howManyToRemove++;
                }
            }
        }
        if (howManyToRemove == 0) {
            return false;
        }
        size -= howManyToRemove;
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        for (int i = 0; i < size; i++) {
            array[i] = storage.get(i);
        }
        return true;
    }

    @Override
    public void clear() {
        array = new Object[0];
    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        array[index] = element;
        return (T) array;
    }

    @Override
    public void add(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        Object[] storage = Arrays.copyOf(array, size);
        size += 1;
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        int arrayIndex = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                array[i] = element;
                continue;
            }
            array[i] = storage[arrayIndex];
            arrayIndex++;
        }
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        Object[] storage = Arrays.copyOf(array, size);
        size -= 1;
        realSize = size;
        array = new Object[increaseArrayRealSize()];
        int skipIndex = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                skipIndex = 1;
            }
            array[i] = storage[i + skipIndex];
        }
        return (T) array;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (areEqual(array[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (areEqual(array[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int cursor = -1;

            @Override
            public boolean hasNext() {
                return cursor + 2 <= size;
            }

            @Override
            public T next() {
                if (cursor + 1 >= size) {
                    throw new NoSuchElementException();
                }
                return (T) array[++cursor];
            }

            @Override
            public boolean hasPrevious() {
                return cursor + 1 > 0;
            }

            @Override
            public T previous() {
                if (isEmpty() || cursor - 1 < -1) {
                    throw new NoSuchElementException();
                }
                return (T) array[cursor--];
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListIterator<T>() {
            int cursor = index - 1;

            @Override
            public boolean hasNext() {
                return cursor + 2 <= size;
            }

            @Override
            public T next() {
                if (cursor + 1 > size || isEmpty()) {
                    throw new NoSuchElementException();
                }
                return (T) array[++cursor];
            }

            @Override
            public boolean hasPrevious() {
                return cursor + 1 > 0;
            }

            @Override
            public T previous() {
                if (isEmpty() || cursor - 1 < -1) {
                    throw new NoSuchElementException();
                }
                return (T) array[cursor--];
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        Object[] storage = Arrays.copyOfRange(array, fromIndex, toIndex);
        List<T> result = new ArrayList<>();
        for (Object element : storage) {
            result.add((T) element);
        }
        return result;
    }

    //support Class Methods

    private boolean areEqual(Object argA, Object argB) {
        if (argA == null || argB == null) {
            return argA == argB;
        }
        return argA.equals(argB);
    }

    private boolean isArrayFull() {
        return size >= realSize;
    }

    private int increaseArrayRealSize() {
        return realSize += realSize / 2;
    }

    private LinkedHashSet<T> returnSet(Collection<?> c) {
        Object[] copyC = c.toArray();
        LinkedHashSet<T> result = new LinkedHashSet<>();
        for (int i = 0; i < copyC.length; i++) {
            result.add((T) copyC[i]);
        }
        return result;
    }

    private void copyObjectsToCollections(Object[] source, int sourceSize, Collection<T> dest) {
        dest.clear();
        for (int i = 0; i < sourceSize; i++) {
            dest.add((T) source[i]);
        }
    }
}
