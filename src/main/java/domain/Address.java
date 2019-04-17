package domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"street", "housenumber"})
public class Address {
    private int id;
    private String street;
    private int number;

    public Address(String street, int id, int number) {
        this.street = street;
        this.id = id;
        this.number = number;
    }

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    public Address(){

    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("number")
    public int getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(int number) {
        this.number = number;
    }
}
