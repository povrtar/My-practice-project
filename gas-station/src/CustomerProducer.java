
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CustomerProducer implements Runnable {
    private static volatile boolean cancelled = false;
    volatile boolean empty = false;
    private static Queue<Integer> amounts = new ConcurrentLinkedQueue<>();
    private int customerOrder = 1;
    {
        amounts.add(20);
        amounts.add(25);
        amounts.add(27);
        amounts.add(32);
    }

    public synchronized void add(int amount) {
        if (amount == 0) {
            cancelled = true;
        } else {
            amounts.offer(amount);
        }
    }

    public int next() throws InterruptedException {
        int amount;
        amount = amounts.remove();
        if (amounts.isEmpty()) {
            empty = true;
        }
        return amount;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Queue<Integer> getAmounts() {
        return amounts;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (customerOrder > 0) {
            System.out.println("How much  You wish to refuel ?");
            customerOrder = scanner.nextInt();
            add(customerOrder);
            empty = false;
        }
        scanner.close();
    }
}