import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PasswordCracker {
    // Declaring all the global variables
    public static int UID;
    public static String hashtext;
    public static String Concat1;
    public static String password;
    public static String salt;
    public static String Temp;
    public static String[] Pass = new String[100];
    public static String[] Salt = new String[100];
    public static String[] Hash = new String[100];
    public static String line;
    

    // Main method asks users for UID input and performs the tasks
    public static void main(String[] args) {
        System.out.println("What is the UID?");//gets UID
        Scanner ask = new Scanner(System.in);
        UID = Integer.parseInt(ask.nextLine());
        while(UID > 100){//makes sure the user input is valid
            System.out.println("Input number 1-100");
            Scanner ask1 = new Scanner(System.in);
            UID = Integer.parseInt(ask1.nextLine());
        }

        Fill("D://Dropbox/Education/College/Projects/Password/Password.txt", Pass);
        Fill("D://Dropbox/Education/College/Projects/Password/Salt.txt", Salt);
        Fill("D://Dropbox/Education/College/Projects/Password/UID.txt", Hash);//filling in array for comparing values
       
        Check("D://Dropbox/Education/College/Projects/Password/UID.txt", Pass, Salt);
        
        read(UID, "D://Dropbox/Education/College/Projects/Password/UID.txt");//reads UID file
        read(UID, "D://Dropbox/Education/College/Projects/Password/Password.txt");//reads password file
        password = line;
        read(UID, "D://Dropbox/Education/College/Projects/Password/Salt.txt");//reads salt file
        salt = line;
        
        Con(password, salt);
        String s = Concat1;//creates the concatenated string for hash function
        hashtext = getMd5(s);//gets hash value
        System.out.println("Hash value: " + hashtext);
    }

     // method that takes the input and creates MD5 hash
     public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // throws exception when an invalid input is given
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // this method fills specified array with specified file text
    public static void Fill(String s, String[] array) {
        try {
            //read file.txt
            FileReader file0 = new FileReader(s);
            BufferedReader buffer = new BufferedReader(file0);

            // iterate through the file and retrieve each line and store into array
            for (int i = 1; i < 101; i++) {
                line = buffer.readLine();
                array[i-1] = line;
            }
            // Error catching
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method concatenates the password sting and salt string to be used for MD5 hashing
    public static void Con(String password, String salt){
        Concat1 = password + salt;
    }

    // This mehtod reads the password and the salt to be concatenated for MD5 hashing
    public static void read(int UID, String s) {
        try {
            FileReader file0 = new FileReader(s);
            BufferedReader buffer = new BufferedReader(file0);

            // iterate through the file 
            for (int i = 1; i < 100; i++) {
                if (i == UID)
                    line = buffer.readLine();
                else
                    buffer.readLine();
            }
            // Error catching
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method takes the 
    //take UID salt + UID password and check hash function and return if it matches UID hash
    public static void Check(String s, String[] Pass, String[] Salt){

        String line = "";
        int Counter = 0;
        try {
            //read file.txt
            FileReader file0 = new FileReader(s);
            BufferedReader buffer = new BufferedReader(file0);

            // This nested for loop iterates through the file to check each combination of password and salt 
            // until it finds the matching hash from the hash file that corresponds to the given UID from the user input
            for (int i = 1; i < 101; i++) {
                for(int a = 0; a < 100; a++){
                    Temp = Pass[i-1] + Salt[a];
                    getMd5(Temp);
                    Integer number = UID;
                    Counter = Counter + 1;

                    // Print statement to eep track of the number combinations checked and each line number and salt is being processed
                    System.out.println("Cracking Combination Number: " + Counter);
                    System.out.println("Line Number: " + i);
                    System.out.println("Salt Number: " + Salt[a]);
                    
                    // If statement to match the hash values and print out the correct hash
                    if(getMd5(Temp).equals(Hash[number-1])){
                        System.out.println("----------------------------------------------");
                        System.out.println("UID Number: " + UID);
                        System.out.println("Salt Number: " + Salt[i-1]);
                        System.out.println("Password Number: " + Pass[i-1]);
                        return;//breaks the loop
                    }
                }
            }
            // Prints the matched hash
            System.out.println(line);
        //Error catching
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}