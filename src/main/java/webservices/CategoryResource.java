package webservices;

import domain.Category;
import persistence.ShopService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryResource {
    private ShopService shopService = new ShopService();

    @GET
    @Produces("application/json")
    public Response getCategories() {
        List<Category> categories = shopService.getAllCategories();
        return Response.ok(categories).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProduct(@PathParam("id") int id) {
        Category category = shopService.getCategory(id);
        return Response.ok(category).build();
    }
}
