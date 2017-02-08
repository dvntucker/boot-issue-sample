package hello;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by Devon on 2017-02-08.
 */
@Component
public class ServletAware2 implements ServletContextAware {

    /**
     * I do get called because I get instantiated during normal bean creation
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("Setting servlet context in ServletAware2");
    }
}
