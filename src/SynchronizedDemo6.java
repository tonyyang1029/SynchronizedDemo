public class SynchronizedDemo6 {
    public static void main(String[] args) {
        SyncThreadExt syncThread1 = new SyncThreadExt();
        SyncThreadExt syncThread2 = new SyncThreadExt();

        Thread thread1 = new Thread(syncThread1, "syncThread1");
        Thread thread2 = new Thread(syncThread2, "syncThread2");

        thread1.start();
        thread2.start();
    }
}

class SyncThreadExt implements Runnable {
    private static int count;

    public SyncThreadExt() {
        count = 0;
    }

    public synchronized static void method() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void run() {
        method();
    }
}
