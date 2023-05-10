package User;

public class Owner extends Admin{

    public Owner(String userName, String password) {
        super(userName, password);
    }

    public Owner() {}
    public void addAdmin(Admin admin){
        AdminDB add = new AdminDB();
        add.saveAdmin(admin);
    }
    public void removeAdmin(Admin admin){
        AdminDB remove = new AdminDB();
        remove.removeAdmin(admin.getUserName(),admin.getPassword());
    }
}
