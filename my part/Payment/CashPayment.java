package Payment;

import ClientDB.ClientDB;
import LoyaltyPointSchema.LoyaltyPoint;
import Order.Order;
import Payment.Payment;
import Voucher.Voucher;
import User.Client;

import java.util.Scanner;
public class CashPayment  extends Payment {

    @Override
    public double calcAmount(Client client) {
        Scanner scan = new Scanner(System.in);

        System.out.print("do you want to apply voucher: ");
        System.out.println();
        System.out.println("1.apply voucher: ");
        System.out.println("2.continue: ");

        int choice =  scan.nextInt();
        if(choice==1) {
            Voucher voucher = new Voucher();
            System.out.println("your voucher code: ");
            String code;
            scan.nextLine();
            code = scan.nextLine();
            int v = voucher.redeem(code);
            double disc = (int) (client.getOrder().getOrderPrice() * ((double)v/100));
            double price = client.getOrder().getOrderPrice() - disc;
            client.getOrder().setOrderPrice(price);
        }


        if(client.getPoint().getPoints()>0){
            System.out.println("you have: "+
                    client.getPoint().getPoints());
            System.out.println("\n"+"do you want to use it");
            System.out.println("1.sure: ");
            System.out.println("2.continue:  "+"\n");
            int option = scan.nextInt();

            if(option == 1){
                int quantity = 0;
                while(true) {
                    System.out.println("Quantity: ");
                    quantity = scan.nextInt();
                    if (quantity > client.getPoint().getPoints())
                            System.out.println("there no enough points: ");
                    else
                        break;
                }
                int discount = (quantity*client.getPoint().getPointPrice());
                client.getOrder().setOrderPrice(client.getOrder().getOrderPrice() - (double)discount);
                client.getPoint().setPoints(-quantity);
            }
        }

        client.getPoint().getSchema();
        int point =(int)client.getOrder().getOrderPrice()/client.getPoint().getSchema();
        client.getPoint().setPoints(point);
        ClientDB clientDB = new ClientDB();
        clientDB.updateClient(client);

        return client.getOrder().getOrderPrice();
    }

    public void printRecipe(double cash){
        System.out.print("the needed cash for payment is: ");
        System.out.println(cash+"\n");
    }
}
