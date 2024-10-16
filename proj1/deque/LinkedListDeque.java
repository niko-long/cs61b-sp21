package deque;

public class LinkedListDeque<T>{
    private class Node{
        public T item;
        public Node next;

        public Node(T i, Node n){
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null);
        size = 0;

    }

    public void addFirst(T x){
        Node oldFrontNode = sentinel.next;
        Node newNode = new Node(x, oldFrontNode);
        sentinel.next = newNode;
        size += 1;
    }
    public int size(){
        return size;
    }
    public void addLast(T x){
        size += 1;
        Node p = sentinel;

        while (p.next != null){
            p = p.next;
        }
        p.next = new Node(x, null);

    }

    public boolean isEmpty(){
        Node p = sentinel;
        return p.next == null;
    }

    private Node getLastNode(){
        Node p = sentinel;
        while(p.next != null){
            p = p.next;
        }
        return p;
    }
    public void printDeque(){
        for (Node p =sentinel.next; p.next != null; p = p.next){
            System.out.print(p.item + "");
        }
        System.out.println();
    }

    public T removeFirst(){
        Node back = getLastNode();
        if (back == sentinel){
            return null;
        }
        size = size - 1;
        Node p = sentinel;
        p.next = p.next.next;
        return back.item;
    }

    public T removeLast(){
        Node back = getLastNode();
        if(back == sentinel){
            return null;
        }
        size = size - 1;
        Node p = sentinel;
        while(p != back){
            p = p.next;
        }
        p.next = null;
        return back.item;
    }
    public T get(int index){
        return null;
    }
    public T getRecursive(int index){
        return null;
    }


}
