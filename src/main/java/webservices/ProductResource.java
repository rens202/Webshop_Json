package webservices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Product;
import persistence.ShopService;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    private ShopService shopService = new ShopService();

    @GET
    @Produces("application/json")
    public Response getProducts() {
        List<Product> products = shopService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProduct(@PathParam("id") int id) {
        Product product = shopService.getProduct(id);
        return Response.ok(product).build();
    }

    @POST
    @Produces("application/json")
    @RolesAllowed({"ADMIN"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeProduct(String productData){
        Product product = jsonToProduct(productData);
        shopService.saveProduct(product);

        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @RolesAllowed({"ADMIN"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeProduct(@PathParam("id") int id,
                                  String productData) {
        Product product = jsonToProduct(productData);
        shopService.updateProduct(product);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    @RolesAllowed({"ADMIN"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) {
        shopService.deleteProduct(id);
        return Response.ok().build();
    }

    public Product jsonToProduct(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        Product product = null;

        try{
            product = objectMapper.readValue(json, Product.class);
            System.out.println(product);
        }catch (JsonGenerationException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return product;

    }


}
