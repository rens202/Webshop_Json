package webservices;

import domain.Advertisement;
import persistence.ShopService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/advertisements")
public class AdvertisementResource {
    private ShopService shopService = new ShopService();

    @GET
    @Produces("application/json")
    public Response getAdvertisements() {
        List<Advertisement> categories = shopService.getAllAdvertisements();
        return Response.ok(categories).build();
    }

}
