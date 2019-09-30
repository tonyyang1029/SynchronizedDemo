class Counter implements Runnable {
    private int count;

    public Counter() {
        count = 0;
    }

    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + count++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printCount() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count : " + count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        if (threadName.equals("A")) {
            countAdd();
        }
        else if (threadName.equals("B")) {
            printCount();
        }
        else {
            System.out.println("Invalid Thread Name");
        }
    }
}

public class SynchronizedDemo4 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "A");
        Thread t2 = new Thread(counter, "B");

        t1.start();
        t2.start();
    }
}
