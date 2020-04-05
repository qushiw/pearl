package web.designMode.adapter;

/**
 * Created by jrqushiwen on 2017-10-10.
 */
public class ConcreteTarget implements Target{


    @Override
    public void request() {
        System.out.println("具体类");
    }
}
