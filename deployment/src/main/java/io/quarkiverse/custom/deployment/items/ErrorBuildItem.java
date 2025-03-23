package io.quarkiverse.custom.deployment.items;

import java.util.Objects;

import io.quarkus.builder.item.SimpleBuildItem;

public final class ErrorBuildItem extends SimpleBuildItem {

    private final String message;

    public ErrorBuildItem(final String message) {
        this.message = Objects.requireNonNull(message);
    }

    public String getMessage() {
        return message;
    }
}
