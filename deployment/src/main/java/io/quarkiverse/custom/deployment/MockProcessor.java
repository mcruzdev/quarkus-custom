package io.quarkiverse.custom.deployment;

import java.util.List;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;

import io.quarkiverse.custom.deployment.items.MockBuildItem;
import io.quarkiverse.custom.runtime.GETMock;
import io.quarkiverse.custom.runtime.recorder.MockRecorder;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ApplicationIndexBuildItem;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.vertx.http.deployment.RouteBuildItem;

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

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void generateRoutes(List<MockBuildItem> mocks, BuildProducer<RouteBuildItem> routes, MockRecorder recorder) {
        for (MockBuildItem mock : mocks) {
            routes.produce(RouteBuildItem.builder().routeFunction(mock.getPath(), recorder.route())
                    .handler(recorder.handler(mock.getStatus(), mock.getResponseBody())).build());
        }
    }
}
