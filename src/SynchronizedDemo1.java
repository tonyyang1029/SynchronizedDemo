/**
 * https://blog.csdn.net/qq_22339457/article/details/82386545
 */

public class SynchronizedDemo1 {
    public static void main(String[] args) throws InterruptedException {
        AccountingSyncClass accountingSyncClass = new AccountingSyncClass();
        AccountingSyncClass accountingSyncClass1 = new AccountingSyncClass();
        AccountingSyncClass accountingSyncClass2 = new AccountingSyncClass();

        Thread t1 = new Thread(accountingSyncClass1);
        Thread t2 = new Thread(accountingSyncClass2);

        accountingSyncClass1.methodtype = 2;
        t1.start();
        t1.join();

        accountingSyncClass2.methodtype = 2;
        t2.start();
        t2.join();

        System.out.println(accountingSyncClass.i);
    }
}

class AccountingSyncClass implements Runnable {
    static int i = 0;
    static boolean flag = true;
    public int methodtype = 0;

    public static synchronized void increase() {
        i++;
    }

    public synchronized void increase2() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            if (methodtype == 1) {
                increase();
            }
            else if (methodtype == 2) {
                increase2();
            }
            else {
                System.out.println("Method Not Found");
            }
        }
    }
}