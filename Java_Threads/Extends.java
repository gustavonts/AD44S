public class Extends {

    public static void main(String[] args) {

        Worker worker = new Worker();
        worker.start();

    }
}

class Worker extends Thread {

    public void run() {

        for (long i = 0; i < 3000000; i++)
            System.out.println("i =" + i);

    }

}
