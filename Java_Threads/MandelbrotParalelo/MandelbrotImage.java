package MandelbrotParalelo;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class MandelbrotImage {
    private final int width;
    private final int height;
    private final int maxIter;

    public MandelbrotImage(int width, int height, int maxIter) {
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }

    public void generateImage(String fileName, int numThreads) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ForkJoinPool pool = new ForkJoinPool(numThreads);

        try {
            MandelbrotTask task = new MandelbrotTask(0, height, image, width, height, maxIter);
            long startTime = System.nanoTime();
            pool.invoke(task);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
            System.out.println("Execution time (parallel, " + numThreads + " threads): " + duration + " ms");
            ImageIO.write(image, "png", new File(fileName));
        } finally {
            pool.shutdown();
        }
    }
}


