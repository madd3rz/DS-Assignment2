
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Group 4 Aima Aimaduddin Bin Bollhasan (65332) Alexander Anak Harris (64613) Milton Bruen Ak Pali (66645) Lau Ngie Hing (66424) Woo Zhenghan (68097) 
public class MD5Thread extends Thread {

    // Variable declaration
    String hashedMD5;
    String interval;
    static int cmp = 0;
    int threadId = 0;
    static boolean isFound = false;
    int passwordLength;
    static String foundPassword;

    // Default constructor
    public MD5Thread(String i, String hash, int passLength) {
        interval = i;
        threadId = cmp;
        hashedMD5 = hash;
        cmp++;
        passwordLength = passLength;
    }

    @Override
    public void run() {
        int a;
        int b;

        // this method takes an interval and start brute force search on it
        String[] A = interval.split("-");
        try {
            // take the interval number. for ex: 33-43
            a = Integer.parseInt(A[0]); // ex : 33
            b = Integer.parseInt(A[1]); // ex : 43
            System.out.println(a);
            System.out.println(b);
        } catch (ArrayIndexOutOfBoundsException e) {
            // for numOfThread == 1 only
            a = 33;
            b = 126;
        }

        //check if the length of password is correct within the range from 2 to 5 character password
        double timesStart = System.currentTimeMillis();
        switch (passwordLength) {
        case 2:
            TwoCharPassword(a, b, timesStart);
            break;
        case 3:
            ThreeCharPassword(a, b, timesStart);
            break;
        case 4:
            FourCharPassword(a, b, timesStart);
            break;
        case 5:
            FiveCharPassword(a, b, timesStart);
            break;
        default:
            System.out.println("You entered invalid length of password.");
            break;
        }
    }

    public static String getMD5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // for specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void passwordChecking(String password, double timesStart) {
        if (hashedMD5.equals(getMD5(password))) {
            foundPassword = password;
            System.out.println("\n--> Result         : The password is " + password + " found at thread number " + threadId);
            double timesEnd = System.currentTimeMillis();
            double totalTime = (timesEnd - timesStart) / 1000 / 60;
            System.out.printf("--> Time elapsed   : %.5f minutes \n\n", totalTime);
            isFound = true;
            Threading.sendBack(password, threadId, totalTime);
            Threading.stop_threads();
        }
    }

    private void TwoCharPassword(int a, int b, double timesStart) {
        // making search on the first character from a to b
        for (int i = a; i < b; i++) {
            if(isFound) break;
            char c = (char) i;
            for (int n = 33; n < 127; n++) {
                char v = (char) n;
                String word = c + "" + v;
                passwordChecking(word, timesStart);
            }
        }
        if(isFound == false){
            System.out.println("Thread " + threadId + " - Password is not found.");
        }

    }

    private void ThreeCharPassword(int a, int b, double timesStart) {
        // making search on the first character from a to b
        for (int i = a; i < b; i++) {
            if(isFound) break;
            char c = (char) i;
            for (int m = 33; m < 127; m++) {
                if(isFound) break;
                char x = (char) m;
                for (int n = 33; n < 127; n++) {
                    char v = (char) n;
                    String word = c + "" + x + "" + v;
                    passwordChecking(word, timesStart);
                }
            }
        }

        if(isFound == false){
            System.out.println("Thread " + threadId + " - Password is not found.");
        }

    }

    private void FourCharPassword(int a, int b, double timesStart) {
        // making search on the first character from a to b
        for (int i = a; i < b; i++) {
            if(isFound) break;
            char c = (char) i;
            for (int l = 33; l < 127; l++) {
                if(isFound) break;
                char r = (char) l;
                for (int m = 33; m < 127; m++) {
                    if(isFound) break;
                    char x = (char) m;
                    for (int n = 33; n < 127; n++) {
                        char v = (char) n;
                        String word = c + "" + r + "" + x + "" + v;
                        passwordChecking(word, timesStart);
                    }
                }
            }
        }

        if(isFound == false){
            System.out.println("Thread " + threadId + " - Password is not found.");
        }

    }

    private void FiveCharPassword(int a, int b, double timesStart) {
        // making search on the first character from a to b
        for (int i = a; i < b; i++) {
            if(isFound) break;
            char c = (char) i;
            for (int k = 33; k < 127; k++) {
                if(isFound) break;
                char e = (char) k;
                for (int l = 33; l < 127; l++) {
                    if(isFound) break;
                    char r = (char) l;
                    for (int m = 33; m < 127; m++) {
                        if(isFound) break;
                        char x = (char) m;
                        for (int n = 33; n < 127; n++) {
                            char v = (char) n;
                            String word = c + "" + e + "" + r + "" + x + "" + v;
                            passwordChecking(word, timesStart);
                        }
                    }
                }
            }
        }

        if(isFound == false){
            System.out.println("Thread " + threadId + " - Password is not found.");
        }

    }

}
