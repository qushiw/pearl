package web.designMode.decorator;

/**
*
* @author qushiwen
* @create 2018-02-21
* @version 1.0
**/
public class ConcreteDecortor extends Decorator {

    public ConcreteDecortor(Component component) {
        super(component);
    }

    @Override
    public void operator() {
        System.out.println("i am decorator");
        super.operator();
    }
}
