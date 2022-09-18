package model;

public class OrderDetail {
    private String orderId;
    private String orderDate;
    private String customerId;
    private double unitPrice;
    private int qty;
    private double tot;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String orderDate, String customerId, double unitPrice, int qty, double tot) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCustomerId(customerId);
        this.setUnitPrice(unitPrice);
        this.setQty(qty);
        this.setTot(tot);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", tot=" + tot +
                '}';
    }
}
