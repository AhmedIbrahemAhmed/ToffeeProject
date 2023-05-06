
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Voucher myVoucher = new Voucher() ;
        myVoucher.generateVoucher();
        LoyaltyPoint points = new LoyaltyPoint();
        points.setPointPrice(5);
        points.setPoints(5);
        CashPayment pay = new CashPayment();
        pay.setLoyaltyPoints(points) ;
        pay.setVoucher(myVoucher);
        pay.setAmount(50);
        pay.printRecipe();

//        System.out.println(voucher.getVoucherCode());
//        System.out.println(voucher.getVoucherDiscount());
//        System.out.println(voucher.getVoucherID());
//        test.createNewDatabase("test2.db");
    }
}