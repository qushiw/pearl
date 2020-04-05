package web.designMode.decorator;

/**
*
* @author qushiwen
* @create 2018-02-21
* @version 1.0
**/
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decortor = new ConcreteDecortor(component);
        decortor.operator();
    }


}
