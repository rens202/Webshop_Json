package domain;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Category {
    private int id;
    private String name;
    private String description;
    private String picture;
    private ArrayList<Product> products;

    public Category(int id, String name, String description, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public Category(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public Category() {

    }

    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Category.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("picture='" + picture + "'")
                .toString();
    }
}
