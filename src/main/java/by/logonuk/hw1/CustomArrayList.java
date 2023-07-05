package by.logonuk.hw1;

import java.util.Collection;
import java.util.Comparator;

public interface CustomArrayList<E> {

    // Default initial array size
    int DEFAULT_CAPACITY = 10;

    // Appends the specified element to the end of this list.
    void add(E object);

    /*
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements
     * to the right (adds one to their indices).
     */
    void add(int index, E object);

    /*
     * Appends all of the elements in the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is undefined if the specified collection is modified while the operation
     * is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty).
     */
    void addAll(Collection<? extends E> c);

    /*
     * Returns the element at the specified position in this list.
     * Params: index – index of the element to return
     * Returns: the element at the specified position in this list
     * Throws: IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size())
     */
    E get(int i);

    /*
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     * Params: index – the index of the element to be removed
     */
    void remove(int index);

    /*
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If this list does not contain the element, it is unchanged. More formally,
     * removes the element with the lowest index i such that Objects.equals(o, get(i)) (if such an element exists).
     * Returns true if this list contained the specified element
     * (or equivalently, if this list changed as a result of the call).
     * Params: E element – element to be removed from this list, if present
     */
    void remove(E element);

    /*
     * Data sorting
     * Params: Comparator - to set the sort rule
     */
    void sort(Comparator<? super E> c);

    /*
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    void clear();

    // Returns:true if this list contains no elements
    boolean isEmpty();

    // Returns: the number of elements in this list
    int size();
}
