package io.quarkiverse.custom.deployment;

import java.time.OffsetDateTime;

import io.quarkiverse.custom.deployment.items.EmptyCustomBuildItem;
import io.quarkiverse.custom.deployment.items.MockBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Consume;
import io.quarkus.deployment.annotations.Produce;

public class EmptyCustomProcessor {

    @BuildStep
    @Produce(EmptyCustomBuildItem.class)
    void before() {
        System.out.println("Running (before) at " + OffsetDateTime.now());
    }

    @BuildStep
    @Consume(EmptyCustomBuildItem.class)
    void after(BuildProducer<MockBuildItem> mocks) throws InterruptedException {
        System.out.println("[before Thread.sleep] Running (after) at " + OffsetDateTime.now());
        Thread.sleep(2000);
        System.out.println("[after Thread.sleep] Running (after) at " + OffsetDateTime.now());
    }
}
