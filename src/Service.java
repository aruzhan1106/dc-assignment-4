import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface for a service which will be accessible remotely
 */
public interface Service extends Remote {
    Integer pollElem() throws RemoteException;
    void addElem(int num) throws RemoteException;
    void store(int num) throws RemoteException;
    void sumPrimeNumbers(ArrayList<Integer> primeList) throws RemoteException;
}
