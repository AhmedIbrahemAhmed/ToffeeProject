package Cart;

import Item.Item;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Item>items = new ArrayList<Item>();
    private double totalPrice = 0;

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

    public double getTotalPrice(){
        return totalPrice;
    }
}
