package deque;

public class ArrayDeque <T>{
    private T[] items;
    private int size;

    public ArrayDeque(){
        items = (T[]) new Object[100];
        size = 0;
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }


    public void addFirst(T x){
        if(size == items.length){
            resize(size*2);
        }
        T[] newItems = (T[]) new Object[items.length + 1];
        /* 新建一个数组 并将首位设置为x*/
        newItems[0] = x;

        System.arraycopy(items, 0, newItems, 1, items.length);
        items = newItems;
        size = size + 1;
    }

    public void addLast(T x){
        if(size == items.length){
            resize(size*2);
        }
        items[size] = x;
        size = size + 1;
    }

    public boolean isEmpty(){
        return size==0;
    }


    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst(){
        T x = get(0);
        T[] newItems = (T[]) new Object[items.length - 1];
        System.arraycopy(items, 1, newItems, 0, items.length - 1);
        items = newItems;
        size = size - 1;
        return x;
    }

    public T removeLast(){
        T x = get(size-1);
        items[size-1] = null;
        size = size - 1;

        return x;
    }

    public T get(int index){
        return items[index];
    }

}