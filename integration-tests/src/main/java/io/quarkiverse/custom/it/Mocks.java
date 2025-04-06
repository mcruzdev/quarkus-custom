package io.quarkiverse.custom.it;

import io.quarkiverse.custom.runtime.GETMock;

public interface Mocks {

    @GETMock(path = "/cars", status = 400, responseBody = "{}")
    void mockUsers();
}
