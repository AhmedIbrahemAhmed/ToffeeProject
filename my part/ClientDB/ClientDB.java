package ClientDB;

import LoyaltyPointSchema.LoyaltyPoint;
import User.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ClientDB {
    private String sqlToString(String sqlFile){
        String sqlScript = "";
        BufferedReader bufferedReader = null;
        try {
            FileReader file = new FileReader("toffoSystemDB/clientDB/"+sqlFile);
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

    public Connection connectClientDB(){
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:toffoSystemDB/clientDB/clientDB.db";
            connection = DriverManager.getConnection(url);
        }catch (SQLException exception){
            System.out.println("connection failed");
        }
        return connection;
    }


    public void createClientTable() {
        Connection connection = connectClientDB();
        String createItemTable = sqlToString("clientDB.sql");
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


    public void saveClient(Client client){
        createClientTable();
        String saveSql = "INSERT INTO Client(clientID,name,password,loyalityPoints,email,status) " +
                "VALUES(?,?,?,?,?,?)";

        Connection connection = connectClientDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(saveSql);
            preparedStatement.setLong(1,client.getID());
            preparedStatement.setString(2,client.getUserName());
            preparedStatement.setString(3,client.getPassword());
            preparedStatement.setInt(4,client.getPoint().getPoints());
            preparedStatement.setString(5,client.getEmail());
            preparedStatement.setString(6,client.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Client loadClient(String userName){
        Connection connection = connectClientDB();
        String loadSql = "SELECT * " +
                "FROM Client " +
                "WHERE name =  "+"'"+userName+"'";
        Client client = new Client();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSql);
            String name = resultSet.getString("name");
            long id = resultSet.getLong("clientID");
            int point = resultSet.getInt("loyalityPoints");
            String password = resultSet.getString("password") ;
            String email = resultSet.getString("email") ;
            String status = resultSet.getString("status") ;
            LoyaltyPoint Point = new LoyaltyPoint();
            Point.setPoints(point);
            if(resultSet.wasNull()){
                client.setUserName("");
                client.setID(0);
                client.setEmail("");
                client.setPassword("");
                client.setStatus("");
            }
            else{
                client.setUserName(name);
                client.setID(id);
                client.setPassword(password);
                client.setEmail(email);
                client.setStatus(status);
                client.setPoint(Point);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return client;
    }
    public void updateClient(Client client){
        Connection connection = connectClientDB();
        String updateSql = "UPDATE Client SET  loyalityPoints= " +client.getPoint().getPoints()+" WHERE name= "+"'"+client.getUserName()+"'" ;
        try{
             PreparedStatement pstmt = connection.prepareStatement(updateSql) ;

            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
