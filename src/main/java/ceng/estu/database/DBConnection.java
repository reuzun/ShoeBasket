package ceng.estu.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author reuzun
 */
public class DBConnection {

    private static DBConnection dbConnection = null;

    private Connection con = null;

    private DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shoebasket", "root", "");
        } catch (Exception e) {
            System.out.println("eRROR CONNECTiON");
        }
    }

    protected static void initDB(){
        if(DBConnection.dbConnection == null)
            DBConnection.dbConnection = new DBConnection();
    }

    public static Connection getCon(){
        return dbConnection.getConnection();
    }

    private Connection getConnection() {
        return con;
    }
}
