public class LoyaltyPoint {
    private int pointPrice= 0 ;
    private int points= 0 ;

    public int getLoyaltyPrice() {
        return pointPrice;
    }
    public int getTotalPrice(){
        return pointPrice*points ;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
