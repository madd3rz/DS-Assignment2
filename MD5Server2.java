
// import java.rmi.registry.*;
import java.rmi.*;

public class MD5Server2 {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "192.168.43.3"); // change to your Server's ip address
            MD5Imple obj = new MD5Imple();
            Naming.rebind("rmi://192.168.43.3:5000/MD5Crack_Interface", obj); // change to your Server's ip address
            System.out.println("server ready");
        } catch (Exception e) {
            System.err.println("Server exception : " + e.toString());
            e.printStackTrace();
        }
    }
}
