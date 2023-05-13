package Cart;

import CartDB.CartDB;
import IDGenerator.IDGenerator;
import Item.Item;
import ItemDB.ItemDB;

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
        totalPrice+=(item.getQuantity()*item.getPrice());
    }

    public void removeItemList(String itemName){
        if(items.isEmpty())
            return;
        for (int i=0 ; i<items.size(); i++) {
            if(items.get(i).getName().equals(itemName)) {
                totalPrice-=(items.get(i).getQuantity()*items.get(i).getPrice());
                items.remove(i);
            }
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private void updateItemQuantity(Item item){
        ItemDB itemDB = new ItemDB();
        Item stockItem = itemDB.loadItem(item.getItemID());
        stockItem.setQuantity(stockItem.getQuantity()-item.getQuantity());
        itemDB.updateItemDBInfo(stockItem);
    }

    public void checkout(){
        CartDB cartDB = new CartDB();
        for(Item item:items){
            updateItemQuantity(item);
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
