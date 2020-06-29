import java.util.Random;

public class Customer extends Thread {

    public static int customerNumber;
    public static int storeCounter = 0;
    public static int totalCustomersServed = Main.numOfCustomers;
    public static int totalFinished = 0;

    public static boolean onLine = true;
    public static boolean arrived = false;
    public static boolean sleeping = false;
    public static boolean storeFull = false;
    public static boolean finishedShopping = false;

    public static long time = System.currentTimeMillis();



    public Customer(int customerNumber) {
        this.customerNumber = customerNumber;
        setName("Customer-" + customerNumber);
        arrived = true;
        start();
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        msg("is waiting on line of Half Foods Supermarket.");

        while (true) {
            //while the passengers are at the airport and the counter can accept them
            while (arrived && Manager.storeOpened && (totalCustomersServed > 0) && (storeCounter != 6)) {

                //increment counter
                storeCounter++;

                totalCustomersServed--;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                msg("is inside supermarket shopping.");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                msg("is done shopping and is heading to line for checkout.");


                //Customer goes to checkout line when done
                if (counterAvailable()) {
                    msg("is on the check-out line");
                    Main.counter.add(this);
                    msg("is now number " + Main.counter.size() + " on the check-out line.");
                    onLine = true;
                } else {
                    storeFull = true;
                }

                //Customer rush to checkout after finished shopping
                rushToCheckout();

                //sleep
                msg("Waiting to checkout");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                msg("has checked out. But there is an accident. Sleeping now...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                msg("has successfully left the parking lot. ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                //Busy wait while waiting on line
                while (!Manager.waitingOnline) {}


                if (Manager.onLine) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }

                while (waitOnLine()) {
                }

                //Elderly are able to yield twice.
                for (int i = 0; i < Main.storeCapacity; i++) {
                    Thread.currentThread().yield();
                    Thread.currentThread().yield();
                }

                while (Customer.finishedShopping) {
                    try {
                        Thread.sleep(1000);
                        sleeping = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        msg("has been woken up");
                    }
                }

            } //while

//            } //if

            storeCounter--;
            totalFinished++;

        }//while

    }// run



    public void msg(String m) { System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

    public static boolean counterAvailable() {
        if (Main.counter.size() < Main.numSelfCheckout) return true;
        return false;
    }

    //simulate rushing by getting and setting priority
    public void rushToCheckout() {
        int priority = Thread.currentThread().getPriority();
        Thread.currentThread().setPriority(priority + 1);

        //sleep for a random time
        try {
            Thread.sleep((long) Math.random() * 50000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        //setPriority back to default
        Thread.currentThread().setPriority(priority);
    }

    public int getNum() {
        return this.customerNumber;
    }

    public boolean waitOnLine() {
        if (Manager.readyToEnter) return true;
        return false;
    }
}
