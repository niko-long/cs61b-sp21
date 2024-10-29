package gh2;

import deque.ArrayDeque;
import deque.Deque;

/**
 * A class that simulates a harp sound using a modified Karplus-Strong algorithm.
 */
public class Harp {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 0.995; // Energy decay factor for harp
    private Deque<Double> buffer;

    /* Create a harp with a frequency that is half of the given frequency. */
    public Harp(double frequency) {
        // Initialize the buffer based on the modified frequency
        int capacity = (int) Math.round(SR / (frequency / 2));
        buffer = new ArrayDeque<>();

        // Fill the buffer with zeros
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }
    }

    /* Pluck the harp by replacing the buffer with random noise. */
    public void pluck() {
        // Fill the buffer with random noise between -0.5 and 0.5
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            Double item = Math.random() - 0.5; // Generate random noise
            buffer.addLast(item);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the modified Karplus-Strong algorithm for the harp.
     */
    public void tic() {
        // Get the first two values from the buffer
        Double c = buffer.removeFirst();
        Double d = buffer.get(0); // Peek at the next value

        // Create a new value based on the average of c and d
        Double item = -DECAY * 0.5 * (c + d);

        // Add the new value back to the buffer
        buffer.addLast(item);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0); // Return the current sound sample
    }
}
