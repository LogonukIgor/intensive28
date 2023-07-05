package by.logonuk.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private CustomArrayListImpl customArrayList;

    @BeforeEach
    public void setCustomArrayList(){
        this.customArrayList = new CustomArrayListImpl();
    }

    @Test
    void add_elementWithAndWithoutIndex_success() {
        // given
        customArrayList.add(1);
        customArrayList.add(1, 2);
        // when
        assertEquals(2, customArrayList.size());
    }

    @Test
    void addAll_success() {
        // given
        customArrayList.addAll(List.of(10, 12, 7));
        // when
        assertEquals(3, customArrayList.size());
    }

    @Test
    void clear_success() {
        // given
        customArrayList.addAll(List.of(10, 12, 7));
        customArrayList.clear();
        // when
        assertEquals(0, customArrayList.size());
    }

    @Test
    void get_success() {
        // given
        customArrayList.addAll(List.of(10, 12, 7));
        // when
        assertEquals(12, customArrayList.get(1));
    }

    @Test
    void isEmpty_success() {
        assertEquals(true, customArrayList.isEmpty());
        customArrayList.add(1);
        assertEquals(false, customArrayList.isEmpty());
    }

    @Test
    void remove_byIndex_success() {
        // given
        customArrayList.add(1);
        customArrayList.remove(0);
        // when
        assertEquals(0, customArrayList.size());
    }

    @Test
    void remove_element_success() {
        // given
        customArrayList.addAll(List.of(1, 2, 3, 4, 5));
        customArrayList.remove(new Integer(4));
        // when
        assertEquals(4, customArrayList.size());
        assertEquals("[1, 2, 3, 5, null, null, null, null, null, null]", customArrayList.toString());
    }

    @Test
    void sort_ascendingQuickSort_success() {
        // given
        customArrayList.addAll(List.of(4, 3, 6, 9, 10, 2, 5, 8, 7));
        customArrayList.sort(new ComparatorForSorting());
        //when
        assertEquals("[2, 3, 4, 5, 6, 7, 8, 9, 10, null]", customArrayList.toString());
    }

    @Test
    void size_success() {
        assertEquals(0, customArrayList.size());
        customArrayList.add(1);
        assertEquals(1, customArrayList.size());
        customArrayList.addAll(List.of(1, 2, 3));
        assertEquals(4, customArrayList.size());
    }
}