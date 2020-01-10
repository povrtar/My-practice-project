import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GasStationApplication {
    static List<GasPump> pumps = new ArrayList<>();

    private static boolean isBalanceTooLow(int order) {
        int highBalance = 0;
        for (GasPump gasPump : pumps) {
            if (gasPump.getBalance() > highBalance) {
                highBalance = gasPump.getBalance();
            }
        }
        if (highBalance < order) {
            return true;
        } else
            return false;
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i < 5; i++) {
            pumps.add(new GasPump(i, 250));
        }
        CustomerProducer customerProducer = new CustomerProducer();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        exec.execute(customerProducer);
        while (!customerProducer.isCancelled()) {
            boolean solved = false;

            if (!customerProducer.isEmpty()) {
                int order = customerProducer.next();

                while (!solved) {
                    for (GasPump gasPump : pumps) {

                        if (solved == false && !gasPump.isBlocked() && gasPump.getBalance() > order) {
                            gasPump.setBlocked();
                            solved = true;
                            exec.execute(new Customer(order, gasPump));
                        }
                    }
                    if (isBalanceTooLow(order)) {
                        System.out.println("Sorry! Balance is too low in all pumps! Try on the next gas station!");
                        System.out.println("Next customer, please");
                        solved = true;
                    }

                }
            }
        }
    }
}
