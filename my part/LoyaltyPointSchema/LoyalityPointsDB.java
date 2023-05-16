package LoyaltyPointSchema;

import java.sql.*;

public class LoyalityPointsDB {
    Connection connectPointDB(){
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:toffoSystemDB/LoyalityPointsDB/LoyalityPointsDB.db";
            connection = DriverManager.getConnection(url);
        }catch (SQLException exception){
            System.out.println("connection failed");
        }
        return connection;
    }

    private void createPointTable() {
        Connection connection = connectPointDB();
        String createPointTable = "CREATE TABLE IF NOT EXISTS LoyalityPoint(pointSchema int)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(createPointTable);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertSchema(int schema){
        createPointTable();
        String loadSql = "SELECT * FROM LoyalityPoint";
        Connection connection = connectPointDB();
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSql);
            if(resultSet.getInt(1)==0){
                String insertSql = "INSERT INTO LoyalityPoint (pointSchema)" +
                        "VALUES(?)";
                PreparedStatement preparedStatement= connection.prepareStatement(insertSql);
                preparedStatement.setInt(1,schema);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSchema(int newSchema){
        insertSchema(newSchema);
        String sqlUpdate = "UPDATE LoyalityPoint" +
                " SET pointSchema = ? ";

        Connection connection = connectPointDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setInt(1,newSchema);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int loadSchema(int schema){
        insertSchema(schema);
        String loadSql = "SELECT * FROM LoyalityPoint";
        Connection connection = connectPointDB();
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loadSql);
            schema = resultSet.getInt("pointSchema");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schema;
    }

}
