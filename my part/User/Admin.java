package User;
import Item.* ;
import Order.* ;
import LoyaltyPointSchema.* ;
import ItemDB.ItemDB ;
import ClientDB.ClientDB ;

public class Admin extends RegisteredUser {
    public Admin(String userName, String password) {
        super(userName, password);
    }

    public Admin() {}

    public void addItemToCatalog(Item item){
        ItemDB add = new ItemDB();
        add.saveItem(item);
    }
    public void removeItemFromCatalog(long ID){
        ItemDB remove = new ItemDB() ;
        remove.removeItemDB(ID);
    }
    public void updateItemInfo(Item item){
        ItemDB update = new ItemDB() ;
        update.updateItemDBInfo(item);
    }
    public void editLoyaltyPointsSchema(String clientName,LoyaltyPoint point){
        ClientDB database = new ClientDB();
        Client client = new Client();
        client =database.loadClient(clientName) ;
        client.setPoint(point);
        database.updateClient(client);
    }
   public void viewOrders(String name){
        OrderDB view = new OrderDB() ;
        view.viewOrders( name);
    }

}
