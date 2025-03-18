
public class ThreadTester {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is running " + i);
            }
        }, "Test-1-thread");
        thread1.start();
    }
}
