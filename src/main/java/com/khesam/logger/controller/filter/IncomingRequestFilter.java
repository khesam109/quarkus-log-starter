package com.khesam.logger.controller.filter;

import org.jboss.logging.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class IncomingRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        UUID uuid = UUID.randomUUID();
        MDC.clear();
        MDC.put("traceId", uuid.toString());
    }
}
