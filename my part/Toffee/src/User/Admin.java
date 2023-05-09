package User;
import Item.* ;
import Order.* ;
import LoyaltyPointSchema.* ;
import ItemDB.ItemDB ;
public class Admin extends RegisteredUser {
    public Admin(String userName, String password, String ID) {
        super(userName, password, ID);
    }

    public Admin() {}

    public void addItemToCatalog(Item item){
        ItemDB add = new ItemDB();
        add.saveItem(item);
    }
    public void removeItemFromCatalog(Item item){
        ItemDB remove = new ItemDB() ;
        remove.removeItemDB(item.getItemID());
    }
    public void updateItemInfo(Item item){
        ItemDB update = new ItemDB() ;
        update.updateItemDBInfo(item);
    }
    public void editLoyaltyPointsSchema(){

    }
    public void viewOrders(){
        OrderDB view = new OrderDB() ;
        view.viewOrders();
    }

}
