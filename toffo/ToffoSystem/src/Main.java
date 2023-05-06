import Cart.Cart;
import Item.Item;
import Item.AvailabilityManger;
import ItemDB.ItemDB;

public class Main {
    public static void main(String[] args) {
        Item item = new Item();
        ItemDB db = new ItemDB();
        Cart cart = new Cart();

        item = db.loadItem(5);
       cart.addItemList(item);
       System.out.println(cart.getTotalPrice());
    }
}