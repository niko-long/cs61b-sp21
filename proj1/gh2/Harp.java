package gh2;

import deque.ArrayDeque;

public class Harp extends GuitarString{
    private static final double DECAY = 0.995;
    public Harp(double frequency) {
        super(frequency);
    }
    @Override
    public void tic() {
        Double c = buffer.removeFirst();
        Double d = buffer.get(0);
        Double item = -DECAY * 0.5 * (c + d);
        buffer.addLast(item);
    }


}
