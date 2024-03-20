package com.redhat.developers.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessProbe implements HealthCheck {

    /*
    Health Checks are important in platforms like Kubernetes because it allows the infrastructure to be aware of the state of the application.

    There are two different types of health checks:
        - Liveness probes tell your platform if your application is running ok or not.
                   When your liveness probe is down, your platform might restart your instance to guarantee that you have the minimum required amount of running instances in production.
        - Readiness probes tell your platform if your application is warm enough to reply to requests in a reasonable amount of time.
                Java applications, for example, might need some time to warm up, so the readiness probe should be up only when it’s ready to reply to a request in a timely manner.
                Checks that depend on other services should be implemented as readiness probes: if a remote service is down, restarting your application won’t fix the issue.
    */

    // curl localhost:8080/q/health | jq

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("I'm alive");
    }
}
