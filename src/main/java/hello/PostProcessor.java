package hello;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Devon on 2017-02-08.
 */
@Component
public class PostProcessor implements BeanPostProcessor {

    @Autowired
    ServletAware servletAware;

    public PostProcessor() {
        System.out.println("Initing post processor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("Post processing before");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("Post processing after");
        return o;
    }
}
