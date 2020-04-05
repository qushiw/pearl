package web.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class Abean implements BeanPostProcessor, ApplicationListener {

    private Bbean bbean;

    public void setBbean(Bbean bbean) {
        this.bbean = bbean;
    }

    public void a() {
        System.out.println("a:");
        bbean.b();
    }

    public Abean(){
        System.out.println("--------- i want to init ----------");
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return null;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return null;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
