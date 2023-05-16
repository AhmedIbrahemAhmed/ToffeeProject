package Order;

public class OrderHistory {
    public void viewOrderHistory(String userName){
        OrderDB orderDB = new OrderDB();
        orderDB.viewOrders(userName);
    }
}
