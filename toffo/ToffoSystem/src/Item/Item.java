package Item;

public class Item{
    private int itemID;
    private String name;
    private String category;
    private String brand;
    private String description;
    private String unitType;
    private double price;
    private double discount;
    private String status;
    private int quantity;
    //private loyalityPoint loyalitypoint;

    public Item(){
        this.name = "";
        this.category = "";
        this.brand = "";
        this.unitType = "";
        this.status = "";
        this.description = "";
        this.price = 0;
        this.discount = 0;
        this.quantity = 0;
    }


    public Item(String name, String category, String brand, String unitType,
         String status, String description, double price, double discount,int quantity){
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.unitType = unitType;
        this.status = status;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getItemID(){
        return itemID;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public String getBrand(){
        return brand;
    }

    public String getDescription(){
        return description;
    }

    public String getUnitType(){
        return unitType;
    }

    public String getStatus(){
        return status;
    }

    public double getPrice(){
        return price;
    }

    public double getDiscount(){
        return discount;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void  setBrand(String brand){
        this.brand = brand;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setUnitType(String unitType){
        this.unitType = unitType;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
