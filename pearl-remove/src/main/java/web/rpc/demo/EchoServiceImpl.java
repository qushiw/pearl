package web.rpc.demo;

/**
 * Created by qsfs on 2017-10-19.
 */
public class EchoServiceImpl implements EchoService{

    @Override
    public String echo(String ping) {
        return "i am ok";
    }
}
