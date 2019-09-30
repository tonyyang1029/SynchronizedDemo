/**
 * https://blog.csdn.net/sinat_32588261/article/details/72880159
 */

class SyncThread implements Runnable {
    private static int count;

    public SyncThread() {
        count = 0;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + (count++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizedDemo3 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No Parameters");
            return;
        }

        if (args[0].equals("case1")) {
            SyncThread s1 = new SyncThread();
            SyncThread s2 = new SyncThread();
            Thread t1 = new Thread(s1);
            Thread t2 = new Thread(s2);

            t1.start();
            t2.start();
        }
        else if (args[0].equals("case2")) {
            SyncThread s = new SyncThread();
            Thread t1 = new Thread(s);
            Thread t2 = new Thread(s);

            t1.start();
            t2.start();
        }
        else {
            System.out.println("Invalid Parameter");
            return;
        }
    }
}
