package User;

import java.sql.*;

public class OwnerDB {
    protected Owner owner = new Owner();

    public Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:toffoSystemDB/OwnerDB/OwnerDB.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:toffoSystemDB/" + fileName;
        Connection conn = null ;
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public  void createNewTable() {
        String url = "jdbc:sqlite:toffoSystemDB/OwnerDB/OwnerDB.db";
        String sql = "CREATE TABLE IF NOT EXISTS Admin (\n"
                + " ID INTEGER64 PRIMARY KEY,\n"
                + " userName text NOT NULL,\n"
                + " password text NOT NULL\n"
                + ");";
        Connection conn = null ;
        try{
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public Owner loadOwner(String username, String password){
        String sql = "SELECT * FROM Owner " + "WHERE userName= "+"'" +username+"'"+" AND password= "+"'"+password+"'"+";";
        Connection conn = null ;
        try {
            conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            owner.setID(rs.getLong("ID"));  ;
            owner.setUserName(rs.getString("userName"));   ;
            owner.setPassword(rs.getString("password"));  ;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return owner ;
    }

    public void saveOwner(Owner owner){
        String sql = "INSERT INTO Owner(ID,userName,password ) VALUES(?,?,?)";
        Connection conn = connect();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getID());
            pstmt.setString(2, owner.getUserName());
            pstmt.setString(3, owner.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
