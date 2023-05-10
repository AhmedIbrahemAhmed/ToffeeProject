package VoucherDB;

import Voucher.Voucher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DatabaseMetaData;


public class VoucherDB {
    protected Voucher voucher = new Voucher() ;
    public VoucherDB(){
        voucher = new Voucher();
    }
    public Connection  connect() {

            Connection connection = null;
            try {
                // db parameters
                String url = "jdbc:sqlite:toffoSystemDB/VoucherDB/VoucherDB.db";
                // create a connection to the database
                connection = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return connection ;
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
        String url = "jdbc:sqlite:toffoSystemDB/VoucherDB/VoucherDB.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS VoucherDB (\n"
                + " voucherID text PRIMARY KEY,\n"
                + " voucherCode text NOT NULL,\n"
                + " discount real\n"
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


    public Voucher loadVoucher(String voucherCode){
        String sql = "SELECT * FROM VoucherDB " + "WHERE voucherCode= "+"'" +voucherCode+"'"+";";
        Connection conn = null ;
        try {
            conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            voucher.setVoucherDiscount(rs.getDouble("discount"))  ;
            voucher.setVoucherCode(rs.getString("voucherCode"))   ;
            voucher.setVoucherID(rs.getInt("voucherID"))  ;

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
        return voucher ;
    }

    public void saveVoucher(Voucher voucher){
        String sql = "INSERT INTO VoucherDB(voucherCode,discount ) VALUES(?,?)";
        Connection conn = null ;
        try{
            conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucher.getVoucherCode());
            pstmt.setDouble(2, voucher.getVoucherDiscount());
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
    public void removeVoucher(String voucherCode){
        String sql = "DELETE FROM VoucherDB WHERE voucherCode = ?";
        Connection conn = null ;
        try{
            conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucherCode);
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
    public Voucher getVoucher() {
        return voucher;
    }
}
