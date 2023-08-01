package com.conygre.spring;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * Created by nicktodd on 12/10/2016.
 */
@Component
@ManagedResource(objectName="com.citi.bean.jmx:name=MyJmxBean",
        description="Counts GET Requests")
public class JmxComponentImpl implements JmxComponent {

    private int numberOfGetRequests;

    @Override
    @ManagedAttribute
    public int getNumberOfGetRequests() {
        return numberOfGetRequests;
    }
    @Override
    public void setNumberOfGetRequests(int i) {
        numberOfGetRequests = i;
    }
    public void incrementNumberOfGetRequests() {
        numberOfGetRequests++;
    }
}