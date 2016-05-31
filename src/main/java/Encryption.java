/**
 * Created by nerianeveem on 20/05/2016.
 */
public abstract class Encryption extends FileManipulation {


    public Encryption(String file) {
        super(file);
    }
    protected abstract void encryptionAlgorithm();

}
