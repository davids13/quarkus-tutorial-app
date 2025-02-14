package com.redhat.developers.security;

import jakarta.annotation.security.RolesAllowed;

public class MyServiceImpl implements MyService {

    // https://www.baeldung.com/quarkus-rest-client-clientbasicauth

    @Override
    @RolesAllowed("admin")
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
