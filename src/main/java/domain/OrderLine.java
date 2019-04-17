package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.StringJoiner;

public class OrderLine {
    private int id;
    private int quantity;
    private double price;
    private Product product;

    @JsonIgnore
    private Order order;


    public OrderLine(int id, int quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
    public OrderLine(){
            }


    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderLine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("quantity=" + quantity)
                .add("price=" + price)
                .add("\n  product=" + product)
                .toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
