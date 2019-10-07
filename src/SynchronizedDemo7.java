public class SynchronizedDemo7 {
    public static void main(String[] args) {
        SyncThread7 syncThread1 = new SyncThread7();
        SyncThread7 syncThread2 = new SyncThread7();

        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");

        thread1.start();
        thread2.start();
    }
}

class SyncThread7 implements Runnable {
    private static int count;

    public SyncThread7() {
        count = 0;
    }

    public static void method() {
        synchronized (SyncThread7.class) {
            for (int i = 0; i < 5; i ++) {
                System.out.println(Thread.currentThread().getName() + " : " + (++count));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public synchronized void run() {
        method();
    }
}
