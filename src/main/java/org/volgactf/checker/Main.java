package org.volgactf.checker;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        DeploymentOptions deploymentOptions = new DeploymentOptions().setInstances(Runtime.getRuntime()
                .availableProcessors())
                .setWorkerPoolSize(Runtime.getRuntime().availableProcessors() * 10);
        Vertx.vertx().deployVerticle(CheckerVerticle::new, deploymentOptions);
    }
}
