package webservices.auth;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Account;
import domain.Address;
import domain.Customer;
import domain.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import persistence.ShopService;
import persistence.UserDao;
import persistence.UserDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.Key;
import java.sql.Date;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Iterator;

@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();
    private ShopService shopService = new ShopService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(String userdata) {
        try {
            // Authenticate the user against the database
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            User user = null;
            try{
                user = objectMapper.readValue(userdata, User.class);
                System.out.println(user);
            }catch (JsonGenerationException e)
            {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UserDao dao = new UserDaoImpl();
            String role = dao.findRole(user.getUsername(), user.getPassword());
            String username = user.getUsername();
            if (role == null) {
                throw new IllegalArgumentException("No user found!");
            }
            System.out.println(String.format(" --- Login [User: %s] [Role: %s]", username, role));
            String token = createToken(username, role);
            AbstractMap.SimpleEntry<String, String> JWT = new AbstractMap.SimpleEntry<String, String>("JWT", token);
            return Response.ok(JWT).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }



    private String createToken(String username, String role) {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(String userData) {

        User user = jsonToUser(userData);

        if (shopService.getUser(user.getUsername()) != null) {
            return Response.serverError().entity("Username is already taken").build();
        }
        user.setRole("USER");
        System.out.println("user = " + user);
        if (shopService.saveNewUser(user))
            return Response.ok().build();
        return Response.serverError().build();
    }

    public User jsonToUser(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        User user = null;

        try{
            user = objectMapper.readValue(json, User.class);
            System.out.println(user);
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
        return user;

    }


}

