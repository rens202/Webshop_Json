package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Order {
    private int id;
    private Address address;
    @JsonIgnore
    private Account account;
    private List<OrderLine> orderLines;

    public Order(int id) {
        this.id = id;
        orderLines = new ArrayList<>();
    }

    public Order(){
        orderLines = new ArrayList<>();
    }

    public Order(Address address, Account account){
        this.address = address;
        this.account = account;
        orderLines = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }


    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("address=" + address)
                .add("\n  orderLines=" + orderLines)
                .toString();
    }
}
