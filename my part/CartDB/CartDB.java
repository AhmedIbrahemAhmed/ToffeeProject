package CartDB;

import Cart.Cart;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class CartDB {
    private String sqlToString(String sqlFile){
        String sqlScript = "";
        BufferedReader bufferedReader = null;

        try {
            FileReader file = new FileReader("toffoSystemDB/cartDB/"+sqlFile);
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

    Connection connectCartDB(){
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:toffoSystemDB/cartDB/cartDB.db";
            connection = DriverManager.getConnection(url);
        }catch (SQLException exception){
            System.out.println("connection failed");
        }
        return connection;
    }

    private void createCartTable() {
        Connection connection = connectCartDB();
        String createCartTable = sqlToString("cartDB.sql");
        try {
            Statement statement = connection.createStatement();
            statement.execute(createCartTable);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCart(long cartID,long itemID,int itemQuantity){
        createCartTable();
        String insertSql = "INSERT INTO Cart(cartID, itemID,itemQuantity)" +
                "VALUES(?,?,?)";

        Connection connection = connectCartDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1,cartID);
            preparedStatement.setLong(2,itemID);
            preparedStatement.setInt(3,itemQuantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
