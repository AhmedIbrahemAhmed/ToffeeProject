package ItemDB;

import Item.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ItemDB {
    //parse SQL file to string
    private String sqlToString(String sqlFile){
        String sqlScript = "";
        BufferedReader bufferedReader = null;

        try {
            FileReader file = new FileReader("toffoSystemDB/itemDB/"+sqlFile);
            bufferedReader = new BufferedReader(file);
        }catch (FileNotFoundException exception){
            System.out.println("file not found");
        }

        String line;
        while (true){
            try {
                line = bufferedReader.readLine();
                if (line != null) sqlScript+=line;
                else
                    break;
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        return sqlScript;
    }

    //update item table give it the required query and item object.it will excute the query(insert,update)
    private void preparedStatmentSet(String sqlQuery,Item item){
        Connection connection = connectItemDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1,item.getItemID());
            preparedStatement.setString(2,item.getName());
            preparedStatement.setString(3,item.getCategory());
            preparedStatement.setString(4,item.getBrand());
            preparedStatement.setString(5,item.getUnitType());
            preparedStatement.setString(6,item.getDescription());
            preparedStatement.setString(7,item.getStatus());
            preparedStatement.setDouble(8,item.getPrice());
            preparedStatement.setDouble(9,item.getDiscount());
            preparedStatement.setInt(10,item.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void excuteSqlStatement(String query){
        Connection connection = connectItemDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection connectItemDB(){
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:toffoSystemDB/itemDB/itemDB.db";
            connection = DriverManager.getConnection(url);
        }catch (SQLException exception){
            System.out.println("connection failed");
        }
        return connection;
    }

    public void createItemTable() {
        Connection connection = connectItemDB();
        String createItemTable = sqlToString("itemDB.sql");
        try {
            Statement statement = connection.createStatement();
            statement.execute(createItemTable);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveItem(Item item){
        createItemTable();
        String insertSQL = "INSERT INTO Item(itemID,name, category, brand, unitType, " +
                "description, status, price, discount,quantity)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?)";
        preparedStatmentSet(insertSQL,item);
    }

    //bug here review code!!!
    public Item loadItem(Long itemID){
        createItemTable();
        String loadSQL = "SELECT * " +
                         "FROM Item " +
                         "WHERE itemID="+itemID.toString();

        Item item = new Item();
        Connection connection = connectItemDB();
        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSQL);

            item.setItemID(resultSet.getLong("ItemID"));
            item.setName(resultSet.getString("name"));
            item.setCategory(resultSet.getString("category"));
            item.setBrand(resultSet.getString("brand"));
            item.setUnitType(resultSet.getString("unitType"));
            item.setDescription(resultSet.getString("description"));
            item.setStatus(resultSet.getString("status"));
            item.setPrice(resultSet.getDouble("price"));
            item.setDiscount(resultSet.getDouble("discount"));
            item.setQuantity(resultSet.getInt("quantity"));
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public long retrieveItemID(String name) {
        createItemTable();
        String retrieveID = "SELECT * FROM Item " +
                "WHERE name = "+"'"+name+"'";
        long ID = 0;
        Connection connection = connectItemDB();

        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveID);
           ID = resultSet.getLong("ItemID");
           connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ID;
    }

    public void updateItemDBInfo(Item item){
        createItemTable();
        Long itemID = item.getItemID();
        String sqlUpdate = "UPDATE Item\n" +
                "SET\n" +
                    "itemID = ? ," +
                    "name = ?,\n" +
                    "category = ?,\n" +
                    "brand = ?,\n" +
                    "unitType = ?,\n" +
                    "description = ?,\n" +
                    "status = ?,\n" +
                    "price = ?,\n" +
                    "discount = ?,\n" +
                    "quantity = ?"+
                "WHERE itemID = "+ itemID.toString();
        preparedStatmentSet(sqlUpdate,item);
    }

    public void removeItemDB(Long itemID){
        createItemTable();
        String deleteSql = "DELETE FROM Item " +
                            "WHERE itemID="+itemID.toString();
        excuteSqlStatement(deleteSql);
    }

    public void viewItemDB(){
        createItemTable();
        String selectSql = "SELECT *" +
                            "FROM Item";
        Connection connection = connectItemDB();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while(resultSet.next()) {
                System.out.println(resultSet.getString("itemID")+'\t'+'\t'+
                        resultSet.getString("name")
                        + '\t' + resultSet.getString("category")
                        + '\t' + resultSet.getString("brand")
                        + '\t' + resultSet.getString("unitType")
                        + '\t' + resultSet.getString("description")
                        +'\t'+ '\t' + resultSet.getString("status")
                        + '\t' + resultSet.getDouble("price")
                        + '\t' + resultSet.getDouble("discount")
                        + '\t' + resultSet.getInt("quantity"));
            }
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
