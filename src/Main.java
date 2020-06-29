import java.util.LinkedList;

public class Main {
    public static Thread[] customers;
    public static Thread employee;
    public static Thread manager;
    public static LinkedList<Customer> counter = new LinkedList<>();

    //Global Variables - Initial Declarations
    public static int storeCapacity = 6;
    public static int numOfCustomers = 20;
    public static int numSelfCheckout = 4;

    public static void main(String[] args) {

        //if command line overrides
        if(args.length > 0) {
            numOfCustomers = Integer.parseInt(args[0]);
        }

        employee = new Thread(new Employee());

        customers = new Thread[numOfCustomers];
        for (int i = 0; i < numOfCustomers; i++) {
            customers[i] = new Customer(i++);
        }

        manager = new Thread(new Manager());
    }

}
