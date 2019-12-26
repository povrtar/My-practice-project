
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CustomerProducer {
    private static volatile boolean cancelled = false;
    static volatile boolean empty = false;
    static Queue<Integer> amounts = new ConcurrentLinkedQueue<>();

    public static synchronized void add(int amount) {
        if (amount == 0) {
            cancelled = true;
        } else {
            amounts.offer(amount);
        }
    }

    public int next() {
        int amount;
        if (amounts.isEmpty()) {
            empty = true;
            System.out.println("There is now customers in the queue!");
            return -1;
        } else {
            empty = false;
        }
        amount = amounts.remove();
        return amount;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
