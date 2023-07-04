package by.logonuk.hw1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int size;

    public CustomArrayList() {
        super();
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(Collection<? extends E> c) {
        super();
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        while (size + c.size() >= array.length) {
            grow();
        }
        List<? extends E> collect = c.stream().collect(Collectors.toList());
        for (int i = 0; i < c.size(); i++) {
            array[size++] = collect.get(i);
        }
    }

    private void addValidation() {
        if (size >= array.length) {
            grow();
        }
    }

    public void add(E object) {
        addValidation();
        array[size++] = object;
    }

    public void add(int index, E object) {
        addValidation();
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[index] = object;
        size++;
    }

    public void addAll(Collection<? extends E> c) {
        while (size + c.size() >= array.length) {
            grow();
        }
        List<? extends E> collect = c.stream().collect(Collectors.toList());
        for (int i = 0; i < c.size(); i++) {
            array[size++] = collect.get(i);
        }
    }

    public void clear() {
        int arrayLength = this.array.length;
        size = 0;
        this.array = (E[]) new Object[arrayLength];
    }

    private void grow() {
        E[] tempArray = (E[]) new Object[array.length * 2 + 1];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        this.array = tempArray;
    }

    public E get(int i) {
        return array[i];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        if (index < array.length && index >= 0) {
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            this.size--;
        }
    }

    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)) {
                System.arraycopy(array, i + 1, array, i, array.length - i - 1);
                this.size--;
                break;
            }
        }
    }

    public void sort(Comparator<? super E> c) {
        quicksort(0, size - 1, c);
    }

    private void quicksort(int startIndex, int endIndex, Comparator<? super E> c) {
        if (startIndex < endIndex) {
            int pivotIndex = partition(startIndex, endIndex, c);
            quicksort(startIndex, pivotIndex, c);
            quicksort(pivotIndex + 1, endIndex, c);
        }
    }

    private int partition(int startIndex, int endIndex, Comparator<? super E> c) {
        int pivotIndex = (startIndex + endIndex) / 2;
        E pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true) {

            do {
                startIndex++;
            } while (c.compare(array[startIndex], pivotValue) < 0);

            do {
                endIndex--;
            } while (c.compare(array[endIndex], pivotValue) > 0);

            if (startIndex >= endIndex) return endIndex;

            E temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;

        }
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
