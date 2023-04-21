package main.java.sg.edu.nus.iss.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class OrderDetails {
    private Integer id;
    private DateTime orderDate;
    private Integer customerId;
    private Double totalPrice;
    private Double discount;
    private Double discountedPrice;
    private Double costPrice;
    
    public OrderDetails() {
    }
   
    public OrderDetails(Integer id, DateTime orderDate, Integer customerId, Double totalPrice,    
            Double discount, Double discountedPrice, Double costPrice) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.discountedPrice = discountedPrice;
        this.costPrice = costPrice;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public Double getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public Double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails [id=" + id + ", orderDate=" + orderDate + ", customerId=" + customerId + ", totalPrice="
                + totalPrice + ", discount=" + discount + ", discountedPrice=" + discountedPrice + ", costPrice="
                + costPrice + "]";
    }
    
    public static OrderDetails create(SqlRowSet resultSet) {
        OrderDetails od = new OrderDetails();
        DateTime orderDate = DateTimeFormat.forPattern("dd/MM/yyyy")
                .parseDateTime(resultSet
                .getString("order_date"));

        od.setId(resultSet.getInt("order_id"));
        od.setOrderDate(orderDate);
        od.setCustomerId(resultSet.getInt("customer_id"));
        od.setTotalPrice(resultSet.getDouble("total_price"));
        od.setDiscount(resultSet.getDouble("discount"));
        od.setDiscountedPrice(resultSet.getDouble("discounted_price"));
        od.setCostPrice(resultSet.getDouble("cost_price"));

        return od;
    }
}
