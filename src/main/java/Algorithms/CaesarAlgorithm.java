package Algorithms;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by nerianeveem on 24/05/2016.
 */
@Getter
@Setter
public class CaesarAlgorithm extends Algorithm {
    public CaesarAlgorithm(FileInputStream file, String filePath) {
        super(file, filePath);
    }

    @Override
    public boolean decryption() {
        System.out.println("start dec.");
        try { //input the encrypted file to input stream
            FileInputStream in = new FileInputStream(directoryPath + "\\" + fileName + ".encrypted");
            FileOutputStream out = new FileOutputStream(decryptionPath + fileName + "_decrypted" + fileExtension);
            byte b;
            while ((b = (byte) in.read()) != -1) {
                b = (byte) (b - key);    //caesar algorithm work on this way
                out.write(b);
            }
            System.out.println("encryption end");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean encryption() {
        System.out.println("start enc.");
        try {
            if (!keyChanged) { //generate a key if user not give a key
                Random rand = new Random();
                key = (byte) rand.nextInt(127);
            }
            FileInputStream in = new FileInputStream(filePath);
            FileOutputStream out = new FileOutputStream(encryptionPath + "\\" + fileName + ".encrypted");
            byte b;
            while ((b = (byte) in.read()) != -1) {
                b = (byte) (b + key);   //encrypt every byte
                out.write(b);
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}