
import java.util.concurrent.locks.StampedLock;

public class StampedLocks {

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        for (int i = 0; i <= 4; i++) {
            ClientThread clientThread = new ClientThread(account);
            clientThread.start();
        }
    }
}

class BankAccount {

    private double balance = 1000.0;
    private StampedLock lock = new StampedLock();

    public double getBalance() {
        long stamp = lock.tryOptimisticRead();
        double currentBalance = balance;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentBalance = balance;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return currentBalance;
    }

    public void updateBalance(double amount) {
        long stamp = lock.writeLock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " updated the balance: " + balance);
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}

class ClientThread extends Thread {

    private BankAccount account;

    public ClientThread(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        double balance = account.getBalance();
        System.out.println(Thread.currentThread().getName() + " viewed the balance: " + balance);
        account.updateBalance(100);
    }
}
