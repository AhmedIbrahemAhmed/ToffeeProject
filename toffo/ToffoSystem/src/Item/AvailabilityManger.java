package Item;

import ItemDB.ItemDB;

public class AvailabilityManger {
    public boolean isAvailable(Item item){
        return item.getQuantity()==0;
    }

    public void changeStatus(Item item){
        if(isAvailable(item)){
            ItemDB itemDB = new ItemDB();
            item.setStatus("out of stock");
            itemDB.updateItemDBInfo(item);
        }
    }
}
