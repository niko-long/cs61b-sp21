package deque;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Random;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());


		lld1.addLast("middle");
		assertEquals(2, lld1.size());


		lld1.addLast("back");
		assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty

		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);
        lld1.removeFirst();
        lld1.removeLast();

        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    public void randomAddRemoveTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Random random = new Random();
        int testCount = 100000; // 随机操作的次数, 设置的较小的时候有一定的概率会通过, 查找不到问题
        int size = 0; // 当前的大小

        for (int i = 0; i < testCount; i++) {
            double action = random.nextDouble(); // 生成 0.0 到 1.0 之间的随机数

            // 根据随机数选择操作
            if (action < 0.05) { // 5%的概率执行 addLast
                int valueToAdd = random.nextInt(100); // 随机生成一个值
                deque.addLast(valueToAdd);
                size++; // 更新大小
            } else if (action < 0.95) { // 90%的概率执行 removeFirst
                if (!deque.isEmpty()) {
                    deque.removeFirst();
                    size--; // 更新大小
                }
            } else { // 5%的概率执行 isEmpty
                boolean expectedEmpty = (size == 0);
                assertEquals("isEmpty() returned incorrect value.", expectedEmpty, deque.isEmpty());
            }

            // 断言当前大小是否正确
            assertEquals("Size is incorrect after operations.", size, deque.size());
        }

        // 测试清空后，再次确认 isEmpty 的行为
        while (!deque.isEmpty()) {
            deque.removeFirst();
        }
        assertTrue("Deque should be empty after removing all elements.", deque.isEmpty());
        assertEquals("Size should be zero after removing all elements.", 0, deque.size());
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }
    @Test
    public void testEqualDequesWithSameElements() {
        LinkedListDeque<Integer> LinkedListDeque = new LinkedListDeque<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        // 添加相同元素到两个 deque 中
        LinkedListDeque.addLast(1);
        LinkedListDeque.addLast(2);
        LinkedListDeque.addLast(3);

        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);

        // 测试两个 deque 是否相等
        assertEquals("LinkedListDeque and ArrayDeque should be equal with same elements",true,arrayDeque.equals(LinkedListDeque));

        // 测试反过来的相等性
        assertEquals("ArrayDeque and LinkedListDeque should be equal with same elements",true,LinkedListDeque.equals(arrayDeque));


    }


    // @Test
    /* Add large number of elements to deque; check if order is correct.*/
    /*
    public void bigLLDequeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    } */

}



