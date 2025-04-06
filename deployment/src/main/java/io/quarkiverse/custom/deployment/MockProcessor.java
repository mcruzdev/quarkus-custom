package io.quarkiverse.custom.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Produce;
import io.quarkus.deployment.builditem.ApplicationIndexBuildItem;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;
import io.quarkus.deployment.pkg.builditem.ArtifactResultBuildItem;
import io.quarkus.runtime.LaunchMode;

public class MockProcessor {

    // 1. Executar apenas se for em modo de desenvolvimento

    @BuildStep
    @Produce(ArtifactResultBuildItem.class)
    void generateMock(LaunchModeBuildItem launchModeBuildItem, ApplicationIndexBuildItem applicationIndexBuildItem) {
        LaunchMode launchMode = launchModeBuildItem.getLaunchMode();
        System.out.println("Launch mode is " + launchMode.name());
        if (!launchModeBuildItem.getLaunchMode().isDevOrTest()) {
            return;
        }

    }
}
