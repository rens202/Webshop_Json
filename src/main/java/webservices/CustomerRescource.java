package webservices;

import domain.Customer;
import persistence.ShopService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomerRescource {
    private ShopService shopService = new ShopService();

    @GET
    @Produces("application/json")
    public Response getCustomers() {
        List<Customer> customers = shopService.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProduct(@PathParam("id") int id) {
        Customer customer = shopService.getCustomer(id);
        return Response.ok(customer).build();
    }
}
