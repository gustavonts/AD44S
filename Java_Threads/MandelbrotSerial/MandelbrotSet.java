package MandelbrotSerial;

import java.io.IOException;

public class MandelbrotSet {
    public static void main(String[] args) {
        int width = 8000;
        int height = 8000;
        int maxIter = 3500;
        String fileName = "mandelbrot3.png";

        MandelbrotImage mandelbrotImage = new MandelbrotImage(width, height, maxIter);

        long startTime = System.nanoTime();
        try {
            mandelbrotImage.generateImage(fileName);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; 
            System.out.println("Execution time (serial): " + duration + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
