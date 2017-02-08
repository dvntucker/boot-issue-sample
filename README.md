This is a small sample file that recreates an issue seen with Spring Boot and ServletContext aware beans.

The basic issue is that if a ServletContextAware bean is instantiated as part of a BeanPostProcessor initialization,
setServletContext never gets called. Spring machinery looks to call it, but at the time servletContext is null so it
doesn't get set.