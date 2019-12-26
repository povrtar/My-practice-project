public class Naspi implements Runnable {
    private static volatile GasPump gasPump1 = new GasPump(1, 200);
    private static volatile GasPump gasPump2 = new GasPump(2, 220);
    private static volatile GasPump gasPump3 = new GasPump(3, 190);
    private static volatile GasPump gasPump4 = new GasPump(4, 270);
    private volatile CustomerProducer customerProducer;
    private volatile int amount;
    private volatile boolean solved;

    public Naspi(CustomerProducer theCustomerProducer) {
        customerProducer = theCustomerProducer;
    }

    @Override
    public void run() {
        while (!customerProducer.isCancelled()) {
            synchronized (this) {
                while (CustomerProducer.empty) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                amount = customerProducer.next();
                solved = false;
            }
            while (!solved) {
                if (!gasPump1.blocked && amount <= gasPump1.getBalance()) {
                    try {
                        gasPump1.setBlocked();
                        System.out.println(
                                "GasPump number 1 pouring " + amount + " liters !Please, wait for a while...");
                        gasPump1.pour(amount);
                        solved = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else if (!gasPump2.blocked && amount <= gasPump2.getBalance()) {
                    try {
                        gasPump2.setBlocked();
                        System.out.println(
                                "GasPump number 2 pouring " + amount + " liters !Please, wait for a while...");
                        gasPump2.pour(amount);
                        solved = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (!gasPump3.blocked && amount <= gasPump3.getBalance()) {
                    try {
                        gasPump3.setBlocked();
                        System.out.println(
                                "GasPump number 3 pouring " + amount + " liters !Please, wait for a while...");
                        gasPump3.pour(amount);
                        solved = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (!gasPump4.blocked && amount <= gasPump4.getBalance()) {
                    try {
                        gasPump4.setBlocked();
                        System.out.println(
                                "GasPump number 4 pouring " + amount + " liters !Please, wait for a while...");
                        gasPump4.pour(amount);

                        solved = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
