package User;

import Cart.Cart;
import ClientDB.ClientDB;
import ItemDB.ItemDB;
import LoyaltyPointSchema.LoyaltyPoint;
import Item.* ;
import Order.Order;
import Order.OrderDB;
import java.util.Scanner;

public class Client extends RegisteredUser{
    private LoyaltyPoint point ;
    private String email;
    private String status;
    private Order order;
    private Cart cart;

    public Client(String userName, String password,String email) {
        super(userName, password);
        this.point = new LoyaltyPoint();
        this.email = email;
        this.cart = new Cart();
        this.status = "Active";
    }

    public Client() {
        super();
    }


     private int choices(){
         int choice;
         Scanner scan = new Scanner(System.in);
         System.out.println();
         System.out.println("1.add Item to cart");
         System.out.println("2.remove Item from cart");
         System.out.println("3.order now");
         System.out.print("your choice: ");
         choice = scan.nextInt();
         System.out.println();
         return choice;
    }

    private String scanProductName(){
        Scanner scanner = new Scanner(System.in);
        String productName;
        System.out.println();
        System.out.print("Enter product name: ");
        productName = scanner.nextLine();
        return productName;
    }

    private void addItemCart(){
        boolean checker = true;
        Item item = null;
        Scanner scanner = new Scanner(System.in);
        int quantity = 0;

        while(checker) {
            String productName = scanProductName();
            System.out.println();
            System.out.print("Enter Quantity: ");
            quantity = scanner.nextInt();

            ItemDB itemDB = new ItemDB();
            long itemID = itemDB.retrieveItemID(productName);
            item = itemDB.loadItem(itemID);

            if((item.getQuantity() - quantity)<0){
                System.out.println("Sorry there is not enough stock for this item");
                continue;
            }

            if(quantity>50) {
                System.out.println("you cant order more than 50 units or kilos per item");
                continue;
            }


            AvailabilityManger manger = new AvailabilityManger();
            checker = manger.isAvailable(item);
            if(checker)
                System.out.println("Sorry your item is not Available");
        }

        item.setQuantity(quantity);
        cart.addItemList(item);
    }


    private void removeItemCart(){
        String productName = scanProductName();
        cart.removeItemList(productName);
    }

    private void orderNow(){
        if(cart.getItems().isEmpty()){
            System.out.println("Your cart is empty");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("order Address: ");
        String orderAddress = scanner.nextLine();
        System.out.println();

        order = new Order(cart,orderAddress,"cash","shipped",ID);
        order.setCheckoutDate();

        OrderDB orderDB = new OrderDB();
        orderDB.saveOrder(order);
        cart.checkout();
    }

     public void makeOrder(){
        viewCatalog();
        Scanner scan = new Scanner(System.in);
        boolean shopping = true;

        while(shopping) {
            int choice = choices();

            switch (choice){
                case 1:
                    addItemCart();
                    break;
                case 2:
                    removeItemCart();
                    break;
                case 3:
                    orderNow();
                    return;
            }
        }
    }

    public void cancelOrder(){
       Scanner scanner = new Scanner(System.in);
       System.out.print("order ID: ");
       long orderID = scanner.nextLong();
       System.out.println();

       OrderDB orderDB= new OrderDB();
       orderDB.removeOrder(orderID);
    }

    public void viewOrders(){
        OrderDB orderDB = new OrderDB();
        orderDB.viewOrders(userName);
    }

    public void orderLastOrder(){
        OrderDB orderDB = new OrderDB();
        cart = orderDB.retrieveLastOrder(userName);
    }
    public LoyaltyPoint getPoint() {
        return point;
    }

    public void setPoint(LoyaltyPoint point) {
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
