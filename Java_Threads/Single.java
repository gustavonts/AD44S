public class Single {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread());

        for (int i = 0; i < 9000000; i++)
            System.out.println("i =" + i);

    }

}