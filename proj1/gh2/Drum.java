package gh2;

import deque.ArrayDeque;
import deque.Deque;

/**
 * A class that simulates a drum sound using a modified Karplus-Strong algorithm.
 */
public class Drum {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0;  // energy decay factor for drum
    private Deque<Double> buffer;

    /* Create a drum of the given frequency. */
    public Drum(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>();
        // Initialize the buffer with zeros
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }
    }

    /* Pluck the drum by replacing the buffer with random noise. */
    public void pluck() {
        // Fill the buffer with random noise between -0.5 and 0.5
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            Double item = Math.random() - 0.5; // generate random noise
            buffer.addLast(item);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * a modified Karplus-Strong algorithm for drums.
     */
    public void tic() {
        // Get the first two values from the buffer
        Double c = buffer.removeFirst();
        Double d = buffer.get(0); // Peek at the next value

        // Create a new value based on the average of c and d
        Double item;
        if (Math.random() - 0.5 > 0) {
            item = -DECAY * 0.5 * (c + d); // Flip the sign randomly
        } else {
            item = DECAY * 0.5 * (c + d);
        }

        // Add the new value back to the buffer
        buffer.addLast(item);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0); // Return the current sound sample
    }
}
