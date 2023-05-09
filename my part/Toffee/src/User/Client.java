package User;
import LoyaltyPointSchema.LoyaltyPoint;
import Item.Item ;
public class Client extends RegisteredUser{
    private LoyaltyPoint point ;

    public Client(String userName, String password, String ID, LoyaltyPoint point) {
        super(userName, password, ID);
        this.point = point;
    }

    public Client() {
        super();
    }
}
