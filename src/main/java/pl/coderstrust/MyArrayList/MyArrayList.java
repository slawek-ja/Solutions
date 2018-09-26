package pl.coderstrust.MyArrayList;

import java.util.*;

@SuppressWarnings("unchecked")
public class MyArrayList<T> implements List<T> {

    private int allocatedSize = 10;
    private int numberOfElements = 0;
    private Object[] array = new Object[]{};

    @Override
    public int size() {
        return this.numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return this.numberOfElements == 0;
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
                return cursor + 2 <= numberOfElements;
            }

            @Override
            public T next() {
                if (cursor + 1 >= numberOfElements) {
                    throw new NoSuchElementException();
                }
                return (T) array[++cursor];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, numberOfElements);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] result = Arrays.copyOf(a, numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            result[i] = (T) array[i];
        }
        return result;
    }

    @Override
    public boolean add(T e) {
        if (isEmpty()) {
            extendArray();
        }
        numberOfElements++;
        if (isArrayFull()) {
            reSizeArray();
        }
        array[numberOfElements - 1] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        int indexFound;
        boolean isFound = false;
        for (indexFound = 0; indexFound < numberOfElements; indexFound++) {
            if (areEqual(array[indexFound], o)) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return isFound;
        }
        Object[] storage = Arrays.copyOf(array, numberOfElements);
        numberOfElements -= 1;
        allocatedSize = numberOfElements;
        extendArray();
        int skipIndex = 0;
        for (int i = 0; i < numberOfElements; i++) {
            if (i == indexFound) {
                skipIndex = 1;
            }
            array[i] = storage[i + skipIndex];
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        T searchedNumber;
        while (iterator.hasNext()) {
            searchedNumber = (T) iterator.next();
            if (!contains(searchedNumber)) {
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
        if (c.size() + numberOfElements < allocatedSize) {
            Iterator<?> iterator = c.iterator();
            int index = numberOfElements;
            numberOfElements += c.size();
            while (iterator.hasNext()) {
                array[index] = iterator.next();
                index++;
            }
            return true;
        }
        Object[] storage = Arrays.copyOf(array, numberOfElements);
        Object[] givenContainer = c.toArray();
        numberOfElements += c.size();
        allocatedSize = numberOfElements;
        extendArray();
        System.arraycopy(storage, 0, array, 0, storage.length);
        System.arraycopy(givenContainer, 0, array, storage.length, numberOfElements);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > numberOfElements || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.numberOfElements);
        }
        if (c.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            extendArray();
        }
        if (c.size() + numberOfElements < allocatedSize) {
            Object[] storage = Arrays.copyOfRange(array, index, numberOfElements);
            Iterator<?> iterator = c.iterator();
            numberOfElements += c.size();
            while (iterator.hasNext()) {
                array[index++] = iterator.next();
            }
            System.arraycopy(storage, 0, array, index, storage.length);
            return true;
        }
        Object[] givenContainer = c.toArray();
        Object[] storage = Arrays.copyOf(array, numberOfElements);
        int arrayIndex = 0;
        numberOfElements += c.size();
        allocatedSize = numberOfElements;
        extendArray();
        boolean added = false;
        for (int i = 0; i < numberOfElements; i++, arrayIndex++) {
            if (!added && index == i) {
                for (int j = 0; j < givenContainer.length; j++) {
                    array[i++] = givenContainer[j];
                }
                added = true;
            }
            if (i >= numberOfElements) {
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
                    for (int j = i; j < numberOfElements - 1; j++) {
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
        numberOfElements -= howManyToRemove;
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
            numberOfElements = 0;
            allocatedSize = numberOfElements;
            extendArray();
            return true;
        }
        Object[] arrayMirror = Arrays.copyOf(array, this.numberOfElements);
        if (Arrays.equals(c.toArray(), arrayMirror)) {
            return false;
        }
        Iterator<?> iterator = c.iterator();
        T searchedValue;
        int anchor = 0;
        T buffer;
        while (iterator.hasNext()) {
            searchedValue = (T) iterator.next();
            for (int i = 0; i < numberOfElements; i++) {
                if (areEqual(array[i], searchedValue)) {
                    buffer = (T) array[anchor];
                    array[anchor] = searchedValue;
                    array[i] = buffer;
                    anchor++;
                }
            }
        }
        if (anchor == 0) {
            numberOfElements = 0;
            allocatedSize = numberOfElements;
            extendArray();
            return true;
        }
        numberOfElements = anchor;
        return true;
    }

    @Override
    public void clear() {
        numberOfElements = 0;
        allocatedSize = numberOfElements;
        extendArray();
    }

    @Override
    public T get(int index) {
        if (index >= numberOfElements || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.numberOfElements);
        }
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= numberOfElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.numberOfElements);
        }
        array[index] = element;
        return (T) array;
    }

    @Override
    public void add(int index, T element) {
        if (index > numberOfElements || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, this.numberOfElements));
        }
        Object[] storage = Arrays.copyOf(array, numberOfElements);
        numberOfElements += 1;
        allocatedSize = numberOfElements;
        extendArray();
        int arrayIndex = 0;
        for (int i = 0; i < numberOfElements; i++) {
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
        if (index > numberOfElements || index < 0 || isEmpty()) {
            if (index < 0) {
                throw new IndexOutOfBoundsException(Integer.toString(index));
            }
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.numberOfElements);
        }
        Object[] storage = Arrays.copyOf(array, numberOfElements);
        numberOfElements -= 1;
        allocatedSize = numberOfElements;
        extendArray();
        int skipIndex = 0;
        for (int i = 0; i < numberOfElements; i++) {
            if (i == index) {
                skipIndex = 1;
            }
            array[i] = storage[i + skipIndex];
        }
        return (T) array;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < numberOfElements; i++) {
            if (areEqual(array[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = numberOfElements - 1; i >= 0; i--) {
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
                return cursor + 2 <= numberOfElements;
            }

            @Override
            public T next() {
                if (cursor + 1 >= numberOfElements) {
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
        if (index > numberOfElements || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListIterator<T>() {
            int cursor = index - 1;

            @Override
            public boolean hasNext() {
                return cursor + 2 <= numberOfElements;
            }

            @Override
            public T next() {
                if (cursor + 1 > numberOfElements || isEmpty()) {
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
        if (toIndex > numberOfElements || fromIndex < 0 || fromIndex > toIndex) {
            if (fromIndex < 0 || toIndex > this.numberOfElements) {
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
        copyObjectsToCollections(array, this.numberOfElements, storage);
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
        Object[] mirror = Arrays.copyOf(array, this.numberOfElements);
        return Arrays.hashCode(mirror) == o.hashCode();
    }

    private void extendArray() {
        if (allocatedSize == 0) {
            allocatedSize = 10;
        }
        allocatedSize += allocatedSize / 2;
        array = new Object[allocatedSize];
    }

    private void reSizeArray() {
        Object[] storage = Arrays.copyOf(array, numberOfElements - 1);
        extendArray();
        System.arraycopy(storage, 0, array, 0, numberOfElements);
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
        return numberOfElements >= allocatedSize;
    }

    private void copyObjectsToCollections(Object[] source, int sourceSize, Collection<T> dest) {
        dest.clear();
        for (int i = 0; i < sourceSize; i++) {
            dest.add((T) source[i]);
        }
    }
}
