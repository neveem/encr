package Algorithms;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by nerianeveem on 01/08/2016.
 */
@Getter
@Setter
public class MWOAlgorithm extends Algorithm {


    public MWOAlgorithm(FileInputStream file, String filePath) {
        super(file,filePath);
    }
    @Override
    public boolean decryption() {
        System.out.println("start dec.");
        try {
            byte decKey;
            for(decKey=Byte.MIN_VALUE; decKey!=Byte.MAX_VALUE; decKey++){
                if((byte)(decKey*key) == 1)
                    break;
            }

            FileInputStream in = new FileInputStream(fileName +".encrypted");
            FileOutputStream  out = new FileOutputStream(fileName + "_decrypted"+fileExtension);
            byte b;
            while((b = (byte)in.read()) != -1) {
                b = (byte)(b * decKey);
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
            if(key%2 == 0 || key == 0)
                throw  new Exception("illegal key!");
            FileInputStream in = new FileInputStream(filePath);
            FileOutputStream  out = new FileOutputStream(fileName +".encrypted");
            byte b;
            while((b =(byte) in.read()) != -1) {
                b = (byte)(b * key);
                out.write(b);
            }
            out.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


}
