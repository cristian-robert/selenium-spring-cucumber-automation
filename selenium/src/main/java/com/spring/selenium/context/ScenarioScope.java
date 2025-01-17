package com.spring.selenium.context;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

public class ScenarioScope implements Scope {

    private final Map<String, Object> scenarioBeans = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (!scenarioBeans.containsKey(name)) {
            scenarioBeans.put(name, objectFactory.getObject());
        }
        return scenarioBeans.get(name);
    }

    @Override
    public Object remove(String name) {
        return scenarioBeans.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    // ... Implement other methods as needed, possibly just throwing UnsupportedOperationException ...
}
