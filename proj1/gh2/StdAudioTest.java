package gh2;

import edu.princeton.cs.algs4.StdAudio;

public class StdAudioTest {
    public static void main(String[] args) {
        // Play a 440 Hz tone for 2 seconds
        double frequency = 440.0;
        int sampleRate = 44100;
        double duration = 2.0; // in seconds
        int N = (int) (sampleRate * duration);
        double[] samples = new double[N];

        // Generate the samples
        for (int i = 0; i < N; i++) {
            samples[i] = Math.sin(2 * Math.PI * frequency * i / sampleRate);
        }

        // Play the sound
        StdAudio.play(samples);
    }
}
