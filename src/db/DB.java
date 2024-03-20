package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    // estabelece a conexão
    public static Connection getConnectionDb() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException | ClassNotFoundException e) {
                throw new DbException(e.getMessage());
            }
        }
        return  conn;
    }

    // carrega as propriedades
    private static Properties loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fileInputStream);
            return props;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Close connection
    public static void closeConcection() {
        if (conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    
    // Close Statement
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    
    // close ResultSet
    public  static  void closeResultSet(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    } 

}
