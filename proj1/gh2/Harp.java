package gh2;


public class Harp extends GuitarString {
    private static final double DECAY = 0.995;
    public Harp(double frequency) {
        super(frequency / 2);
    }
    @Override
    public void tic() {
        super.tic();
        Double c = removeBufferFirst();
        Double d = getBufferfirst();
        Double item = -DECAY * 0.5 * (c + d);
        addBufferLast(item);
    }
}
