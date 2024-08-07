public class Implements {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Worker());
        thread1.start();

    }
}

class implementsWorker implements Runnable {

    public void run() {

        for (long i = 0; i < 3000000; i++)
            System.out.println("i =" + i);

    }

}
