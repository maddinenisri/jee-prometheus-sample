package com.mdstech.sample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customer")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findById(@PathParam("id") Integer id) {
        return customerService.findById(id);
    }
}
