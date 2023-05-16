import ClientDB.ClientDB;
import Item.Item;
import LoyaltyPointSchema.LoyaltyPoint;
import Order.*;
import User.*;
import ClientDB.ClientDB;
import Regitretion.*;
import Voucher.Voucher;

import java.util.* ;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Welcome to the program!");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. View categories");
            System.out.println("4. Search Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Select user type:");
                    System.out.println("1. Admin");
                    System.out.println("2. Client");
                    System.out.println("3. Owner");
                    System.out.println("4. Return to Main Menu");
                    System.out.print("Enter your choice: ");
                    int userTypeChoice;
                    userTypeChoice = scanner.nextInt();
                    Login login = new Login();
                    switch (userTypeChoice) {
                        case 1:
                            Admin admin = new Admin();
                            admin =login.adminLogin() ;
                            adminMenu(admin);
                            break;
                        case 2:
                            Client client = new Client();
                            client = login.clientLogin();
                            clientMenu(client);
                            break;
                        case 3:
                            Owner owner = new Owner();
                            owner = login.ownerLogin();
                            ownerMenu(owner);
                            break;
                        case 4:
                            break ;
                        default:
                            System.out.println("Invalid choice, please try again.");
                    }
                        break;
                case 2:
                        SignUp signup = new SignUp();
                        signup.signup();
                         break;
                case 3:
                        RegisteredUser user = new RegisteredUser();
                        user.viewCatalog();
                    break;
                case 4:
                    RegisteredUser user2 = new RegisteredUser();
                    System.out.println("enter item name to search");
                     String itemName = scanner.next();
                    user2.searchItem(itemName);
                    break ;
                case 5:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        }
    }

    private static void ownerMenu(Owner owner) {
        Scanner scanner2 = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome to the menu!");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Update item");
            System.out.println("4. Edit loyalty points");
            System.out.println("5. View orders");
            System.out.println("6. View categories");
            System.out.println("7. Search item");
            System.out.println("8. add admin");
            System.out.println("9. remove admin");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

            int choice2 = scanner2.nextInt();

            switch (choice2) {
                case 1:
                    scanner2.nextLine();

                    System.out.print("Enter item name: ");
                    String name = scanner2.nextLine();

                    System.out.print("Enter item category: ");
                    String category = scanner2.nextLine();

                    System.out.print("Enter item brand: ");
                    String brand = scanner2.nextLine();

                    System.out.print("Enter item description: ");
                    String description = scanner2.nextLine();

                    System.out.print("Enter item unit type: ");
                    String unitType = scanner2.nextLine();

                    System.out.print("Enter item status: ");
                    String status = scanner2.nextLine();

                    System.out.print("Enter item price: ");
                    double price = scanner2.nextDouble();

                    System.out.print("Enter item discount: ");
                    double discount = scanner2.nextDouble();

                    System.out.print("Enter item ID: ");
                    long itemID = scanner2.nextLong();

                    System.out.print("Enter item quantity: ");
                    int quantity = scanner2.nextInt();

                    Item item = new Item(name,category,brand,unitType,status,description,price,discount,quantity);
                    owner.addItemToCatalog(item);
                    break;
                case 2:
                    System.out.print("Enter item ID: ");
                    long itemID2 = scanner2.nextLong();
                    owner.removeItemFromCatalog(itemID2);
                    break;
                case 3:
                    Item item2 = new Item() ;
                    owner.updateItemInfo(item2);
                    break;
                case 4:
                    System.out.println("enter the client to edit his loyalty points: ");
                    String name2 = scanner2.next();
                    System.out.println("enter the amount of loyalty points: ");
                    int points = scanner2.nextInt();
                    LoyaltyPoint point = new LoyaltyPoint();
                    point.setPoints(points);
                    owner.editLoyaltyPointsSchema(name2,point);
                    break;
                case 5:
                    System.out.println("enter the client to view his orders: ");
                    String name3 = scanner2.next();
                    owner.viewOrders(name3);
                    break;
                case 6:
                    owner.viewCatalog();
                    break;
                case 7:
                    System.out.println("enter item name to search");
                    String itemName = scanner2.next();
                    owner.searchItem(itemName);
                    break;
                case 8:
                    System.out.print("Enter username for admin: ");
                    String username = scanner2.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner2.nextLine();
                    Admin admin7 = new Admin(username,password) ;
                    owner.addAdmin(admin7);
                    break ;
                case 9:
                    System.out.print("Enter username for admin: ");
                    String username2 = scanner2.nextLine();
                    System.out.print("Enter password: ");
                    String password2 = scanner2.nextLine();
                    Admin admin8 = new Admin(username2,password2);
                    owner.removeAdmin(admin8);
                    break ;
                case 10:

                    isRunning = false;
                    System.out.println("Logged out!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        }
    }

    private static void clientMenu(Client client) {
        Scanner scanner3 = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Please select an option:");
            System.out.println("1. Make order");
            System.out.println("2. Cancel order");
            System.out.println("3. View orders");
            System.out.println("4. order last order");
            System.out.println("5. View catalog");
            System.out.println("6. Search item");
            System.out.println("7. logout");
            System.out.print("enter your choice: ");
            int choice = scanner3.nextInt();

            switch (choice) {
                case 1:
                    client.makeOrder();
                    break;
                case 2:
                    client.cancelOrder();
                    break;
                case 3:
                    client.viewOrders();
                    break;
                case 4:
                    client.orderLastOrder();
                    break;
                case 5:
                    client.viewCatalog();
                    break;
                case 6:
                    System.out.println("enter item name to search");
                    String itemName = scanner3.next();
                    client.searchItem(itemName);
                    break;
                case 7:
                    isRunning = false;
                    System.out.println("Logged out!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    private static void adminMenu(Admin admin) {
        Scanner scanner2 = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome to the menu!");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Update item");
            System.out.println("4. Edit loyalty points");
            System.out.println("5. View orders");
            System.out.println("6. View categories");
            System.out.println("7. Search item");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice2 = scanner2.nextInt();

            switch (choice2) {
                case 1:

                    scanner2.nextLine();

                    System.out.print("Enter item name: ");
                    String name = scanner2.nextLine();

                    System.out.print("Enter item category: ");
                    String category = scanner2.nextLine();

                    System.out.print("Enter item brand: ");
                    String brand = scanner2.nextLine();

                    System.out.print("Enter item description: ");
                    String description = scanner2.nextLine();

                    System.out.print("Enter item unit type: ");
                    String unitType = scanner2.nextLine();

                    System.out.print("Enter item status: ");
                    String status = scanner2.nextLine();

                    System.out.print("Enter item price: ");
                    double price = scanner2.nextDouble();

                    System.out.print("Enter item discount: ");
                    double discount = scanner2.nextDouble();

                    System.out.print("Enter item quantity: ");
                    int quantity = scanner2.nextInt();

                    Item item = new Item(name,category,brand,unitType,status,description,price,discount,quantity);
                    admin.addItemToCatalog(item);
                    break;
                case 2:
                    System.out.print("Enter item ID: ");
                    long itemID2 = scanner2.nextLong();
                    admin.removeItemFromCatalog(itemID2);
                    break;
                case 3:
                    System.out.println("enter id for item to update: ");
                    long itemID4 = scanner2.nextLong() ;
                    scanner2.nextLine();

                    System.out.print("Enter item name: ");
                    String name6 = scanner2.nextLine();

                    System.out.print("Enter item category: ");
                    String category2 = scanner2.nextLine();

                    System.out.print("Enter item brand: ");
                    String brand2 = scanner2.nextLine();

                    System.out.print("Enter item description: ");
                    String description2 = scanner2.nextLine();

                    System.out.print("Enter item unit type: ");
                    String unitType2 = scanner2.nextLine();

                    System.out.print("Enter item status: ");
                    String status2 = scanner2.nextLine();

                    System.out.print("Enter item price: ");
                    double price2 = scanner2.nextDouble();

                    System.out.print("Enter item discount: ");
                    double discount2 = scanner2.nextDouble();

                    System.out.print("Enter item quantity: ");
                    int quantity2 = scanner2.nextInt();
                    Item item2 = new Item(name6,category2,brand2,unitType2,status2,description2,price2,discount2,quantity2);
                    item2.setItemID(itemID4);
                    admin.updateItemInfo(item2);
                    break;
                case 4:
                    Scanner s = new Scanner(System.in);
                    System.out.println("Enter new Loyality point schema: ");
                    LoyaltyPoint loyaltyPoint = new LoyaltyPoint();
                    int schema = s.nextInt();
                    loyaltyPoint.setSchema(schema);
                    break;
                case 5:
                    System.out.println("enter the client to view his orders: ");
                    String name3 = scanner2.next();
                    admin.viewOrders(name3);
                    break;
                case 6:
                    admin.viewCatalog();
                    break;
                case 7:
                    System.out.println("enter item name to search");
                    String itemName = scanner2.next();
                    admin.searchItem(itemName);
                    break;
                case 8:
                    isRunning = false;
                    System.out.println("Logged out!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        }
    }
}