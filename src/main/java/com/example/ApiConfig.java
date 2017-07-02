package com.example;

import java.util.Collections;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.google.api.server.spi.guice.EndpointsModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApiConfig {

    public static class ApiModule extends EndpointsModule {
        @Override
        protected void configureServlets() {
            bind(TestApi.class).asEagerSingleton();
            configureEndpoints("/_ah/api/*", Collections.singletonList(TestApi.class));
            super.configureServlets();
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

    // for devserver
    @WebServlet(name = "empty", value = "/_ah/api/*")
    public static class EmptyServlet extends HttpServlet {}

}
