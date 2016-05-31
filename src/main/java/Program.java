import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by nerianeveem on 18/05/2016.
 */
public class Program {


    public static void main(String[] args) throws FileNotFoundException {
        int choice;
        FileManipulation fileMan ;//= new BasicEncryptionAl("j");
        String filePath;
        FileInputStream f;
        Scanner in= new Scanner(System.in);;
        Thread task;

        System.out.println("Please choose between Encryption and Decryption: " +
                               "\nfor Encryption press 1, for Decryption press 2");
        choice = in.nextInt();
        while (choice<1 || choice>2){
            System.out.println("illegal choice number, please choose 1 or 2 only! ");
            choice = in.nextInt();
        }

        System.out.println("write the file path now:");
      //  File f = new File(in.next());
        filePath= in.next();
        f = new FileInputStream(filePath);
        while (f == null/*.exists()*/){  // !f.isDirectory())
            System.out.println("file not found, write the path again.");
            filePath= in.next();
            f = new FileInputStream(filePath);
        }

        switch (choice){
            case 1:
                System.out.println("File found, start encrypt the file!");
                // start encryption function
                fileMan = new BasicEncryptionAl(filePath);
                (new Thread((Runnable) fileMan)).start();
             //   task.start();
                break;
            case 2:
                System.out.println("File found, start decrypt the file!");
                // start decryption function
                // decrypt(filePath);
                break;
            default:
                System.out.println("unexpected error");
        }




    }



}
