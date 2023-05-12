package User;

import IDGenerator.IDGenerator;
import Item.Item;
import ItemDB.ItemDB;
import Regitretion.* ;
public class RegisteredUser implements User{

    protected String userName;
    protected String password;
    protected long ID;

    public RegisteredUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
        IDGenerator generator = new IDGenerator();
        this.ID = generator.generate();
    }

    public RegisteredUser() {

    }

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

    public void setID(long ID){
        this.ID = ID;
    }

    public long getID() {
        return ID;
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
        SignUp signup = new SignUp();
        signup.signup();
    }
}
