import Cart.Cart;
import CartDB.CartDB;
import Item.Item;
import Item.AvailabilityManger;
import ItemDB.ItemDB;
import Order.Order;
import Order.OrderDB;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Kitkat","Chocolate","Nestla","unit","in stock","bsemsm",20,2,100);
        Item item2 = new Item("Moro","Chocolate","Nestla","unit","in stock","mshbesmsm",15,0,100);

        ItemDB itemDB = new ItemDB();
        itemDB.saveItem(item1);
        itemDB.saveItem(item2);

        Cart cart = new Cart();
        cart.addItemList(item1);
        cart.addItemList(item2);
        cart.checkout();

        Order order = new Order(cart,"sellicon Valley","paied","Shipped");
        order.setCheckoutDate();

        OrderDB orderDB = new OrderDB();
        orderDB.saveOrder(order);
    }
}