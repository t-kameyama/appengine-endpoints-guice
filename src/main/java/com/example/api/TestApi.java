package com.example.api;

import javax.inject.Inject;

import com.example.ApiBase;
import com.example.model.TestModel;
import com.example.service.TestService;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiReference;

@ApiReference(ApiBase.class)
public class TestApi {

    private TestService testService;

    public TestApi() {}

    @Inject
    public TestApi(TestService testService) {
        this.testService = testService;
    }

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, path = "hello")
    public TestModel get() {
        return testService.createTestModel("Hello!!");
    }

}
