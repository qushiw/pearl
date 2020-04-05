package web.designMode.adapter;

/**
 *
 * 适配器2
 * Created by jrqushiwen on 2017-10-10.
 */
public class AdaperTwo implements Target{

    private RiseTarget riseConcrete;


    public AdaperTwo(RiseTarget riseConcrete){
        this.riseConcrete = riseConcrete;
    }

    @Override
    public void request() {
        riseConcrete.riseRequest();
    }
}