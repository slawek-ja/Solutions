package pl.coderstrust.MyArrayList;

import java.util.*;

@SuppressWarnings("unchecked")
public class MyArrayList<T> implements List<T> {

    private int realArraySize = 10;
    private int usedSize = 0;
    private Object[] array = new Object[]{};

    @Override
    public int size() {
        return this.usedSize;
    }

    @Override
    public boolean isEmpty() {
        return this.usedSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }
        if (o != null && array[0].getClass() != o.getClass()) {
            throw new ClassCastException();
        }
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
                return cursor + 2 <= usedSize;
            }

            @Override
            public T next() {
                if (cursor + 1 >= usedSize) {
                    throw new NoSuchElementException();
                }
                return (T) array[++cursor];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, usedSize);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] result = Arrays.copyOf(a, usedSize);
        for (int i = 0; i < usedSize; i++) {
            result[i] = (T) array[i];
        }
        return result;
    }

    @Override
    public boolean add(T e) {
        if (isEmpty()) {
            extendArray();
        }
        usedSize++;
        if (isArrayFull()) {
            reSizeArray();
        }
        array[usedSize - 1] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        int indexFound;
        boolean isFound = false;
        for (indexFound = 0; indexFound < usedSize; indexFound++) {
            if (areEqual(array[indexFound], o)) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return isFound;
        }
        Object[] storage = Arrays.copyOf(array, usedSize);
        usedSize -= 1;
        realArraySize = usedSize;
        extendArray();
        int skipIndex = 0;
        for (int i = 0; i < usedSize; i++) {
            if (i == indexFound) {
                skipIndex = 1;
            }
            array[i] = storage[i + skipIndex];
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean numbersMatched;
        Iterator<?> iterator = c.iterator();
        T searchedNumber;
        while (iterator.hasNext()) {
            numbersMatched = false;
            searchedNumber = (T) iterator.next();
            for (int i = 0; i < usedSize; i++) {
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
        if (isEmpty()) {
            extendArray();
        }
        if (c.size() + usedSize < realArraySize) {
            Iterator<?> iterator = c.iterator();
            int index = usedSize;
            usedSize += c.size();
            while (iterator.hasNext()) {
                array[index] = iterator.next();
                index++;
            }
            return true;
        }
        Object[] storage = Arrays.copyOf(array, usedSize);
        Object[] givenContainer = c.toArray();
        usedSize += c.size();
        realArraySize = usedSize;
        extendArray();
        System.arraycopy(storage, 0, array, 0, storage.length);
        System.arraycopy(givenContainer, 0, array, storage.length, usedSize);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > usedSize || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.usedSize);
        }
        if (c.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            extendArray();
        }
        if (c.size() + usedSize < realArraySize) {
            Object[] storage = Arrays.copyOfRange(array, index, usedSize);
            Iterator<?> iterator = c.iterator();
            usedSize += c.size();
            while (iterator.hasNext()) {
                array[index++] = iterator.next();
            }
            System.arraycopy(storage, 0, array, index, storage.length);
            return true;
        }
        Object[] givenContainer = c.toArray();
        Object[] storage = Arrays.copyOf(array, usedSize);
        int arrayIndex = 0;
        usedSize += c.size();
        realArraySize = usedSize;
        extendArray();
        boolean added = false;
        for (int i = 0; i < usedSize; i++, arrayIndex++) {
            if (!added && index == i) {
                for (int j = 0; j < givenContainer.length; j++) {
                    array[i++] = givenContainer[j];
                }
                added = true;
            }
            if (i >= usedSize) {
                return added;
            }
            array[i] = storage[arrayIndex];
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (c.size() == 0) {
            return false;
        }
        Iterator<?> iterator = c.iterator();
        int sizeToCheck = size();
        int howManyToRemove = 0;
        T searchingValue;
        while (iterator.hasNext()) {
            searchingValue = (T) iterator.next();
            for (int i = 0; i < sizeToCheck; i++) {
                if (areEqual(searchingValue, array[i])) {
                    for (int j = i; j < usedSize - 1; j++) {
                        swap(array, j, j + 1);
                    }
                    sizeToCheck--;
                    howManyToRemove++;
                }
            }
        }
        if (howManyToRemove == 0) {
            return false;
        }
        usedSize -= howManyToRemove;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        if (c.isEmpty()) {
            usedSize = 0;
            realArraySize = usedSize;
            extendArray();
            return true;
        }
        Object[] arrayMirror = Arrays.copyOf(array, this.usedSize);
        if (Arrays.equals(c.toArray(), arrayMirror)) {
            return false;
        }
        Iterator<?> iterator = c.iterator();
        T searchedValue;
        int anchor = 0;
        T buffer;
        while (iterator.hasNext()) {
            searchedValue = (T) iterator.next();
            for (int i = 0; i < usedSize; i++) {
                if (areEqual(array[i], searchedValue)) {
                    buffer = (T) array[anchor];
                    array[anchor] = searchedValue;
                    array[i] = buffer;
                    anchor++;
                }
            }
        }
        if (anchor == 0) {
            usedSize = 0;
            realArraySize = usedSize;
            extendArray();
            return true;
        }
        usedSize = anchor;
        return true;
    }

    @Override
    public void clear() {
        usedSize = 0;
        realArraySize = usedSize;
        extendArray();
    }

    @Override
    public T get(int index) {
        if (index >= usedSize || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.usedSize);
        }
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= usedSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.usedSize);
        }
        array[index] = element;
        return (T) array;
    }

    @Override
    public void add(int index, T element) {
        if (index > usedSize || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, this.usedSize));
        }
        Object[] storage = Arrays.copyOf(array, usedSize);
        usedSize += 1;
        realArraySize = usedSize;
        extendArray();
        int arrayIndex = 0;
        for (int i = 0; i < usedSize; i++) {
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
        if (index > usedSize || index < 0 || isEmpty()) {
            if (index < 0) {
                throw new IndexOutOfBoundsException(Integer.toString(index));
            }
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.usedSize);
        }
        Object[] storage = Arrays.copyOf(array, usedSize);
        usedSize -= 1;
        realArraySize = usedSize;
        extendArray();
        int skipIndex = 0;
        for (int i = 0; i < usedSize; i++) {
            if (i == index) {
                skipIndex = 1;
            }
            array[i] = storage[i + skipIndex];
        }
        return (T) array;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < usedSize; i++) {
            if (areEqual(array[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = usedSize - 1; i >= 0; i--) {
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
                return cursor + 2 <= usedSize;
            }

            @Override
            public T next() {
                if (cursor + 1 >= usedSize) {
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
        if (index > usedSize || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListIterator<T>() {
            int cursor = index - 1;

            @Override
            public boolean hasNext() {
                return cursor + 2 <= usedSize;
            }

            @Override
            public T next() {
                if (cursor + 1 > usedSize || isEmpty()) {
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
        if (toIndex > usedSize || fromIndex < 0 || fromIndex > toIndex) {
            if (fromIndex < 0 || toIndex > this.usedSize) {
                throw new IndexOutOfBoundsException((fromIndex < 0) ? String.format("fromIndex = %d", fromIndex) : String.format("toIndex = %d", toIndex));
            }
            throw new IllegalArgumentException(String.format("fromIndex(%d) > toIndex(%d)", fromIndex, toIndex));
        }
        Object[] storage = Arrays.copyOfRange(array, fromIndex, toIndex);
        List<T> result = new MyArrayList<>();
        for (Object element : storage) {
            result.add((T) element);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        List<T> storage = new MyArrayList<>();
        copyObjectsToCollections(array, this.usedSize, storage);
        Iterator i = storage.iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Object[] mirror = Arrays.copyOf(array, this.usedSize);
        return Arrays.hashCode(mirror) == o.hashCode();
    }

    private void extendArray() {
        if (realArraySize == 0) {
            realArraySize = 10;
        }
        realArraySize += realArraySize / 2;
        array = new Object[realArraySize];
    }

    private void reSizeArray() {
        Object[] storage = Arrays.copyOf(array, usedSize - 1);
        extendArray();
        System.arraycopy(storage, 0, array, 0, usedSize);
    }

    private void swap(Object[] src, int idxA, int idxB) {
        Object buffer;
        buffer = src[idxA];
        src[idxA] = src[idxB];
        src[idxB] = buffer;
    }

    private boolean areEqual(Object argA, Object argB) {
        if (argA == null || argB == null) {
            return argA == argB;
        }
        return argA.equals(argB);
    }

    private boolean isArrayFull() {
        return usedSize >= realArraySize;
    }

    private void copyObjectsToCollections(Object[] source, int sourceSize, Collection<T> dest) {
        dest.clear();
        for (int i = 0; i < sourceSize; i++) {
            dest.add((T) source[i]);
        }
    }
}
