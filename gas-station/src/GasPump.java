import java.util.concurrent.TimeUnit;

public class GasPump {
    private volatile boolean blocked = false;
    private volatile int balance;
    private final int pumpId;

    public GasPump(int pumpId, int balance) {
        this.pumpId = pumpId;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked() {
        blocked = true;
    }

    public int getPumpId() {
        return pumpId;
    }

    public void pour(int amount) throws InterruptedException {
        balance = balance - amount;
        System.out.println("Balance for gas pump " + pumpId + " is " + balance);
        TimeUnit.SECONDS.sleep(amount);
        blocked = false;
    }

}
