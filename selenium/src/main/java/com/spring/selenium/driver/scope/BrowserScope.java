package com.spring.selenium.driver.scope;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Objects;

/**
 * This scope is a custom browser scope handling WebDriver objects
 * for managing WebDriver instances for each thread.
 *
 * @author cristian_iosef
 */
public class BrowserScope extends SimpleThreadScope {

    /**
     * Returns an instance from the scope.
     *
     * @param name          the name of the object to retrieve
     * @param driverFactory the factory to create the object if not found in the scope
     * @return the instance from the scope
     */
    @Override
    public Object get(String name, ObjectFactory<?> driverFactory) {
        Object object = super.get(name, driverFactory);

        if (object instanceof RemoteWebDriver) {
            RemoteWebDriver remoteWebDriverObject = (RemoteWebDriver) object;
            SessionId sessionId = remoteWebDriverObject.getSessionId();

            if (Objects.isNull(sessionId)) {
                super.remove(name);
                remoteWebDriverObject = (RemoteWebDriver) super.get(name, driverFactory);
            }
            return remoteWebDriverObject;
        } else {
            return object;
        }
    }

    /**
     * No-op implementation for the registerDestructionCallback.
     */
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // No destruction callback is needed
    }
}
