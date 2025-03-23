package io.quarkiverse.custom.deployment;

import java.util.List;

import io.quarkiverse.custom.deployment.items.MockBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Produce;
import io.quarkus.deployment.pkg.builditem.ArtifactResultBuildItem;

public class MultiCustomProcessor {

    @BuildStep
    MockBuildItem producesMockBuildItem() {
        return new MockBuildItem("/users", """
                [
                   {
                      "id": 1,
                      "name": "Matheus Cruz"
                   },
                   {
                      "id": 2,
                      "name": "John Doe"
                   }
                ]
                """, 200);
    }

    @BuildStep
    void producesUsingBuildProducer(BuildProducer<MockBuildItem> mocks) {
        mocks.produce(new MockBuildItem("/users/1", """
                   {
                      "id": 1,
                      "name": "Matheus Cruz"
                   }
                """, 200));

        mocks.produce(new MockBuildItem("/users/2", """
                   {
                      "id": 2,
                      "name": "John Doe"
                   }
                """, 200));
    }

    @BuildStep
    @Produce(ArtifactResultBuildItem.class)
    void consumeMockBuildItem(List<MockBuildItem> mocks) {
        mocks.forEach(item -> {
            System.out.println(item);
        });
    }
}
