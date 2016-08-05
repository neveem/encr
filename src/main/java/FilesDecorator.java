import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nerianeveem on 05/08/2016.
 */
@Getter
@Setter
public class FilesDecorator implements Files{
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
        return result;
    }

    @Override
    public boolean decryption() {
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

}
