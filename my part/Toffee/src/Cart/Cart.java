package Cart;

import CartDB.CartDB;
import IDGenerator.IDGenerator;
import Item.Item;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Item>items;
    private double totalPrice;
    private long cartID;

    public Cart(){
        this.items = new ArrayList<Item>();
        this.totalPrice = 0;
        IDGenerator ID = new IDGenerator();
        this.cartID = ID.generate();
    }

    public void addItemList(Item item){
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

    public void checkout(){
        CartDB cartDB = new CartDB();
        for(Item item:items){
            cartDB.saveCart(cartID,item.getItemID());
        }
    }

    public long getCartID() {
        return cartID;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

}
