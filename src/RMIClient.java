import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);
            while (true) {
                Integer number = service.pollElem();
                if (number == null) {
                    System.out.println("Received none!");
                    break;
                } else {
                    System.out.println("Received: " + number);
                    service.store(primesLessThan(number));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }

    public static int primesLessThan(int num) {
        int counter = 0;
        for (int i = 1; i < num; i++) {
            if (isPrime(i)) {
                counter++;
            }
        }
        return counter;
    }
}
