package gh2;


import deque.ArrayDeque;
import deque.Deque;


//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */

    private Deque<Double> buffer;


    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int capacity = (int) Math.round(this.SR / frequency);
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i += 1) {
            buffer.addFirst(0.0);
        }
    }



    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        /*
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
            Double item = Math.random() - 0.5;
            buffer.addLast(item); //这个版本会陷入无限循环
        }

         */

        for (int i = 0; i < buffer.size(); i += 1) {
            buffer.removeFirst();
            Double item = Math.random() - 0.5;
            buffer.addLast(item);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        Double c = buffer.removeFirst();
        Double d = buffer.get(0);
        Double item = DECAY * 0.5 * (c + d);
        buffer.addLast(item);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.get(0);
    }
}
