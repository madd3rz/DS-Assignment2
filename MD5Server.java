import java.rmi.*;

public class MD5Server {
    public static void main(String[] args) {
        try {
<<<<<<< HEAD
<<<<<<< Updated upstream
            System.setProperty("java.rmi.server.hostname", "192.168.1.108"); // change to your Server's ip address
            MD5Imple obj = new MD5Imple();
            Naming.rebind("rmi://192.168.1.108:5000/MD5Crack_Interface", obj); // change to your Server's ip address
=======
            System.setProperty("java.rmi.server.hostname", "192.168.1.100"); // change to your Server's ip address
            MD5Imple obj = new MD5Imple();
            Naming.rebind("rmi://192.168.1.100:5000/MD5Crack_Interface", obj); // change to your Server's ip address
>>>>>>> Stashed changes
=======
            System.setProperty("java.rmi.server.hostname", "192.168.0.161"); // change to your Server's ip address
            MD5Imple obj = new MD5Imple();
            Naming.rebind("rmi://192.168.0.161:5000/MD5Crack_Interface", obj); // change to your Server's ip address
>>>>>>> 52cbd02253bbd9abd7b4b969b14389954da2d346
            System.out.println("server ready");
        } catch (Exception e) {
            System.err.println("Server exception : " + e.toString());
            e.printStackTrace();
        }
    }
}
