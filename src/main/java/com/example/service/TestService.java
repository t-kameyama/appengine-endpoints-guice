package com.example.service;

import javax.inject.Singleton;

import com.example.model.TestModel;

@Singleton
public class TestService {

    public TestModel createTestModel(String message) {
        return new TestModel(message);
    }

}
