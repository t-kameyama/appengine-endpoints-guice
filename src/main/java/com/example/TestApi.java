package com.example;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name = "test", version = "v1")
public class TestApi {

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, path = "hello")
    public TestModel get() {
        return new TestModel("Hello");
    }

    public static class TestModel {
        private final String message;
        public TestModel(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

}
