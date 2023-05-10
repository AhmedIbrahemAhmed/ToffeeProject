package User;

import Voucher.Voucher;

import java.sql.*;

public class AdminDB {
    protected Admin admin = new Admin();

    public Connection  connect() {

        Connection connection = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:toffoSystemDB/Admin/AdminDB.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

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
        // SQLite connection string
        String url = "jdbc:sqlite:toffoSystemDB/Admin/AdminDB.db";

        // SQL statement for creating a new table
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


    public Admin loadAdmin(String username, String password){
        String sql = "SELECT * FROM Admin " + "WHERE userName= "+"'" +username+"'"+" AND password= "+"'"+password+"'"+";";
        Connection conn = null ;
        try {
            conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            admin.setID(rs.getLong("ID"));  ;
            admin.setUserName(rs.getString("userName"));   ;
            admin.setPassword(rs.getString("password"));  ;

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
        return admin ;
    }

    public void saveAdmin(Admin admin){
        String sql = "INSERT INTO Admin(ID,userName,password ) VALUES(?,?,?)";
        Connection conn = null ;
        try{
            conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, admin.getID());
            pstmt.setString(2, admin.getUserName());
            pstmt.setString(3, admin.getPassword());
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
    public void removeAdmin(String username, String password){
        String sql = "DELETE FROM Admin WHERE userName= "+"'" +username+"'"+" AND password= "+"'"+password+"'"+";";
        Connection conn = null ;
        try{
            conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
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
