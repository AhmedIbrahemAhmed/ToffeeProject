package Voucher;

import java.util.Objects;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger ;
import VoucherDB.VoucherDB ;
public class Voucher {
    protected String voucherCode ;
    protected double discount= 0 ;
    protected int voucherID ;
    public Voucher(){
        this.discount = 0 ;
        this.voucherCode = "" ;
        this.voucherID = 0 ;
    }
    public boolean isValidVoucher(String voucherCode){
        VoucherDB connect = new VoucherDB() ;
        if(connect.loadVoucher(voucherCode).getVoucherCode()!="")
            return true;
        else
            return false ;
    }

    public double getVoucherDiscount() {
        return discount;
    }

    public void setVoucherDiscount(double discount) {
        this.discount = discount;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String generateVoucher(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("enter amount of discount");
        this.discount = myObj.nextDouble();
        SecureRandom rand = new SecureRandom();
        String random = Integer.toString(rand.nextInt(100000)) ;
        this.voucherCode = random ;
        VoucherDB database = new VoucherDB() ;
        database.saveVoucher(this);
        return random ;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public String getVoucherCode() {
        return voucherCode;
    }
    public double redeem(String voucherCode ){
        VoucherDB database = new VoucherDB() ;
        double discount =database.loadVoucher(voucherCode).getVoucherDiscount();
        database.removeVoucher(voucherCode);
        return discount ;
    }
}

