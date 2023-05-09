package Order;

import Cart.Cart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class OrderDB {
    private String sqlToString(String sqlFile){
        String sqlScript = "";
        BufferedReader bufferedReader = null;

        try {
            FileReader file = new FileReader("toffoSystemDB/orderDB/"+sqlFile);
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

     Connection connectOrderDB(){
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:toffoSystemDB/orderDB/orderDB.db";
            connection = DriverManager.getConnection(url);
        }catch (SQLException exception){
            System.out.println("connection failed");
        }
        return connection;
    }

    private void createOrderTable() {
        Connection connection = connectOrderDB();
        String createOrderTable = sqlToString("orderDB.sql");
        try {
            Statement statement = connection.createStatement();
            statement.execute(createOrderTable);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOrder(Order order){
        createOrderTable();
        String insertSql = "INSERT INTO `Order`(orderID,cartID,orderPrice, orderAddress, checkoutDate, " +
                "paymentStatus,orderStatus,checkoutDate)" +
                " VALUES(?,?,?,?,?,?,?,?)";

        Connection connection = connectOrderDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1,order.getOrderID());
            preparedStatement.setLong(2,order.getShoppingCart().getCartID());
            preparedStatement.setDouble(3,order.getOrderPrice());
            preparedStatement.setString(4,order.getOrderAddress());
            preparedStatement.setString(5,order.getCheckoutDate());
            preparedStatement.setString(6,order.getPaymentStatus());
            preparedStatement.setString(7,order.getOrderStatus());
            preparedStatement.setString(8,order.getCheckoutDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // result of the join, join it with user id
   /* public Cart loadCart(){

    }
*/

    public Order retrieveLastOrder(){
        Connection connection = connectOrderDB();
        String loadSQL = "SELECT* \n" +
                "FROM `Order`\n" +
                "order by checkoutDate DESC\n" +
                "LIMIT 1;";

        Order order = new Order();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSQL);
            order.setOrderID(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
    public void viewOrders(){
        Connection connection = connectOrderDB();
        String loadSQL = "SELECT* \n" +
                "FROM `Order`\n";;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSQL);
            while(resultSet.next()) {
                System.out.println(resultSet.getLong("orderID")
                        + '\t' + resultSet.getLong("cartID")
                        + '\t' + resultSet.getDouble("orderPrice")
                        + '\t' + resultSet.getString("orderAddress")
                        + '\t' + resultSet.getString("checkoutDate")
                        + '\t' + resultSet.getString("paymentStatus")
                        +'\t'+ '\t' + resultSet.getString("orderStatus")
                        + '\t' + resultSet.getString("checkoutDate")
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // result of the join, join it with user id
     /*public void viewOrderHistory(){

     }*/
}
