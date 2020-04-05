package web.spring.aop;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class MainClient {

    @Resource(name = "targetClassProxy")
    private TargetClassInterFace targetClassProxy;

    @Test
    public void aopTest() {
        targetClassProxy.test();
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("asdasdadasdad".getBytes("unicode").length);
    }


}
