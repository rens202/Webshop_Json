package webservices;

import domain.Order;
import domain.User;
import persistence.ShopService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/orders")
@RolesAllowed({"USER", "ADMIN"})
public class OrderResource {
    private ShopService shopService = new ShopService();

    @GET
    @Produces("application/json")
    public Response getOrders(@Context SecurityContext sec) {
        String username = sec.getUserPrincipal().getName();
        User user = shopService.getUser(username);

        List<Order> orders = user.getCustomer().getAccount().getOrders();

        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getOrder(@Context SecurityContext sec,
                             @PathParam("id") int id) {
        Order order = shopService.getOrder(id);
        return Response.ok(order).build();
    }
}
