package Payment;

import Payment.Payment;

import java.util.Scanner;
public class CashPayment  extends Payment {

    @Override
    public double calcAmount() {
        double discount = 0 ;
        double temp = amount ;
        while(true) {
            System.out.println("enter 1 to redeem vouchers or 2 to redeem loyalty points or 3 to calculate the amount");
            Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt() ;
            if(choice== 1){
                System.out.println("enter number of vouchers to redeem");
                int num = myObj.nextInt() ;
                while((num--)> 0){
                    System.out.println("enter code of voucher to redeem");
                    discount+= voucher.redeem(myObj.next());
                    if(discount>=1)
                        break ;
                }
            }
            else if (choice== 2) {
                System.out.println("enter number of points to redeem");
                int num2 = myObj.nextInt() ;
                while(num2> points.getPoints() || num2<0){
                    System.out.println("invalid number of points try again");
                    num2 = myObj.nextInt() ;
                }
                amount-= num2*points.getLoyaltyPrice();
            }
            else{
                break;
            }
        }
        return amount- (discount*temp);
    }
    public void printRecipe(){
        double cash = calcAmount() ;
        System.out.print("the needed cash for payment is: ");
        System.out.println(cash);
    }
}
