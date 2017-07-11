package com.example;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiReference;

@ApiReference(ApiBase.class)
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
