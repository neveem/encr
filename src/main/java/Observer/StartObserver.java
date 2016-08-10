package Observer;

/**
 * Created by nerianeveem on 04/08/2016.
 */
public class StartObserver extends Observer { //observe start and end of encryption or decrption

    public StartObserver(FilesDecorator fd) {
        this.ob = fd;
        this.ob.addObserver(this);
    }

    @Override
    public void update(String s) {
        if (s.contains("start"))
            System.out.println(s + " observer");

    }
}
