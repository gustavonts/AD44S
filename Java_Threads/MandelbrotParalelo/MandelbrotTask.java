package MandelbrotParalelo;

import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class MandelbrotTask extends RecursiveAction {
    private static final int THRESHOLD = 100; // Limite para dividir a tarefa
    private final int startRow;
    private final int endRow;
    private final BufferedImage image;
    private final int width;
    private final int height;
    private final int maxIter;

    public MandelbrotTask(int startRow, int endRow, BufferedImage image, int width, int height, int maxIter) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.image = image;
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }

    @Override
    protected void compute() {
        if (endRow - startRow <= THRESHOLD) {
            for (int y = startRow; y < endRow; y++) {
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
        } else {
            int middle = (startRow + endRow) / 2;
            MandelbrotTask task1 = new MandelbrotTask(startRow, middle, image, width, height, maxIter);
            MandelbrotTask task2 = new MandelbrotTask(middle, endRow, image, width, height, maxIter);
            invokeAll(task1, task2);
        }
    }
}
