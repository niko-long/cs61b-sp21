package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int NUM_KEYS = KEYBOARD.length();
    public static final double CONCERT_A = 440.0;


    public static void main(String[] args) {

        GuitarString[] strings = new GuitarString[NUM_KEYS];
        for (int i = 0; i < NUM_KEYS; i += 1) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24.0) / 12.0);
            strings[i] = new GuitarString(frequency);
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                int index = KEYBOARD.indexOf(StdDraw.nextKeyTyped());
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample =  0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}

