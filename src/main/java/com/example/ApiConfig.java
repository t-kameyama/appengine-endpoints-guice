package com.example;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.google.api.server.spi.config.ApiReference;
import com.google.api.server.spi.guice.EndpointsModule;
import com.google.common.reflect.ClassPath;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApiConfig {

    private static final String API_PACKAGE = "com.example.api";
    private static final Logger log = Logger.getLogger(ApiConfig.class.getSimpleName());

    public static class ApiModule extends EndpointsModule {
        @Override
        protected void configureServlets() {
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                List<? extends Class<?>> apiClasses =
                        ClassPath.from(classLoader).getTopLevelClassesRecursive(API_PACKAGE)
                                .stream()
                                .map(ClassPath.ClassInfo::load)
                                .filter(c -> c.isAnnotationPresent(ApiReference.class))
                                .map(c -> {
                                    bind(c).asEagerSingleton();
                                    return c;
                                })
                                .collect(Collectors.toList());
                configureEndpoints("/_ah/api/*", apiClasses);
                super.configureServlets();
            } catch (IOException e) {
                log.severe("Failed to configure servlets");
            }
        }
    }

    @WebListener
    public static class ApiServletContextListener extends GuiceServletContextListener {
        @Override
        protected Injector getInjector() {
            return Guice.createInjector(new ApiModule());
        }
    }

    @WebFilter(filterName = "api", urlPatterns = "/_ah/api/*")
    public static class ApiFilter extends GuiceFilter {
    }

    // Required when starting with mvn appengine:run
    @WebServlet(name = "empty", value = "/_ah/api/*")
    public static class EmptyServlet extends HttpServlet {
    }

}
