package domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"street", "housenumber", "name", "picture"})
public class Customer {
    private int id;
    private String name;
    private String picture;
    private Address address;
    private Account account;

    public Customer(int id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public Customer(String name, String picture, Address address, Account account) {
        this.name = name;
        this.picture = picture;
        this.address = address;
        this.account = account;
    }

    public Customer(){
    }

    public Customer( String name, String picture) {
        this.name = name;
        this.picture = picture;
    }


    @JsonPropertyOrder({"street", "housenumber"})
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("picture='" + picture + "'")
                .add("\n  address=" + address)
                .add("\n  account=" + account)
                .toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("picture")
    public String getPicture() {
        return picture;
    }

    @JsonProperty("picture")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @JsonPropertyOrder({"street", "housenumber"})
    public Address getAddress() {
        return address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
