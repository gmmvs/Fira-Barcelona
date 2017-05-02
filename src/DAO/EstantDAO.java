package DAO;

import Espais.Estants.Estant;
import Espais.Estants.Producte;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class EstantDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public EstantDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertEstant(Estant estant, int idPlanta) {
        try {
            String queryString = "INSERT INTO Estant( Nom, NomEmpresa, Ingressos, idPlanta) VALUES(?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, estant.getNom());
            ptmt.setString(2, estant.getEmpresa());
            ptmt.setDouble(3, estant.getIngressos());
            ptmt.setInt(4, idPlanta);
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                estant.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateEstantTreballador(Estant estant, int idTreballador){

        try {
            String queryString = "UPDATE Estant SET Nom=?, NomEmpresa=?, Ingressos=?, idTreballador=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, estant.getNom());
            ptmt.setString(2, estant.getEmpresa());
            ptmt.setDouble(3, estant.getIngressos());
            ptmt.setInt(4, idTreballador);
            ptmt.setInt(5, estant.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void updateEstantPlanta(Estant estant, int idPlanta){

        try {
            String queryString = "UPDATE Estant SET Nom=?, NomEmpresa=?, Ingressos=?, idPlanta =? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, estant.getNom());
            ptmt.setString(2, estant.getEmpresa());
            ptmt.setDouble(3, estant.getIngressos());
            ptmt.setDouble(4, idPlanta);
            ptmt.setInt(5, estant.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void updateEstantEmpresa(Estant estant, int idEmpresa){

        try {
            String queryString = "UPDATE Estant SET Nom=?, NomEmpresa=?, Ingressos=?, idEmpresa =? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, estant.getNom());
            ptmt.setString(2, estant.getEmpresa());
            ptmt.setDouble(3, estant.getIngressos());
            ptmt.setDouble(4, idEmpresa);
            ptmt.setInt(5, estant.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void updateEstant(Estant estant){

        try {
            String queryString = "UPDATE Estant SET Nom=?, NomEmpresa=?, Ingressos=? WHERE id=?";
            connection = getConnection();
            ProducteDAO prods = new ProducteDAO();
            for (String e:estant.getProducte().keySet()) {
                prods.updateProducte(estant.getProducte().get(e),estant.getId());
            }
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, estant.getNom());
            ptmt.setString(2, estant.getEmpresa());
            ptmt.setDouble(3, estant.getIngressos());
            ptmt.setInt(4, estant.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteEstant(int codi) {

        try {
            ProducteDAO prods = new ProducteDAO();
            for (String e:selectEstant(codi).getProducte().keySet()) {
                prods.deleteProducte(selectEstant(codi).getProducte().get(e).getId());
            }

            String queryString = "DELETE FROM Estant WHERE id=?";
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

    public Estant selectEstant(int id) {
        Estant estant = new Estant();
        try {
            String queryString = "SELECT * FROM Estant WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                ProducteDAO prodEstant = new ProducteDAO();
                estant = new Estant(resultSet.getInt("id"),resultSet.getString("Nom"),resultSet.getString("NomEmpresa"), prodEstant.llistarProducteEstant(resultSet.getInt("id")),resultSet.getDouble("Ingressos"));
                estant.setId(resultSet.getInt("id"));
                resultSet.close();
                return estant;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<Estant> llistarEstants() {
        try {
            ArrayList<Estant> estants = new ArrayList<>();
            String queryString = "SELECT * FROM Estant";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                estants.add(selectEstant(rs.getInt("Id")));
            }
            rs.close();
            return estants;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public ArrayList<Estant> llistarEstantsTreballador(int idTreballador) {
        try {
            ArrayList<Estant> estants = new ArrayList<>();
            String queryString = "SELECT * FROM Estant WHERE idTreballador=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idTreballador);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                estants.add(selectEstant(rs.getInt("Id")));
            }
            rs.close();
            return estants;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public ArrayList<Estant> llistarEstantsEmpresa(int idEmpresa) {
        try {
            ArrayList<Estant> estants = new ArrayList<>();
            String queryString = "SELECT * FROM Estant WHERE idEmpresa=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idEmpresa);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                estants.add(selectEstant(rs.getInt("Id")));
            }
            rs.close();
            return estants;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public HashMap<Integer,Estant> llistarEstantsPlanta(int idPlanta) {
        try {
            HashMap<Integer,Estant> estants = new HashMap<Integer,Estant>();
            String queryString = "SELECT * FROM Estant WHERE idPlanta=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idPlanta);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                estants.put(rs.getInt("Id"),selectEstant(rs.getInt("Id")));
            }
            rs.close();
            return estants;

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
