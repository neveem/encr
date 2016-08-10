package Observer;

import Algorithms.*;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nerianeveem on 05/08/2016.
 */
@Getter
@Setter
public class FilesDecorator implements FileS { //decorate the algorithms with more functionality
    private FileS itsFiles;
    private List<Observer> observers = new ArrayList<Observer>();

    public FilesDecorator(FileS f) {
        itsFiles = f;
    }

    @Override
    public boolean encryption() {
        notifyAllObserversStart("encryption");  //update all the observers 'encryption start'
        long startTime = System.currentTimeMillis(); //save system time
        boolean result = itsFiles.encryption();          //encrypt the Algoritm object
        long endTime = System.currentTimeMillis();
        try {
            saveDetails();
        }  //save important details of the file for decryption
        catch (Exception e) {
            System.out.println("not save key! " + e.getMessage());
        }
        notifyAllObserversEnd("encryption"); //update all observers encyption end
        long duration = (endTime - startTime);     //calculate the time of encryption
        System.out.println("the duration is " + duration + " ms");
        ((Algorithm) itsFiles).setPaths();
        return result;
    }

    @Override
    public boolean decryption() {
        try {
            retrieveDetails();
        }   //get the detail of the encrypted file
        catch (Exception e) {
            System.out.println("not get file key! " + e.getMessage());
        }
        notifyAllObserversStart("decryption");
        long startTime = System.currentTimeMillis();
        boolean result = itsFiles.decryption();
        long endTime = System.currentTimeMillis();
        notifyAllObserversEnd("decryption");
        long duration = (endTime - startTime);
        System.out.println("the duration is " + duration + " ms");
        return result;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObserversStart(String s) {
        for (Observer observer : observers) {
            observer.update("start " + s);
        }
    }

    public void notifyAllObserversEnd(String s) {
        for (Observer observer : observers) {
            observer.update("end " + s);
        }
    }

    private void saveDetails() throws IOException {
        Algorithm theAlg = (Algorithm) itsFiles;
        FileOutputStream fs = new FileOutputStream(theAlg.getEncryptionPath() + theAlg.getFileName() + "key.bin");
        String fExnt = theAlg.getFileExtension();
        String fName = theAlg.getFileName();
        boolean isHaveTwoKeys = false;
        byte[] keys = null;
        byte key = theAlg.getKey();
        if (theAlg instanceof SplitAlgorithm || theAlg instanceof DoubleAlgorithm) {//save to keys if needed
            isHaveTwoKeys = true;
            keys = theAlg.getKeys();
        }
        EncryptedFileDetails fd = new EncryptedFileDetails(fExnt, isHaveTwoKeys, keys, key, fName);
        ObjectOutputStream obs = new ObjectOutputStream(fs);
        obs.writeObject(fd); //save the serialzable object to file
        obs.close();
        fs.close();


    }

    private void retrieveDetails() throws IOException, ClassNotFoundException {
        Algorithm theAlg = (Algorithm) itsFiles;
        FileInputStream fin = new FileInputStream(theAlg.getDirectoryPath() + "\\" + theAlg.getFileName() + "key.bin");
        ObjectInputStream obs = new ObjectInputStream(fin);
        EncryptedFileDetails a = (EncryptedFileDetails) obs.readObject();

        theAlg.setFileExtension(a.getFileExtension());
        theAlg.setFileName(a.getFileName());
        if (a.isTwoKeys())
            theAlg.setKeys(a.getKeys());
        else
            theAlg.setKey(a.getKey());
        theAlg.setKeyChanged(true);
        fin.close();
    }

}
