import Order.Order;
import Order.OrderDB;
import User.Admin;
import User.RegisteredUser;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin() ;
        admin.viewOrders();
        RegisteredUser user = new RegisteredUser("Ahmed","354312421","20210006");
        user.viewCatalog();
//        RegisteredUser user = new RegisteredUser("Ahmed","354312421","20210006");
//        user.searchItem("3salia");
//        System.out.println("Hello world!");
//        Voucher.Voucher myVoucher = new Voucher.Voucher() ;
//        myVoucher.generateVoucher();
//        LoyaltyPointSchema.LoyaltyPoint points = new LoyaltyPointSchema.LoyaltyPoint();
//        points.setPointPrice(5);
//        points.setPoints(5);
//        Payment.CashPayment pay = new Payment.CashPayment();
//        pay.setLoyaltyPoints(points) ;
//        pay.setVoucher(myVoucher);
//        pay.setAmount(50);
//        pay.printRecipe();
//        OrderDB orderDB = new OrderDB();
//        Order order = orderDB.retrieveLastOrder();
//        System.out.println(order.getOrderID());
    }
}