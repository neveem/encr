package Algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by nerianeveem on 07/08/2016.
 */
public class SplitAlgorithm extends Algorithm {

    public SplitAlgorithm(FileInputStream file, String filePath) {
        super(file, filePath);
        keys = new byte[2];
    }

    @Override
    public boolean decryption() {
        System.out.println("start dec.");
        try {
            FileInputStream in = new FileInputStream(directoryPath + "\\" + fileName + ".encrypted");
            FileOutputStream out = new FileOutputStream(decryptionPath + fileName + "_decrypted" + fileExtension);
            byte b;
            int i = 0;
            while ((b = (byte) in.read()) != -1) {
                byte evenOrOdd = (byte) (i % 2);
                switch (evenOrOdd) {
                    case 1:
                        b = (byte) (b ^ keys[0]);
                        out.write(b);
                        ++i;
                        break;
                    case 0:
                        b = (byte) (b - keys[1]);
                        out.write(b);
                        ++i;
                        break;
                }
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
            if (!keyChanged) {
                Random rand = new Random();
                keys[0] = (byte) rand.nextInt(127);
                keys[1] = (byte) rand.nextInt(127);
            }
            FileInputStream in = new FileInputStream(filePath);
            FileOutputStream out = new FileOutputStream(encryptionPath + "\\" + fileName + ".encrypted");
            byte b;
            int i = 0;
            while ((b = (byte) in.read()) != -1) {
                byte evenOrOdd = (byte) (i % 2);
                switch (evenOrOdd) {
                    case 1:
                        b = (byte) (b ^ keys[0]);
                        out.write(b);
                        ++i;
                        break;
                    case 0:
                        b = (byte) (b + keys[1]);
                        out.write(b);
                        ++i;
                        break;
                }
            }
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
