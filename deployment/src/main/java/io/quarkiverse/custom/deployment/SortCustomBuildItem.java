package io.quarkiverse.custom.deployment;

import java.time.OffsetDateTime;

import io.quarkiverse.custom.deployment.items.CheckpointBuildItem;
import io.quarkiverse.custom.deployment.items.MockBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Consume;
import io.quarkus.deployment.annotations.Produce;

public class SortCustomBuildItem {

    @BuildStep
    @Produce(CheckpointBuildItem.class)
    void start() throws InterruptedException {
        System.out.println("Running start at " + OffsetDateTime.now());
        Thread.sleep(2000);
    }

    @Consume(CheckpointBuildItem.class)
    @BuildStep
    void finish(BuildProducer<MockBuildItem> mocks) {
        System.out.println("Running finish at " + OffsetDateTime.now());
    }

    @Consume(CheckpointBuildItem.class)
    @BuildStep
    void finishFinish(BuildProducer<MockBuildItem> mocks) {
        System.out.println("Running finishFinish at " + OffsetDateTime.now());
    }
}
