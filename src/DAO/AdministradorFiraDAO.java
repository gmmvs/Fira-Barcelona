package DAO;

/**
 * Created by Marc Espinosa on 01/05/2017.
 */
import DAO.DAOFactory;
import Exceptions.ExceptionNotAnUser;
import Usuaris.*;

import java.sql.*;
import java.util.ArrayList;


public class AdministradorFiraDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public AdministradorFiraDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertAdministradorFira(AdministradorFira administradorFira) {
        try {
            String queryString = "INSERT INTO AdministradorFira( User, Passwd) VALUES(?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, administradorFira.getUser());
            ptmt.setString(2, administradorFira.getPasswd());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                administradorFira.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateAdiministradorFira(AdministradorFira administradorFira) {

        try {
            String queryString = "UPDATE AdministradorFira SET User=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, administradorFira.getUser());
            ptmt.setInt(2, administradorFira.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteAdiministradorFira(int codi) {

        try {
            String queryString = "DELETE FROM AdministradorFira WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, codi);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public AdministradorFira selectAdministradorFira(int id) {
        AdministradorFira admin = new AdministradorFira();
        try {
            String queryString = "SELECT * FROM AdministradorFira WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                admin = new AdministradorFira(resultSet.getString("User"),resultSet.getString("Passwd"));
                admin.setId(resultSet.getInt("Id"));
                resultSet.close();
                return admin;
            }else throw new ExceptionNotAnUser("No existeix Aquest Usuari");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionNotAnUser e) {
            System.out.println(e);
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<AdministradorFira> llistarAdministradorFira() {
        try {
            ArrayList<AdministradorFira> Administradors = new ArrayList<>();
            String queryString = "SELECT * FROM AdministradorFira";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Administradors.add(selectAdministradorFira(rs.getInt("Id")));
            }
            rs.close();
            return Administradors;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public void closeDbConn() {
        try {
            if (ptmt != null)
                ptmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



