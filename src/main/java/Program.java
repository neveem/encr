import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by nerianeveem on 18/05/2016.
 */
public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        int encrpOrDecrp, algorithmChoice;
        //Algorithm al ;
        FilesDecorator al;
        Algorithm tmp;
        String filePath;
        FileInputStream f;
        Scanner in= new Scanner(System.in);
        Thread task;


        System.out.println("Please choose encryption algorithm: " +
                "\nfor Caesar press 1, for XOR press 2\"\n" +
                "for MWO press 3");
        algorithmChoice=in.nextInt();

        System.out.println("Please choose between Encryption and Decryption: " +
                               "\nfor Encryption press 1, for Decryption press 2\"\n" +
                            "to end the program press 0");
        encrpOrDecrp = in.nextInt();
        while (encrpOrDecrp<1 || encrpOrDecrp>2){
            System.out.println("illegal choice number, please choose 1 or 2 only! ");
            encrpOrDecrp = in.nextInt();
        }

        System.out.println("write the file path now:");
        filePath= in.next();
        f = new FileInputStream(filePath);
        while (f == null/*.exists()*/){  // !f.isDirectory())
            System.out.println("file not found, write the path again.");
            filePath= in.next();
            f = new FileInputStream(filePath);
        }
        switch (encrpOrDecrp) {
            case 1:
                al = new FilesDecorator(new CaesarAlgorithm(f, filePath));
                break;
            case 2:
                al = new FilesDecorator( new XorAlgorithm(f, filePath));
                break;
            case 3:
                al = new FilesDecorator( new MWOAlgorithm(f, filePath));
                break;
            default:
                al = new FilesDecorator(new CaesarAlgorithm(f, filePath));
        }

        new StartObserver(al);
        new EndObserver(al);
        boolean isPassed;
        System.out.println("File found");

        while(encrpOrDecrp!=0) {
            try {
                switch (encrpOrDecrp) {
                    case 1:
                        System.out.println("You want enter a key? y/n ");
                        if((in.next()).equals("y")){
                            System.out.println("Enter the key now: ");
                            ((Algorithm)(al.getItsFiles())).setKey(in.nextByte());
                            ((Algorithm)(al.getItsFiles())).setKeyChanged(true);
                        }
                        isPassed = al.encryption();
                        break;
                    case 2:
                        isPassed = al.decryption();
                        break;
                    default:
                        isPassed = false;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("enter your choice: ");
            encrpOrDecrp = in.nextInt();
        }
    }
}
