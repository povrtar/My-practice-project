import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {

    public static void main(String[] args) {
        CustomerProducer.amounts.offer(20);
        CustomerProducer.amounts.offer(22);
        CustomerProducer.amounts.offer(35);
        CustomerProducer.amounts.offer(42);

        CustomerProducer customerProducer = new CustomerProducer();
        int customerOrder = 1;

        ExecutorService exec = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            exec.execute(new Naspi(customerProducer));
        }
        Scanner scanner = new Scanner(System.in);
        while (customerOrder > 0) {
            System.out.println("How much  You wish to refuel ?");
            customerOrder = scanner.nextInt();
            CustomerProducer.add(customerOrder);
            CustomerProducer.empty = false;
        }
        scanner.close();
    }
}
