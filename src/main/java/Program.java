import Algorithms.*;
import Observer.EndObserver;
import Observer.FilesDecorator;
import Observer.StartObserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by nerianeveem on 18/05/2016.
 */
public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        int encrpOrDecrp = 1, algorithmChoice;
        String filePath;
        FileInputStream f;
        Scanner in = new Scanner(System.in);
        while (true) {
            final FilesDecorator al;
            System.out.println("Please choose between Encryption and Decryption: Encryption 1, Decryption 2, end 0");
            encrpOrDecrp = in.nextInt();
            while (encrpOrDecrp < 0 || encrpOrDecrp > 2) {
                System.out.println("illegal choice number");
                encrpOrDecrp = in.nextInt();
            }
            if (encrpOrDecrp == 0)
                break;
            System.out.println("Do you want to encrypt file or directory?\n file 1, directory 2");
            if (in.nextInt() == 1) {
                System.out.println("write the file path now:");
                filePath = in.next();
                File fil = new File(filePath);
                while (!fil.exists() || fil.isDirectory()) {
                    System.out.println("file not found, write the path again.");
                    filePath = in.next();
                    fil = new File(filePath);
                }
                f = new FileInputStream(filePath);

                System.out.println("File found");
                System.out.println("Please choose encryption algorithm: Caesar 1, XOR 2, MWO 3, Double 4, Revers 5,  Split 6");
                algorithmChoice = in.nextInt();
                switch (algorithmChoice) {
                    case 1:
                        al = new FilesDecorator(new CaesarAlgorithm(f, filePath));
                        break;
                    case 2:
                        al = new FilesDecorator(new XorAlgorithm(f, filePath));
                        break;
                    case 3:
                        al = new FilesDecorator(new MWOAlgorithm(f, filePath));
                        break;
                    case 4:
                        al = new FilesDecorator(new DoubleAlgorithm(f, filePath));
                        break;
                    case 5:
                        al = new FilesDecorator(new ReversAlgorithm(f, filePath));
                        break;
                    case 6:
                        al = new FilesDecorator(new SplitAlgorithm(f, filePath));
                        break;
                    default:
                        al = new FilesDecorator(new CaesarAlgorithm(f, filePath));
                }
                // create observers for start/end event
                new StartObserver(al);
                new EndObserver(al);
                boolean isPassed = false;
                try {
                    switch (encrpOrDecrp) {
                        case 1:
                            System.out.println("You want enter a key? y/n ");
                            if ((in.next()).equals("y")) {
                                System.out.println("Enter the key now: ");
                                ((Algorithm) (al.getItsFiles())).setKey(in.nextByte());
                                ((Algorithm) (al.getItsFiles())).setKeyChanged(true);
                            }
                            isPassed = al.encryption();
                            break;
                        case 2:
                            isPassed = al.decryption();
                            break;
                        default:
                            isPassed = false;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("enter your choice: ");

            } else {
                System.out.println("write the directory path now:");
                filePath = in.next();
                switch (encrpOrDecrp) {
                    case 1:
                        String finalFilePath = filePath;
                        Thread enc = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("thread");
                                File folder = new File(finalFilePath);
                                File[] listOfFiles = folder.listFiles();
                                for (int i = 0; i < listOfFiles.length; i++) {
                                    if (listOfFiles[i].isFile()) {
                                        try {
                                            FileInputStream fis = new FileInputStream(listOfFiles[i]);
                                            (new FilesDecorator(new XorAlgorithm(fis, listOfFiles[i].toString()))).encryption();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        });
                        enc.start();
                        break;
                    case 2:
                        String finalFilePath1 = filePath;
                        Thread dec = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                File folder1 = new File(finalFilePath1);
                                File[] listOfFiles1 = folder1.listFiles();
                                for (int i = 0; i < listOfFiles1.length; i++) {
                                    String pathty = listOfFiles1[i].getPath();
                                    pathty = pathty.substring(pathty.lastIndexOf('.'));
                                    if (listOfFiles1[i].isFile() && pathty.equals(".encrypted")) {
                                        try {
                                            FileInputStream fis = new FileInputStream(listOfFiles1[i]);
                                            (new FilesDecorator(new XorAlgorithm(fis, listOfFiles1[i].toString()))).decryption();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        });
                        dec.start();
                        break;
                }
            }

        }
    }
}