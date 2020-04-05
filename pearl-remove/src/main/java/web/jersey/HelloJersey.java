package web.jersey;

import com.web.model.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jrqushiwen on 2017/9/15.
 */

@Path("jersey")
public class HelloJersey {

    @Autowired
    private Test test;


    @GET
    @Path("hello/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello(@PathParam("param") String param){

        test.getA();


        return "hello jersey" + param;
    }
}
