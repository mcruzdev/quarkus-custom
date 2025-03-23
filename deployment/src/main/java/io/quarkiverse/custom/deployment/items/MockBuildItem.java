package io.quarkiverse.custom.deployment.items;

import io.quarkus.builder.item.MultiBuildItem;

/**
 * ...
 */
public final class MockBuildItem extends MultiBuildItem {

    private final String path;
    private final String responseBody;
    private final Integer status;

    public MockBuildItem(String path, String responseBody, Integer status) {
        this.path = path;
        this.responseBody = responseBody;
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MockBuildItem{" + "path='" + path + '\'' + ", responseBody='" + responseBody + '\'' + ", status=" + status
                + '}';
    }
}
