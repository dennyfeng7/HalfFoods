import java.util.LinkedList;

public class Employee extends Thread {

    public static long time = System.currentTimeMillis();
    public static LinkedList<Customer> readyToCheckout = new LinkedList<>();


    public void run() {
        msg("waiting for customer");
        while(true) {
            if(Customer.onLine) {
                msg("This is the size of the checkout counter" + Main.counter.size());
            }
        }
    }

    public void msg(String m) { System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

    public Customer counter() {
        return Main.counter.remove(0);
    }
}
