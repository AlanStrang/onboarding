package com.example.helloworld.dependencies;

import com.example.helloworld.HelloWorldConfiguration;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

/**
 * Created by dev on 06/06/14.
 */
public class Dependencies {

    protected final MutablePicoContainer picoContainer = new DefaultPicoContainer(new Caching());

    /** set up required dependencies.  */
    public void initialise(HelloWorldConfiguration config) {
        /* 1st param is class, 2nd (optional) param is the instance with which we'll create our component */
        picoContainer.addComponent(HelloWorldResource.class, new HelloWorldResource(config.getTemplate(), config.getDefaultName()));
        picoContainer.addComponent(TemplateHealthCheck.class, new TemplateHealthCheck(config.getTemplate()));
    }

    /** generic method to get an instance of an Object */
    public <T> T get(Class<T> klass) {
        return picoContainer.getComponent(klass);
    }
}
