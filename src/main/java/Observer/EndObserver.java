package Observer;

import Algorithms.Algorithm;

/**
 * Created by nerianeveem on 05/08/2016.
 */
public class EndObserver extends Observer{ //observe the end of encryption or decryption

    public EndObserver(FilesDecorator fd){
        this.ob = fd;
        this.ob.addObserver(this);
    }

    @Override
    public void update(String s) {
        if(s.contains("end")) {
            System.out.println(s + " observer");
            if(s.contains("encryption")){
                Algorithm al =  (Algorithm)(ob.getItsFiles());
                al.setFilePath(al.getDirectoryPath()+"\\encrypted\\"+al.getFileName()+".encrypted");
                al.setPaths();

            }
        }
    }
}
