import java.rmi.*;
import java.rmi.server.*;
import java.util.List;

public class MD5Imple extends UnicastRemoteObject implements MD5Crack_Interface {

    int numOfThreads = Runtime.getRuntime().availableProcessors();

    // ArrayList<String> values = new ArrayList<>();
    List<String> values;
    protected MD5Imple() throws RemoteException {
        super();
    }

    @Override
    public void crackPassword(String hashedMD5, int numberOfThreads, int passwordLength, int server) throws RemoteException {
            Threading.startServer(hashedMD5, numberOfThreads, passwordLength, server);
    }

    public List<String> getPassword(){
        while (Threading.isFound != true){
            values = Threading.getPass();
        }

        return (values);
    }

    public int getTotalThreads(){
        return numOfThreads;
    }
    
}
