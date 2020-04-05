package web.designMode.proxy.dynamic;

/**
 * Created by qsfs on 2017-10-19.
 */
public class ProxyImpl implements ProxyInterface {

    public String doSometing() {
        System.out.println("im really object");
        return "i am the result";
    }
}
