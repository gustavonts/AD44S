package MandelbrotParalelo;

import java.io.IOException;

public class MandelbrotSet {
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        int maxIter = 10000;
        String fileName = "mandelbrot_parallel.png";

        MandelbrotImage mandelbrotImage = new MandelbrotImage(width, height, maxIter);

        int[] threadCounts = {1, 2, 4, 6, 8, 16};

        for (int numThreads : threadCounts) {
            try {
                mandelbrotImage.generateImage(fileName, numThreads);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


