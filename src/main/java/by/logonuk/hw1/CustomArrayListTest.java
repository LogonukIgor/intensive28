package by.logonuk.hw1;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayListTest {
    public static void main(String[] args) {

        CustomArrayList<Integer> c = new CustomArrayList<>();

        // add(E element) test
        c.add(9);
        c.add(8);
        c.add(8);
        c.add(10);
        c.add(20);
        c.add(1);
        c.add(5);

        System.out.println("add(E element) test:");
        System.out.println(c + "\n");

        // add(int index, E element) test
        c.add(2, 99);

        System.out.println("add(int index, E element) test:");
        System.out.println(c + "\n");

        // addAll(Collection<? extends E> c) test

        List<Integer> listForAdd = new ArrayList<>();
        listForAdd.add(7);
        listForAdd.add(7);
        listForAdd.add(7);
        listForAdd.add(5);
        listForAdd.add(7);

        c.addAll(listForAdd);

        System.out.println("addAll(Collection<? extends E> c) test:");
        System.out.println(c + "\n");

        // get(int index) test
        System.out.println("get(int index) test");
        System.out.println(c.get(3) + "\n");

        // sort(Comparator<? super E> c) test (quick sort)
        c.sort(new ComparatorForTest());
        System.out.println("sort(Comparator<? super E> c) test (QUICK SORT):");
        System.out.println(c + "\n");

        // remove(int index) test
        c.remove(1);
        System.out.println("remove(int index) test:");
        System.out.println(c + "\n");

        // remove(int index) test
        c.remove(new Integer(7));
        System.out.println("remove(E element) test:");
        System.out.println(c + "\n");

        // isEmpty() and clear() test
        System.out.println("isEmpty() and clear() test:");
        System.out.println(c.isEmpty());
        c.clear();
        System.out.println(c.isEmpty());
    }
}
