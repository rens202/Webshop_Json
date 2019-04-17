package domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Account {
    private int id;
    private Date openDate;
    private int isActive;
    private List<Order> orders;
    private Address address;
    private Customer customer;

    public Account(int id, Date openDate, int isActive) {
        this.id = id;
        this.openDate = openDate;
        this.isActive = isActive;
        orders = new ArrayList<>();
    }

    public Account(Date openDate, int isActive){
        this.openDate = openDate;
        this.isActive = isActive;
        orders = new ArrayList<>();

    }

    public Account(){
        orders = new ArrayList<>();
    }


    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("openDate=" + openDate)
                .add("isActive=" + isActive)
                .add("\n  orders=" + orders)
                .toString();
    }

    public int getIsActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public int isActive() {
        return isActive;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
