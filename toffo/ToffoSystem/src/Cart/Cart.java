package Cart;

import CartDB.CartDB;
import IDGenerator.IDGenerator;
import Item.Item;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Item>items = new ArrayList<Item>();
    private double totalPrice = 0;
    private long cartID;

    public Cart(){
        IDGenerator ID = new IDGenerator();
        this.cartID = ID.generate();
    }

    public long getCartID() {
        return cartID;
    }

    public void addItemList(Item item,long orderID){
        CartDB cartDB = new CartDB();
        cartDB.saveCart(this,item.getItemID(),orderID);
        items.add(item);
        totalPrice+=(item.getPrice()-item.getDiscount());
    }

    public void removeItemList(int itemID){
        if(items.isEmpty())
            return;
        for (Item i:items) {
            if(i.getItemID()==itemID) {
                totalPrice-=(i.getPrice()-i.getDiscount());
                items.remove(i);
            }
        }
    }

    public double getTotalPrice(){
        return totalPrice;
    }

}
