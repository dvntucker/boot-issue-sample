package hello;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by Devon on 2017-02-08.
 */
@Component
public class ServletAware implements ServletContextAware {

    public ServletAware() {
        System.out.println("Initing servlet aware");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("Setting servlet context to: " + servletContext);
    }
}
