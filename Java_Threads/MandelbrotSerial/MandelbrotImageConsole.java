package MandelbrotSerial;

public class MandelbrotImageConsole {
    private final int width;
    private final int height;
    private final int maxIter;

    public MandelbrotImageConsole(int width, int height, int maxIter) {
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }

    public void generateConsoleImage() {
        char[] charset = " .:-=+*%@#".toCharArray();
        StringBuilder sb = new StringBuilder();

        long startTime = System.nanoTime();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - width / 2.0) * 4.0 / width;
                double cY = (y - height / 2.0) * 4.0 / width;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }

                // Map iteration count to a character
                int index = (int) (charset.length * (maxIter - iter) / (double) maxIter);
                if (index >= charset.length) index = charset.length - 1; // Prevent out-of-bounds
                sb.append(charset[index]);
            }
            sb.append('\n');
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Execution time (console): " + duration / 1000.0 + " seconds");

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        int width = 80;   // Ajustado para tamanho menor no console
        int height = 40;  // Ajustado para tamanho menor no console
        int maxIter = 9000000;

        MandelbrotImageConsole mandelbrotImageConsole = new MandelbrotImageConsole(width, height, maxIter);
        mandelbrotImageConsole.generateConsoleImage();
    }
}
