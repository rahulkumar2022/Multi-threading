

public class MyThreadExtend extends Thread{
    @Override
    public void run(){
        System.out.println("New Thread Entry Point started");
        System.out.println("Thread is running");
    }
    public static void main(String[] args) {
        MyThreadExtend t1 = new MyThreadExtend();
        t1.start();
    }
}