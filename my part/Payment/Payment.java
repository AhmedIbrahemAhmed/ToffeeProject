package Payment;

import LoyaltyPointSchema.LoyaltyPoint;
import Order.Order;
import User.Client;
import Voucher.Voucher ;

public abstract class Payment {
    protected double amount ;
    protected Voucher voucher = new Voucher() ;

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    public abstract double  calcAmount(Client client);

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
