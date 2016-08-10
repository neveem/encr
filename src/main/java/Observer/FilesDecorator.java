package Observer;

import Algorithms.Algorithm;
import Algorithms.Files;
import Observer.Observer;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nerianeveem on 05/08/2016.
 */
@Getter
@Setter
public class FilesDecorator implements Files {
    private Files itsFiles;
    private List<Observer> observers = new ArrayList<Observer>();

    public FilesDecorator (Files f){
        itsFiles=f;
    }

    @Override
    public boolean encryption() {
        notifyAllObserversStart("encryption");
        long startTime = System.currentTimeMillis();
        boolean result= itsFiles.encryption();
        long endTime = System.currentTimeMillis();
        notifyAllObserversEnd("encryption");
        long duration = (endTime - startTime);
        System.out.println("the duration is " + duration + " ms");
        try{ saveKey();}
        catch (Exception e){System.out.println("not save key! "+ e.getMessage());}
        return result;
    }

    @Override
    public boolean decryption() {
        try{((Algorithm)itsFiles).setKey(retrieveKey());}
        catch (Exception e){System.out.println("not save key! "+ e.getMessage());}
        notifyAllObserversStart("decryption");
        long startTime = System.currentTimeMillis();
        boolean result= itsFiles.decryption();
        long endTime = System.currentTimeMillis();
        notifyAllObserversEnd("decryption");
        long duration = (endTime - startTime);
        System.out.println("the duration is " + duration + " ms");
        return result;
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserversStart(String s){
        for (Observer observer : observers) {
            observer.update("start " + s);
        }
    }

    public void notifyAllObserversEnd(String s){
        for (Observer observer : observers) {
            observer.update("end " + s);
        }
    }

    private void saveKey() throws IOException {
        FileOutputStream fs = new FileOutputStream("key.bin");
        fs.write(((Algorithm)itsFiles).getKey());
        fs.close();
    }
    private byte retrieveKey()  throws IOException {
        FileInputStream fin = new FileInputStream("key.bin");
        byte b=(byte)fin.read();
        fin.close();
        return  b;
    }

}
