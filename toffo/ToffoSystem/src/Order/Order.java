package Order;

import Cart.Cart;
import IDGenerator.IDGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Cart shoppingCart;
    private long orderID;
    //private Client customer;
    private double orderPrice;
    private String orderAddress;
    private String checkoutDate;
    private String paymentStatus;
    private String orderStatus;

    public Order(){
        this.shoppingCart = new Cart();
        IDGenerator ID = new IDGenerator();
        this.orderID = ID.generate();
        this.orderPrice = 0;
        this.orderAddress  = "";
        this.paymentStatus = "";
        this.orderStatus = "";
    }

    public Order(Cart shoppingCart,String orderAddress, String paymentStatus,String orderStatus){
        IDGenerator ID = new IDGenerator();
        this.orderID = ID.generate();
        this.shoppingCart = shoppingCart;
        this.orderPrice = shoppingCart.getTotalPrice();
        this.orderAddress = orderAddress;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

    public void setShoppingCart(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = shoppingCart.getTotalPrice();
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public void setCheckoutDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
       this.checkoutDate = currentDate.format(date);
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderID(long orderID){
        this.orderID = orderID;
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public long getOrderID() {
        return orderID;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

}
