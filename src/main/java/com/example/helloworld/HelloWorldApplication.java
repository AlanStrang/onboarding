package com.example.helloworld;

import com.example.helloworld.dependencies.Dependencies;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.health.TemplateHealthCheck;

/** extends Application, with config passed in */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    private Dependencies dependencies;

    public HelloWorldApplication() {
        dependencies = new Dependencies();
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {

        /*call initialise method - can't do it in the constructor as we need this config parameter*/
        dependencies.initialise(configuration);

        final HelloWorldResource resource = dependencies.get(HelloWorldResource.class);

        /*initial initialisation - replaced with Dependency Injection. ooo.*/
//        final HelloWorldResource resource = new HelloWorldResource(
//                configuration.getTemplate(),
//                configuration.getDefaultName()
//        );

        final TemplateHealthCheck healthCheck = dependencies.get(TemplateHealthCheck.class);

        /*initial initialisation - replaced with Dependency Injection. ooo.*/
//        final TemplateHealthCheck healthCheck =
//                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}