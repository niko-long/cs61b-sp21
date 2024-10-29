package gh2;


public class Harp extends GuitarString{
    private static final double DECAY = 0.995;
    public Harp(double frequency) {
        super(frequency/2);
    }
    @Override
    public void tic() {
        Double c = buffer.removeFirst();
        Double d = buffer.get(0);
        Double item = -DECAY * 0.5 * (c + d);
        buffer.addLast(item);
    }
}
