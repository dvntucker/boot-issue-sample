package hello;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by devon on Feb/8/2017.
 */
public class ContainerInitializingWebContext extends EmbeddedWebApplicationContext implements ConfigurableApplicationContext {

    private final EmbeddedServletContainer embeddedServletContainer;

    public ContainerInitializingWebContext() {
        EmbeddedServletContainerFactory containerFactory = new TomcatEmbeddedServletContainerFactory();
        this.embeddedServletContainer = containerFactory.getEmbeddedServletContainer(getSelfInitializer());
    }

    /**
     * Returns the {@link ServletContextInitializer} that will be used to complete the
     * setup of this {@link WebApplicationContext}.
     * @return the self initializer
     * @see #prepareEmbeddedWebApplicationContext(ServletContext)
     */
    private org.springframework.boot.web.servlet.ServletContextInitializer getSelfInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                selfInitialize(servletContext);
            }
        };
    }

    private void selfInitialize(ServletContext servletContext) throws ServletException {
        prepareEmbeddedWebApplicationContext(servletContext);
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        ExistingWebApplicationScopes existingScopes = new ExistingWebApplicationScopes(
            beanFactory);
        WebApplicationContextUtils.registerWebApplicationScopes(beanFactory,
            getServletContext());
        existingScopes.restore();
        WebApplicationContextUtils.registerEnvironmentBeans(beanFactory,
            getServletContext());
        for (ServletContextInitializer beans : getServletContextInitializerBeans()) {
            beans.onStartup(servletContext);
        }
    }

    @Override
    protected void onRefresh() {
        initPropertySources();
    }
}
