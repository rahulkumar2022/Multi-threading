
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockEpl {

    public static void main(String[] args) {
        Menu menu = new Menu();

        // Creating chef thread to update the menu
        ChefThread chefThread = new ChefThread(menu);
        chefThread.start();

        for (int i = 0; i <= 4; i++) {
            CustomerThread customerThread = new CustomerThread(menu);
            customerThread.start();
        }
    }
}

class Menu {

    private String[] items = {"Pizza", "Burger", "Pasta", "Coke"};
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public String[] getMenu() {
        lock.readLock().lock();
        try {
            System.out.println("Menu viewed by a customer ");
            return items;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void updateMenu(String[] newItems) {
        lock.writeLock().lock();
        try {
            System.out.println("Menu updated by the chef");
            items = newItems;
        } finally {
            lock.writeLock().unlock();
        }
    }
}

class CustomerThread extends Thread {

    private Menu menu;

    public CustomerThread(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        menu.getMenu();
    }
}

class ChefThread extends Thread {

    private Menu menu;

    public ChefThread(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        String[] newMenu = {"Pizza", "Burger", "Pasta", "Coke", "Ice Cream"};
        menu.updateMenu(newMenu);
    }
}
