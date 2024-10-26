package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    @Test
    public void maxWithComparatorTest() {
        // 使用传统的匿名内部类实现 Comparator
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b); // 直接使用 Integer 的 compareTo 方法
            }
        };

        MaxArrayDeque<Integer> maxDeque = new MaxArrayDeque<>(integerComparator);

        maxDeque.addLast(1);
        maxDeque.addLast(3);
        maxDeque.addLast(2);

        assertEquals("Max should be 3", Integer.valueOf(3), maxDeque.max());
    }

    @Test
    public void maxWithCustomComparatorTest() {
        // 使用传统的匿名内部类实现 Comparator
        Comparator<Integer> descendingComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a; // 降序排列
            }
        };

        MaxArrayDeque<Integer> maxDeque = new MaxArrayDeque<>(descendingComparator);

        maxDeque.addLast(1);
        maxDeque.addLast(3);
        maxDeque.addLast(2);
        maxDeque.printDeque();
        assertEquals("Max should be 1 when using descending comparator", Integer.valueOf(1), maxDeque.max(descendingComparator));
    }

    @Test
    public void maxOnEmptyDequeTest() {
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };

        MaxArrayDeque<Integer> maxDeque = new MaxArrayDeque<>(integerComparator);

        assertNull("Max should be null when deque is empty", maxDeque.max());
        assertNull("Max should be null when using custom comparator on empty deque", maxDeque.max(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        }));
    }

    @Test
    public void maxWithEqualElementsTest() {
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };

        MaxArrayDeque<Integer> maxDeque = new MaxArrayDeque<>(integerComparator);

        maxDeque.addLast(5);
        maxDeque.addLast(5);
        maxDeque.addLast(5);

        assertEquals("Max should be 5", Integer.valueOf(5), maxDeque.max());
    }
}
