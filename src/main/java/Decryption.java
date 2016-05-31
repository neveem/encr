/**
 * Created by nerianeveem on 20/05/2016.
 */
public abstract class Decryption extends FileManipulation {
    public Decryption(String file) {
        super(file);
    }

    protected abstract void decryptionAlgorithm();

}
