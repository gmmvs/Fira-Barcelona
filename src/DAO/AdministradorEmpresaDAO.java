package DAO;

import Exceptions.ExceptionNotAnUser;
import Usuaris.AdministradorEmpresa;
import Usuaris.AdministradorFira;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class AdministradorEmpresaDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public AdministradorEmpresaDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertAdministradorEmpresa(AdministradorEmpresa administradorEmpresa) {
        try {
            String queryString = "INSERT INTO AdministradorEmpresa( User, Passwd, nomEmpresa) VALUES(?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, administradorEmpresa.getUser());
            ptmt.setString(2, administradorEmpresa.getPasswd());
            ptmt.setString(3, administradorEmpresa.getNomEmpresa());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                administradorEmpresa.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateAdministradorEmpresa(AdministradorEmpresa administradorEmpresa) {

        try {
            String queryString = "UPDATE AdministradorEmpresa SET User = ?, Passwd = ?, nomEmpresa = ? WHERE Id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, administradorEmpresa.getUser());
            ptmt.setString(2, administradorEmpresa.getPasswd());
            ptmt.setString(3, administradorEmpresa.getNomEmpresa());
            ptmt.setInt(4, administradorEmpresa.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteAdiministradorEmpresa(int codi) {

        try {
            String queryString = "DELETE FROM AdministradorEmpresa WHERE Id=?";
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

    public AdministradorEmpresa selectAdministradorEmpresa(int id) {
        AdministradorEmpresa admin = new AdministradorEmpresa();
        try {
            String queryString = "SELECT * FROM AdministradorEmpresa WHERE Id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                admin = new AdministradorEmpresa(resultSet.getString("User"),resultSet.getString("Passwd"),resultSet.getString("NomEmpresa"));
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


    public ArrayList<AdministradorEmpresa> llistarAdministradorEmpresa() {
        try {
            ArrayList<AdministradorEmpresa> Administradors = new ArrayList<>();
            String queryString = "SELECT * FROM AdministradorEmpresa";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Administradors.add(selectAdministradorEmpresa(rs.getInt("Id")));
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
