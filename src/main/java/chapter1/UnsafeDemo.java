package chapter1;

public class UnsafeDemo implements Runnable{
    public static final int LOOP_NUMBER = 10000;

    private int value;

    public int getValue() {
        return value++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            UnsafeDemo unsafeDemo = new UnsafeDemo();

            Thread thread1 = new Thread(unsafeDemo, "thread 1");
            Thread thread2 = new Thread(unsafeDemo, "thread 2");

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // about 15 times print
            if (unsafeDemo.value != 2*LOOP_NUMBER) {
                System.out.println(String.format("The count name should be %d, but the result is: %s", 2*LOOP_NUMBER,  unsafeDemo.value == 2*LOOP_NUMBER));

                System.out.println(unsafeDemo.value);
            }
        }
    }

    @Override
    public void run() {
        for (int k = 0; k < LOOP_NUMBER; k++) {
            this.getValue();
        }
    }
}
