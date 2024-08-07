package MandelbrotSerial;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MandelbrotImage {
    private final int width;
    private final int height;
    private final int maxIter;

    public MandelbrotImage(int width, int height, int maxIter) {
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }

    public void generateImage(String fileName) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        long startTime = System.nanoTime();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - width / 2) * 4.0 / width;
                double cY = (y - height / 2) * 4.0 / width;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                image.setRGB(x, y, iter | (iter << 8));
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Execution time (serial): " + duration / 1000.0 + " seconds");

        ImageIO.write(image, "png", new File(fileName));
    }

    public static void main(String[] args) {
        int width = 1600;   // Ajustado para maior resolução
        int height = 1600;  // Ajustado para maior resolução
        int maxIter = 5000; // Ajustado para mais iterações
        String fileName = "mandelbrot_high_res.png";

        MandelbrotImage mandelbrotImage = new MandelbrotImage(width, height, maxIter);
        try {
            mandelbrotImage.generateImage(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}