/**
 * Created by nerianeveem on 05/08/2016.
 */
public class EndObserver extends Observer{

    public EndObserver(FilesDecorator fd){
        this.ob = fd;
        this.ob.addObserver(this);
    }

    @Override
    public void update(String s) {
        if(s.contains("end"))
            System.out.println(s +  " observer");

    }
}
