package Algorithms;

import lombok.Data;

import java.io.FileInputStream;

/**
 * Created by nerianeveem on 05/08/2016.
 */
@Data
public class ReversAlgorithm extends Algorithm{
    private Algorithm xorAlgorithm;
    public ReversAlgorithm(FileInputStream file, String filePath) {
        super(file, filePath);
        xorAlgorithm = new CaesarAlgorithm(file,filePath);
    }
    @Override
    public boolean decryption() {
        return xorAlgorithm.encryption();
    }

    @Override
    public boolean encryption() {
        return xorAlgorithm.decryption();
    }
}
