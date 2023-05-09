package User;

import Item.Item;
import ItemDB.ItemDB;

public class RegisteredUser implements User{

    protected String userName;
    protected String password;
    protected String ID;

    public RegisteredUser(String userName, String password, String ID) {
        this.userName = userName;
        this.password = password;
        this.ID = ID;
    }

    public RegisteredUser() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public void viewCatalog() {
        ItemDB view = new ItemDB();
        view.viewItemDB();
    }

    @Override
    public void searchItem(String name) {
        ItemDB view = new ItemDB();
        Item item =view.loadItem(view.retrieveItemID(name)) ;
        System.out.println(item.getItemID()
                + '\t' + item.getName()
                + '\t' + item.getCategory()
                + '\t' + item.getBrand()
                + '\t' + item.getUnitType()
                + '\t' + item.getDescription()
                +'\t'+ '\t' + item.getStatus()
                + '\t' + item.getPrice()
                + '\t' + item.getDiscount()
                + '\t' + item.getQuantity());

    }

    @Override
    public void login() {

    }

    public void logout(){

    }

    @Override
    public void signUp() {

    }
}
