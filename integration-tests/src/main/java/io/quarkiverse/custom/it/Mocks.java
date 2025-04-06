package io.quarkiverse.custom.it;

import io.quarkiverse.custom.runtime.GETMock;

public interface Mocks {

    @GETMock(path = "/users", status = 200, responseBody = "[]")
    void mockUsers();
}
