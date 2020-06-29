public class Manager extends Thread {

    public static long time = System.currentTimeMillis();
    public static boolean storeOpened = true;
    public static boolean readyToEnter = false;
    public static boolean onLine = false;
    public static boolean waitingOnline = false;

    public Manager() {
        setName("Manager");
        start();
    }

    public void msg(String m) { System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }
}
