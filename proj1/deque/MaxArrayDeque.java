package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;

    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem  = get(0);
        for (int i = 0; i < this.size(); i += 1 ) {
            T curItem = get(i);
            if (comparator.compare(curItem, maxItem) > 0) {
                maxItem = curItem;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem  = get(0);
        for (int i = 0; i < this.size(); i += 1 ) {
            T curItem = get(i);
            if (c.compare(curItem, maxItem) > 0) {
                maxItem = curItem;
            }
        }
        return maxItem;
    }

}
