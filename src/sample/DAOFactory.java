package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/FiraBarcelona";
    String dbUser = "root";
    String dbPwd = "root";

    private static sample.DAOFactory DAOFactory = null;

    private DAOFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static sample.DAOFactory getInstance() {
        if (DAOFactory == null) {
            DAOFactory = new DAOFactory();
        }
        return DAOFactory;
    }
}