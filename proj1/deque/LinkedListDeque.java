package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {
    private class Node {
        public Node prev; //try IMO
        public T item;
        public Node next;

        public Node(Node m,T i, Node n) {
            prev = m; //try IMO
            item = i;
            next = n;
        }
    }

    public Node sentinel;
    private int size;
    private Node current;


    public LinkedListDeque() {
        sentinel = new Node(null, null, null); //try IMO
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque.LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizPos = sentinel.next;
        public boolean hasNext() {return wizPos != null; }
        public T next() {
            T returnItem = wizPos.item;
            wizPos = wizPos.next;
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
        if (other.getClass() != this.getClass()) {
            return false;
        }
        LinkedListDeque<T> o = (LinkedListDeque<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(!o.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addFirst(T x) {
        Node oldFrontNode = sentinel.next;
        Node newNode = new Node(sentinel, x, oldFrontNode);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node oldEndNode = sentinel.prev;
        Node newNode = new Node(oldEndNode, x, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
        /* Node p = sentinel;
         while (p.next != null){
            p = p.next;
        } */

    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getLastNode() {
        Node p = sentinel;
        if(isEmpty()){
            return null;
        }
        return p.prev;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        Node p = sentinel;
        Node front = p.next;
        p.next = p.next.next;
        p.next.prev = sentinel;
        size = size - 1;
        return front.item;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        Node p = sentinel;
        Node back = p.prev;
        p.prev = p.prev.prev;
        p.prev.next = p;
        size = size -1;
        return back.item;
    }


    @Override
    public T get(int index) {
        Node p = sentinel;
        if(size == 0) {
            return null;
        }
        for(int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }


    public T getRecursive(int index) {
        if(size == 0 || index >= size) {
            return null;
        }else{
            current = sentinel.next;
            return getRecursiveHelper(index);
        }
    }
    public T getRecursiveHelper(int index) {
        if(index == 0) {
            return current.item;
        }else{
            current = current.next;
            return getRecursiveHelper(index-1);
        }
    }

}
