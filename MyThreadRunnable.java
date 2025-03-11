
public class MyThreadRunnable implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("New Thread Entry Point started");
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        MyThreadRunnable myThreadRunnable = new MyThreadRunnable();
        Thread t1 = new Thread(myThreadRunnable);
        t1.start();

    }

}
