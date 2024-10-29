package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int index = 0;
        for (int i = 0; i < size; i += 1) {
            index = arrayInd(i);
            a[(capacity / 4 + i) % capacity] = items[index];
        }
        items = a;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }


    private int arrayInd(int ind) {
        if (nextFirst + 1 + ind >= items.length) {
            return nextFirst + 1 + ind - items.length;
        } else {
            return nextFirst + 1 + ind;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        private ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Deque)) {
            return false;
        }
        /*
        if (other.getClass() != this.getClass()) {
            System.out.println("other.getClass()" + other.getClass());
            System.out.println("this.getClass()" + this.getClass());
            return false;
        }
         */
        Deque<T> o = (Deque<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(o.get(i))) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst]  = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size = size + 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }

        size = size + 1;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = get(0);
        size = size - 1;
        int index = arrayInd(0);
        items[index] = null;
        nextFirst = index;
        if (size > 0 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = get(size - 1);
        int index = arrayInd(size - 1);
        items[index] = null;
        size = size - 1;
        nextLast = index;
        if (size > 0 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return x;
    }

    @Override
    public T get(int index) {
        return items[arrayInd(index)];
    }

}
