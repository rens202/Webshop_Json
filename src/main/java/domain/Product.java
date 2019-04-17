package domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.StringJoiner;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "price", "description", "picture"})
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String picture;
    private ArrayList<Category> categories;

    public Product(int id, String name, double price, String description, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    public Product(String name, double price, String description, String picture) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    public Product(){
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
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

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("picture")
    public String getPicture() {
        return picture;
    }

    @JsonProperty("picture")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("description='" + description + "'")
                .add("picture='" + picture + "'")
                .add("\n  categories=" + categories)
                .toString();
    }
}
