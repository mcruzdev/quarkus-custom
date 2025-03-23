package io.quarkiverse.custom.deployment;

import java.util.Optional;

import io.quarkiverse.custom.deployment.items.ErrorBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Produce;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.pkg.builditem.ArtifactResultBuildItem;

class CustomProcessor {

    private static final String FEATURE = "custom";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    void producesErrorBuildItemUsingBuildProducer(BuildProducer<ErrorBuildItem> errors) {
        errors.produce(new ErrorBuildItem("Error from quarkus-custom using BuildProducer"));
    }

    @BuildStep
    @Produce(ArtifactResultBuildItem.class)
    void consumeErrorBuildItem(ErrorBuildItem error) {
        System.out.println("Error message from ErrorBuildItem: " + error.getMessage());
    }

    @BuildStep
    @Produce(ArtifactResultBuildItem.class)
    void consumeErrorBuildItemAsOptional(Optional<ErrorBuildItem> optionalError) {
        optionalError.ifPresent(errorBuildItem -> {
            System.out.println("Optional error: " + errorBuildItem.getMessage());
        });
    }
}
