package gh2;
public class Drum extends GuitarString{
    private static final double DECAY = 1;
    public Drum(double frequency) {
        super(frequency);
    }
    @Override
    public void tic() {
        Double c = buffer.removeFirst();
        Double d = buffer.get(0);
        Double item;
        if (Math.random() - 0.5 > 0) {
            item = -DECAY * 0.5 * (c + d);
        } else {
            item = DECAY * 0.5 * (c + d);
        }
        buffer.addLast(item);
    }
}
