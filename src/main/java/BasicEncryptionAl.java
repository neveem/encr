/**
 * Created by nerianeveem on 24/05/2016.
 */
public class BasicEncryptionAl extends Encryption implements Runnable {


    public BasicEncryptionAl(String file) {
        super(file);
    }

    protected void encryptionAlgorithm() {
        System.out.print("start enc.");
    }


    public void run() {
        encryptionAlgorithm();

    }
}
