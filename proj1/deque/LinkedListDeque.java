package deque;

public class LinkedListDeque<T>{
    private class Node{
        public Node prev; //try IMO
        public T item;
        public Node next;

        public Node(Node m,T i, Node n){
            prev = m; //try IMO
            item = i;
            next = n;
        }
    }

    public Node sentinel;
    private int size;
    private Node current;


    public LinkedListDeque(){
        sentinel = new Node(null, null, null); //try IMO
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x){
        Node oldFrontNode = sentinel.next;
        Node newNode = new Node(sentinel, x, oldFrontNode);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T x){
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
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    private Node getLastNode(){
        Node p = sentinel;
        if(isEmpty()){
            return null;
        }
        return p.prev;
    }
    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
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

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        Node p = sentinel;
        Node back = p.prev;
        p.prev = p.prev.prev;
        p.prev.next = p;
        size = size -1;
        return back.item;
    }

    public T get(int index){
        Node p = sentinel;
        if(size == 0){
            return null;
        }
        for(int i = 0; i <= index; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if(size == 0 || index >= size){
            return null;
        }else{
            current = sentinel.next;
            return getRecursiveHelper(index);
        }
    }
    public T getRecursiveHelper(int index){
        if(index == 0){
            return current.item;
        }else{
            current = current.next;
            return getRecursiveHelper(index-1);
        }
    }

}
