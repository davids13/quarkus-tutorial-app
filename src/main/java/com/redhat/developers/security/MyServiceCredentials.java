package com.redhat.developers.security;

import io.quarkus.rest.client.reactive.ClientBasicAuth;

@ClientBasicAuth(username = "john", password = "secret1")
public interface MyServiceCredentials extends MyService {
}
