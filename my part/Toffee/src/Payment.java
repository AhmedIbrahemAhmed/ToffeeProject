public abstract class Payment {
    protected double amount ;
    protected Voucher voucher = new Voucher() ;
    protected LoyaltyPoint points =new LoyaltyPoint() ;
    protected Client customer = new Client() ;

    public void setLoyaltyPoints(LoyaltyPoint points) {
        this.points = points;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    public abstract double  calcAmount();

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
