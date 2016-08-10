package Algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by nerianeveem on 07/08/2016.
 */
public class SplitAlgorithm extends Algorithm {
    protected byte[] keys;

    public SplitAlgorithm(FileInputStream file, String filePath) {
        super(file, filePath);
        keys = new  byte[2];
    }
    @Override
    public boolean decryption() {
        System.out.println("start dec.");
        try {
            FileInputStream in = new FileInputStream(fileName +".encrypted");
            FileOutputStream out = new FileOutputStream(fileName + "_decrypted"+fileExtension);
            byte b;
            int i=0;
            while((b = (byte)in.read()) != -1) {
                byte evenOrOdd = (byte)(i%2);
                switch (evenOrOdd){
                    case 1:
                        b = (byte)(b ^ keys[evenOrOdd]);
                        out.write(b);
                        break;
                    case 0:
                        b = (byte)(b - key);
                        out.write(b);
                        break;
                }
            }
            System.out.println("encryption end");
        }catch (Exception e){return false;}

        return true;
    }
    // C:\Users\nerianeveem\IdeaProjects\encryptor\a.txt
    @Override
    public boolean encryption() {
        System.out.println("start enc.");
        try {
            if(!keyChanged) {
                Random rand = new Random();
                key = (byte) rand.nextInt(127);
            }
            FileInputStream in = new FileInputStream(filePath);
            FileOutputStream  out = new FileOutputStream(fileName +".encrypted");
            byte b;
            int i=0;
            while((b = (byte)in.read()) != -1) {
                byte evenOrOdd = (byte)(i%2);
                switch (evenOrOdd){
                    case 1:
                        b = (byte)(b ^ keys[evenOrOdd]);
                        out.write(b);
                        break;
                    case 0:
                        b = (byte)(b + key);
                        out.write(b);
                        break;
                }
            }
            out.close();
        }catch (Exception e){return false;}
        return true;
    }}
