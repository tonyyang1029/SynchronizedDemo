public class SynchronizedDemo2 {
    public static boolean flag = true;
    public static StringBuffer sbf = new StringBuffer("123");

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    synchronized (sbf) {
                        try {
                            sbf.wait();
                            System.out.println("t1 awake");
                            flag = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sbf) {
                    System.out.println(sbf);
                    sbf.notify();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
