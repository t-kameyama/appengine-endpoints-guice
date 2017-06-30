package com.example;

import java.util.Collections;

import com.google.api.server.spi.guice.EndpointsModule;

public class TestModule extends EndpointsModule {
   @Override
    protected void configureServlets() {
        bind(TestApi.class).asEagerSingleton();
        configureEndpoints("/_ah/api/*", Collections.singletonList(TestApi.class));
        super.configureServlets();
    }
}
