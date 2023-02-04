package chapter1;

public class SafeDemo implements Runnable{

    public static final int LOOP_NUMBER = 10000;

    private int value;

    public synchronized int getValue() {
        return value++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            SafeDemo safeDemo = new SafeDemo();

            Thread thread1 = new Thread(safeDemo, "thread 1");
            Thread thread2 = new Thread(safeDemo, "thread 2");

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // all pass and do not print any log
            if (safeDemo.value != 2*LOOP_NUMBER) {
                System.out.println(String.format("The count name should be %d, but the result is: %s", 2*LOOP_NUMBER,  safeDemo.value == 2*LOOP_NUMBER));

                System.out.println(safeDemo.value);
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
