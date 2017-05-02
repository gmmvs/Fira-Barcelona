package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Marc Espinosa on 01/05/2017.
 */
public class DAOFactory {
    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/FiraBarcelona";
    String dbUser = "root";
    String dbPwd = "";

    private static DAOFactory DAOFactory = null;

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

    public static DAOFactory getInstance() {
        if (DAOFactory == null) {
            DAOFactory = new DAOFactory();
        }
        return DAOFactory;
    }
}
