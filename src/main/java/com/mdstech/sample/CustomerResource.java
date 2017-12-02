package com.mdstech.sample;

import com.mdstech.sample.interceptor.LogRequest;
import io.prometheus.client.Summary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;

@Path("customer")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @LogRequest(role="ADMIN")
    public Customer findById(@PathParam("id") Integer id) {
//        Instant instant = Instant.now();
        Summary.Timer requestTimer = customerService.getTimer("findById", id.toString());
        Customer customer = customerService.findById(id);
        try {
            Thread.sleep(id*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        requestTimer.observeDuration();
        customerService.getGauge().labels("custom", "custom1", "custom2").inc();
        return customer;
    }
}
