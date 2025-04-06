package io.quarkiverse.custom.runtime.recorder;

import java.util.function.Consumer;

import io.quarkus.runtime.annotations.Recorder;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;

@Recorder
public class MockRecorder {

    public Consumer<Route> route() {
        return new Consumer<Route>() {
            @Override
            public void accept(Route route) {
                route.method(HttpMethod.GET);
            }
        };
    }

    public Handler<RoutingContext> handler(int status, String responseBody) {
        return new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                HttpServerResponse response = routingContext.response();
                response.setStatusCode(status);
                response.send(responseBody);
            }
        };
    }
}
