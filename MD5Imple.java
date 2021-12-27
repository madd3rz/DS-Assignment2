import java.rmi.*;
import java.rmi.server.*;
import java.util.List;

public class MD5Imple extends UnicastRemoteObject implements MD5Crack_Interface {

    int numOfThreads = Runtime.getRuntime().availableProcessors();
    boolean found = false;

    List<String> values;

    protected MD5Imple() throws RemoteException {
        super();
    }

    @Override
    public void crackPassword(String hashedMD5, int numberOfThreads, int passwordLength, int server)
            throws RemoteException {
        Threading.startServer(hashedMD5, numberOfThreads, passwordLength, server);
    }

    public List<String> getPassword() throws RemoteException {
        values = Threading.getPass();
        return (values);
    }

    public int getTotalThreads() {
        return numOfThreads;
    }

    @Override
    public void stopAllThreads() throws RemoteException {
        Threading.stop_threads();
    }

    @Override
    public boolean isFound() throws RemoteException {
        while (Threading.isFound != true) {
            return false;
        }
        return true;
    }

}
