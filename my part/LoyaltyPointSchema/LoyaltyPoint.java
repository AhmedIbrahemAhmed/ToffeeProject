package LoyaltyPointSchema;
import ClientDB.ClientDB;
import Order.Order;

public class LoyaltyPoint {
    private int schema;
    private int points;
    private int pointPrice;

    public LoyaltyPoint(){
        this.schema = 50;
        this.points = 0;
        this.pointPrice = 1;
    }

    public int getPointPrice(){
        return pointPrice;
    }

    public void setPoints(int points){
        this.points+=points;
    }

    public int getPoints(){
        return points;
    }

    public void setSchema(int schema){
        LoyalityPointsDB loyalityPointsDB = new LoyalityPointsDB();
        loyalityPointsDB.updateSchema(schema);
    }

    public int getSchema(){
        LoyalityPointsDB loyalityPointsDB = new LoyalityPointsDB();
        schema  = loyalityPointsDB.loadSchema(schema);
        return schema;
    }
}
