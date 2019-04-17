package domain;

import java.sql.Date;

public class Advertisement {
    private int id;
    private Date fromDate;
    private Date untilDate;
    private double offerPrice;
    private String advertisementText;
    private Product product;

    public Advertisement(int id, Date fromDate, Date untilDate, double offerPrice, String advertisementText) {
        this.id = id;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.offerPrice = offerPrice;
        this.advertisementText = advertisementText;
    }

    public Advertisement(Date fromDate, Date untilDate, double offerPrice, String advertisementText) {
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.offerPrice = offerPrice;
        this.advertisementText = advertisementText;
    }

    public Advertisement(){
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product products) {
        this.product = products;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                ", offerPrice=" + offerPrice +
                ", advertisementText='" + advertisementText + '\'' +
                ", products=" + product +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getAdvertisementText() {
        return advertisementText;
    }

    public void setAdvertisementText(String advertisementText) {
        this.advertisementText = advertisementText;
    }
}
