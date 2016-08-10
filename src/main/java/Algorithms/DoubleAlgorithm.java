package Algorithms;

import lombok.Data;

import java.io.FileInputStream;
import java.io.Serializable;

/**
 * Created by nerianeveem on 05/08/2016.
 */
@Data
public class DoubleAlgorithm extends Algorithm {
    private Algorithm anotherCaesarAlgorithm;
    private Algorithm anotherXorAlgorithm;

    public DoubleAlgorithm(FileInputStream file, String filePath) {
        super(file, filePath);
        anotherCaesarAlgorithm = new CaesarAlgorithm(file, filePath);
        anotherXorAlgorithm = new CaesarAlgorithm(file, filePath);
        keys = new byte[2]; //save two keys for each algorithm
    }

    @Override
    public boolean decryption() {
        anotherXorAlgorithm.setKey(keys[0]);
        anotherXorAlgorithm.setKey(keys[1]);
        boolean passedCaesar, passedXor;
        passedXor = anotherXorAlgorithm.encryption();
        passedCaesar = anotherCaesarAlgorithm.encryption();
        if (passedCaesar && passedXor) //check if both algorithms encryption success
            return true;
        return false;
    }

    @Override
    public boolean encryption() {
        boolean passedCaesar, passedXor;
        passedCaesar = anotherCaesarAlgorithm.encryption();
        passedXor = anotherXorAlgorithm.encryption();
        if (passedCaesar && passedXor) {
            keys[0] = anotherCaesarAlgorithm.getKey();
            keys[1] = anotherXorAlgorithm.getKey();
            return true;
        }
        return false;
    }
}
