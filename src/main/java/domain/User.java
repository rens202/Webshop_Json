package domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"username", "password", "street", "housenumber", "name", "picture"})
public class User {
    private String username;
    private String password;
    private String role;
    private Customer customer;

    public User() {

    }

    public User(String us, String pass){
        this.username = us;
        this.password = pass;
    }

    public User(String username, String password, String role, Customer customer) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.customer = customer;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonPropertyOrder({"street", "housenumber", "name", "picture"})
    public Customer getCustomer() {
        return customer;
    }

    @JsonPropertyOrder({"street", "housenumber", "name", "picture"})
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("role='" + role + "'")
                .add("\n  customer='" + customer + "'")
                .toString();
    }
}

