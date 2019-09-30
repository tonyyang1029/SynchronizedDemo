public class SynchronizedDemo5 {
    public static void main(String[] args) {
        Account account = new Account("Trump", 10000.0f);
        AccountOperator accountOperator = new AccountOperator(account);

        final int THREAD_NUM = 5;
        Thread thread[] = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            thread[i] = new Thread(accountOperator, "Thread " + i);
            thread[i].start();
        }
    }
}

class Account {
    String name;
    float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public void deposit(float amount) {
        this.amount += amount;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(float amount) {
        this.amount -= amount;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float getBalance() {
        return amount;
    }
}

class AccountOperator implements Runnable {
    private Account account;

    public AccountOperator(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        /*synchronized (account) {
            account.deposit(500);
            account.withdraw(500);
            System.out.println(Thread.currentThread().getName() + " : " + account.getBalance());
        }*/

        account.deposit(500);
        account.withdraw(500);
        System.out.println(Thread.currentThread().getName() + " : " + account.getBalance());
    }
}