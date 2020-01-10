public class Customer implements Runnable {
    private int order;
    private GasPump gasPump;

    public Customer(int order, GasPump gasPump) {
        this.order = order;
        this.gasPump = gasPump;
    }

    @Override
    public void run() {
        try {
            gasPump.setBlocked();
            System.out.println("GasPump number " + gasPump.getPumpId() + " pouring " + order
                    + " liters !Please, wait for a while...");
            gasPump.pour(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
