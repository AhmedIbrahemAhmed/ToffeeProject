import Cart.Cart;
import CartDB.CartDB;
import Item.Item;
import Item.AvailabilityManger;
import ItemDB.ItemDB;
import Order.Order;
import Order.OrderDB;

public class Main {
    public static void main(String[] args) {
        OrderDB orderDB = new OrderDB();
        Order order = orderDB.retrieveLastOrder();
        System.out.println(order.getOrderID());
    }
}