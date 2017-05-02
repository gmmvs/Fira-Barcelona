package DAO;

import Espais.Estants.Producte;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class ProducteDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public ProducteDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertProducte(Producte product, int idEstant) {
        try {
            String queryString = "INSERT INTO Producte( Nom, Preu, idEstant) VALUES(?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, product.getNom());
            ptmt.setDouble(2, product.getPreu());
            ptmt.setInt(3, idEstant);
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                product.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateProducte(Producte product, int idEstant){

        try {
            String queryString = "UPDATE Producte SET Nom=?, Preu=?, idEstant=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, product.getNom());
            ptmt.setDouble(2, product.getPreu());
            ptmt.setInt(3, idEstant);
            ptmt.setInt(4, product.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteProducte(int codi) {

        try {
            String queryString = "DELETE FROM Producte WHERE id=?";
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

    public Producte selectProducte(int id) {
        Producte product = new Producte();
        try {
            String queryString = "SELECT * FROM Producte WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                product = new Producte(resultSet.getString("Nom"),resultSet.getDouble("Preu"));
                product.setId(resultSet.getInt("id"));
                resultSet.close();
                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<Producte> llistarProducte() {
        try {
            ArrayList<Producte> Productes = new ArrayList<>();
            String queryString = "SELECT * FROM Producte";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Productes.add(selectProducte(rs.getInt("id")));
            }
            rs.close();
            return Productes;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public HashMap<String,Producte> llistarProducteEstant(int IdEstant) {
        try {
            HashMap<String,Producte> Productes = new HashMap<String,Producte>();
            String queryString = "SELECT * FROM Producte WHERE idEstant=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, IdEstant);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Producte prod = selectProducte(rs.getInt("id"));
                Productes.put(prod.getNom(),prod);
            }
            rs.close();
            return Productes;

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
