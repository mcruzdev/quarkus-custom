package io.quarkiverse.custom.deployment;

import java.util.List;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;

import io.quarkiverse.custom.deployment.items.MockBuildItem;
import io.quarkiverse.custom.runtime.GETMock;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.ApplicationIndexBuildItem;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;
import io.quarkus.runtime.LaunchMode;

public class MockProcessor {

    // 1. Executar apenas se for em modo de desenvolvimento

    @BuildStep
    void generateMock(LaunchModeBuildItem launchModeBuildItem, ApplicationIndexBuildItem applicationIndexBuildItem,
            BuildProducer<MockBuildItem> mocks) {
        LaunchMode launchMode = launchModeBuildItem.getLaunchMode();
        System.out.println("Launch mode is " + launchMode.name());
        if (!launchModeBuildItem.getLaunchMode().isDevOrTest()) {
            return;
        }

        Index index = applicationIndexBuildItem.getIndex();

        List<AnnotationInstance> annotations = index.getAnnotations(DotName.createSimple(GETMock.class));
        for (AnnotationInstance annotation : annotations) {
            String path = annotation.value("path").asString();
            String responseBody = annotation.value("responseBody").asString();
            int status = annotation.value("status").asInt();

            // validações
            mocks.produce(new MockBuildItem(path, responseBody, status));
        }
    }
}
