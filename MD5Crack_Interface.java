

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MD5Crack_Interface extends Remote{
    public void crackPassword(String hashedMD5, int numberOfThreads, int passwordLength, int server) throws RemoteException;

    public List<String> getPassword() throws RemoteException;

    public int getTotalThreads() throws RemoteException;

    public void stopAllThreads() throws RemoteException;

    public boolean isFound() throws RemoteException;
}