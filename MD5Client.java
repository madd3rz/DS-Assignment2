import java.net.MalformedURLException;
import java.rmi.*;
import java.util.List;
import java.util.Scanner;

public class MD5Client {
    private MD5Client() {
    }

    // Variable declaration
    static int numOfThread = 0;
    static int numOfServers = 0;
    static String hashedMD5;
    static int passwordLength = 0;
    static int cores = 0;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        
        Scanner userInput = new Scanner(System.in);

        // User Interface
        System.out.print("\nHello and welcome to Group 4's Brute Force MD5 Password Search!");
        System.out.print("\nPlease enter the right information required on each input area.\n");

        System.out.print("\nThe following are the MD5 Hash Value for Group 4\n");
        System.out.print("=====================================================\n");
        System.out.print("2 character password:d39aeb060e81b0fc04267ae08255938a\n");
        System.out.print("3 character password:81cbf7f25e32573edb82d1b2f00c0df0\n");
        System.out.print("4 character password:859f5a735e8f047c768495d7ca2f2f0e\n");
        System.out.print("5 character password:cb6b4a74b8a77d34e1f5b694bf50e5ec\n");

        System.out.print("\nYou are required to enter the number of machines/server either 1 or 2.\n");
        System.out.print("\nYou are also required to enter the number of threads only ranging from 1 to 10.\n");
        System.out.print("You are also required to enter the number of character ranging from 2 to 5.\n");

        System.out.print("\nHashed MD5 : ");
        hashedMD5 = userInput.nextLine();

        System.out.print("The number of machine/server do you want use : ");
        numOfServers = userInput.nextInt();

        System.out.print("The total number of thread do you want use : ");
        numOfThread = userInput.nextInt();

        System.out.print("The number of character of password that you want to crack : ");
        passwordLength = userInput.nextInt();

        try {
            if (numOfServers == 1) {
                // change to your 1st server's ip address
                MD5Crack_Interface stub = (MD5Crack_Interface) Naming
                        .lookup("rmi://192.168.0.132:5000/MD5Crack_Interface");
                cores = stub.getTotalThreads();
                validation();
                stub.crackPassword(hashedMD5, numOfThread, passwordLength, 3);
                while (stub.isFound() == false) {
                    if (stub.isFound()) {
                        stub.stopAllThreads();
                        break;
                    }
                }
                List<String> values = stub.getPassword();
                if (!values.isEmpty()) {
                    System.out.print("Found password is " + values.get(0) + "\n");
                    System.out.print("Found threadID is " + values.get(1) + "\n");
                    System.out.print("Time is " + values.get(2));
                }
            } else if (numOfServers == 2) {
                // change to your 1st server's ip address
                MD5Crack_Interface stub = (MD5Crack_Interface) Naming
                        .lookup("rmi://192.168.0.132:5000/MD5Crack_Interface");
                cores = stub.getTotalThreads();
                // change to your 2nd Server's ip address
                MD5Crack_Interface stub2 = (MD5Crack_Interface) Naming
                        .lookup("rmi://192.168.0.167:5000/MD5Crack_Interface");
                cores += stub2.getTotalThreads();
                validation();
                stub.crackPassword(hashedMD5, numOfThread, passwordLength, 1);
                stub2.crackPassword(hashedMD5, numOfThread, passwordLength, 2);
                while (stub.isFound() == false || stub2.isFound() == false) {
                    if (stub.isFound()) {
                        stub2.stopAllThreads();
                        break;
                    } else if (stub2.isFound()) {
                        stub.stopAllThreads();
                        break;
                    }
                }
                List<String> values = stub.getPassword();
                List<String> values2 = stub2.getPassword();
                if (!values.isEmpty()) {
                    System.out.print("Found password is " + values.get(0) + "\n");
                    System.out.print("Found threadID is " + values.get(1) + "\n");
                    System.out.print("Time is " + values.get(2));
                } else {
                    System.out.print("Found password is " + values2.get(0) + "\n");
                    System.out.print("Found threadID is " + values2.get(1) + "\n");
                    System.out.print("Time is " + values2.get(2));
                }
            }

            System.out.println("\n ===end===");

        } catch (Exception e) {
            System.err.println("Client Exception : " + e.toString());
            e.printStackTrace();
        }

        // Close the scanner obj
        userInput.close();

    }

    private static void validation(){
        // Check for valid hashed md5 value 
        if (hashedMD5.length() != 32) {
            System.out.println("\nError! Please enter correct length of MD5 Hash value.");
            System.exit(0);
        }

        try {
            if (numOfServers < 1 || numOfServers > 2) {
                System.out.println("Max 2 servers only!");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("\nError! Please enter a valid number 1 or 2 only");
            System.exit(0);
        }
<<<<<<< HEAD
        // change to your server's ip address
        MD5Crack_Interface stub = (MD5Crack_Interface) Naming.lookup("rmi://192.168.1.100:5000/MD5Crack_Interface");
        cores = stub.getTotalThreads();

        
        // change to your Server's ip address
        MD5Crack_Interface stub2 = (MD5Crack_Interface) Naming.lookup("rmi://192.168.1.106:5000/MD5Crack_Interface"); 
        cores += stub2.getTotalThreads();

        System.out.print("The total number of thread do you want use : ");
        numOfThread = userInput.nextInt();
=======
>>>>>>> 52cbd02253bbd9abd7b4b969b14389954da2d346

        try {
            if (numOfThread < 1 || numOfThread > 10 && cores < numOfThread) { // check number of thread entered
                System.out.println("\nError! Please enter a valid number of threads (1-10).");
                System.out.println("Number of available threads on your machine is : " + cores);
                System.exit(0);
            }
            // to inform the users that their machine is not capable to execute
            else if (cores < numOfThread) {
                System.out.println("\nError, your machine could not excecute! Insufficient thread.");
                System.out.println("Available threads : " + cores);
                System.exit(0);
            }
        } catch (Exception ex) { // if input is not a number
            System.out.println("\nError! Please enter a valid number of threads (1-10)");
            System.exit(0);
        }

        try {
            if (passwordLength < 2 || passwordLength > 5) { // check the password length entered
                System.out.println("\nError! Please enter a character number from 2 to 5 only.");
                System.exit(0);
            }
        } catch (Exception ex) { // if input is not a number
            System.out.println("\nError! Please enter a valid number for password length (2-5)");
            System.exit(0);
        }

        
    }

}
