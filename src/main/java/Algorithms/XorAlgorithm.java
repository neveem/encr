package Algorithms;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by nerianeveem on 31/07/2016.
 */
@Getter
@Setter
public class XorAlgorithm extends Algorithm {

    public XorAlgorithm(FileInputStream file, String filePath) {
        super(file,filePath);
    }
    @Override
    public boolean decryption() {
        System.out.println("start dec.");
        try {
            FileInputStream in = new FileInputStream(fileName +".encrypted");
            FileOutputStream  out = new FileOutputStream(fileName + "_decrypted"+fileExtension);
            byte b;
            while((b = (byte)in.read()) != -1) {
                b = (byte)(b ^ key);
                out.write(b);
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
            while((b =(byte) in.read()) != -1) {
                b = (byte)(b ^ key);
                out.write(b);
            }
            out.close();
        }catch (Exception e){return false;}
        return true;
    }
}