package gh2;

import edu.princeton.cs.algs4.StdDraw;

public class StdDrawTest {
    public static void main(String[] args) {
        // Set up the drawing canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        // Draw a filled square
        StdDraw.filledSquare(0.5, 0.5, 0.3);

        // Print instructions
        System.out.println("Press any key to change color...");

        // Main loop
        while (true) {
            // Check if a key is pressed
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'r') {
                    StdDraw.setPenColor(StdDraw.RED);
                } else if (key == 'g') {
                    StdDraw.setPenColor(StdDraw.GREEN);
                } else if (key == 'b') {
                    StdDraw.setPenColor(StdDraw.BLUE);
                }
                StdDraw.filledSquare(0.5, 0.5, 0.3); // Redraw with new color
            }
        }
    }
}
