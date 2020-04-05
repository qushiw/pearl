package com.web.controller;

import com.web.model.ModelTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jrqushiwen on 2017/9/10.
 */

@Controller
@RequestMapping("/springMVC/test")
public class DruidController {

    private ModelTest modelTest;

/*    @Autowired
    private DruidDataSource druidDataSource;*/

    @RequestMapping("/db.do")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("webSocketDemo");
        try {
//            System.out.println(modelTest);
//            Connection connection = druidDataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select name from test");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("name"));
//            }
//            Properties properties = System.getProperties();
//            System.out.println(properties.getProperty("maxWait"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }


    public void setModelTest(ModelTest modelTest) {
        this.modelTest = modelTest;
    }
}
