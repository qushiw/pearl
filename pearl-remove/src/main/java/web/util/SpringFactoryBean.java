package web.util;

import com.web.model.ModelTest;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by jrqushiwen on 2017-09-25.
 */
public class SpringFactoryBean implements FactoryBean<ModelTest>{

    @Override
    public ModelTest getObject() throws Exception {
        return new ModelTest();
    }

    @Override
    public Class<?> getObjectType() {
        return ModelTest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
