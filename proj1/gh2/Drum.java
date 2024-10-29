package gh2;
public class Drum extends GuitarString {
    private static final double DECAY = 1;
    public Drum(double frequency) {
        super(frequency);
    }
    @Override
    public void tic() {
        super.tic();
        Double c = removeBufferFirst();
        Double d = getBufferfirst();
        Double item;
        if (Math.random() - 0.5 > 0) {
            item = -DECAY * 0.5 * (c + d);
        } else {
            item = DECAY * 0.5 * (c + d);
        }
        addBufferLast(item);
    }
}
