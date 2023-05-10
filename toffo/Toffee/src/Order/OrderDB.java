package Order;

import Cart.Cart;
import Item.Item;

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
        String insertSql = "INSERT INTO `Order`(orderID,cartID,clientID,orderPrice, orderAddress, checkoutDate, " +
                "paymentStatus,orderStatus,checkoutDate)" +
                " VALUES(?,?,?,?,?,?,?,?,?)";

        Connection connection = connectOrderDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1,order.getOrderID());
            preparedStatement.setLong(2,order.getShoppingCart().getCartID());
            preparedStatement.setLong(3,order.getClientID());
            preparedStatement.setDouble(4,order.getOrderPrice());
            preparedStatement.setString(5,order.getOrderAddress());
            preparedStatement.setString(6,order.getCheckoutDate());
            preparedStatement.setString(7,order.getPaymentStatus());
            preparedStatement.setString(8,order.getOrderStatus());
            preparedStatement.setString(9,order.getCheckoutDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void attachDatabase(Connection connection, String folder,String databaseName,String name) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String attachSql = "ATTACH DATABASE '" +System.getProperty("user.dir")+"/toffoSystemDB/"+folder+"/"+databaseName + "' AS "+name;
            statement.executeUpdate(attachSql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cart retrieveLastOrder(String clientName){
        Connection connection = connectOrderDB();
        attachDatabase(connection,"clientDB","clientDB.db","db1");
        attachDatabase(connection,"orderDB","orderDB.db","db2");
        attachDatabase(connection,"itemDB","itemDB.db","db3");
        attachDatabase(connection,"cartDB","cartDB.db","db4");
        String loadSQL = "SELECT * " +
                "FROM `Order` " +
                "INNER JOIN Client ON Client.clientID = `Order`.clientID " +
                "INNER JOIN Cart ON Cart.cartID = `Order`.cartID " +
                "INNER JOIN Item ON Cart.itemID = Item.itemID " +
                "WHERE Client.name  = "+"'"+clientName+"'"+
                "ORDER BY `Order`.checkoutDate DESC";

        Cart cart = new Cart();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSQL);
            while(resultSet.next()){
                Item item = new Item();
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
                cart.addItemList(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }


    public void viewOrders(String clientName){
        Connection connection = connectOrderDB();
        attachDatabase(connection,"clientDB","clientDB.db","db1");
        String loadSQL = "SELECT * " +
                "FROM `Order` " +
                "INNER JOIN Client ON Client.clientID = `Order`.clientID" +
                " WHERE Client.name = "+"'"+clientName+"'";

        System.out.printf("%-15s %-15s %-30s %-20s %-15s %-20s\n",
                "Order ID", "Order Price", "Order Address", "Checkout Date", "Payment Status", "Order Status");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSQL);
            while(resultSet.next()) {
                System.out.printf("%-15d $%-14.2f %-30s %-20s %-15s %-20s\n",
                        resultSet.getLong("orderID"),
                        resultSet.getDouble("orderPrice"),
                        resultSet.getString("orderAddress"),
                        resultSet.getString("checkoutDate"),
                        resultSet.getString("paymentStatus"),
                        resultSet.getString("orderStatus"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeOrder(Long orderID){
        Connection connection = connectOrderDB();
        String removeSQL = "DELETE FROM `Order` WHERE orderID = "+orderID.toString();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
