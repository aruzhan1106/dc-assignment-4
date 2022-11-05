import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.System;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue;
    ArrayList<Integer> primeCount = new ArrayList<>();
    boolean clientsStarted = false;
    static long startTime = 0;
    static long endTime = 0;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public Integer pollElem() throws RemoteException {
        if (!clientsStarted) {
            startTime = System.nanoTime();
        }
        clientsStarted = true;
        return this.queue.poll();
    }

    @Override
    public void addElem(int num) throws RemoteException {
        this.queue.add(num);
        System.out.println("Added: " + num);
    }

    public void store(int num) throws RemoteException {
        primeCount.add(num);
        if (queue.isEmpty()) {
            sumPrimeNumbers(primeCount);
        }
    }
    @Override
    public void sumPrimeNumbers(ArrayList<Integer> primeList) throws RemoteException {
        int sum = 0;
        for (int prime: primeList) {
            sum += prime;
        }
        System.out.println("Sum of number of primes: " + sum);
        //long seconds = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        System.out.println("Time:" + (System.nanoTime() - startTime));
    }
}
