package DAO;

import Espais.Estants.Estant;
import Espais.Planta;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class PlantaDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public PlantaDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertPlanta(Planta planta, int idEdifici) {
        try {
            String queryString = "INSERT INTO Planta( Num, idEdifici) VALUES(?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setInt(1, planta.getNum());
            ptmt.setInt(2, idEdifici);
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                planta.setId(resultSet.getInt(1));
            }
            EstantDAO estDAO = new EstantDAO();
            for (Integer e :planta.getEstants().keySet()) {
                estDAO.insertEstant(planta.getEstants().get(e),planta.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updatePlanta(Planta planta, int idEdifici){

        try {
            String queryString = "UPDATE Planta SET Num=?, idEdifici=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, planta.getNum());
            ptmt.setInt(2, idEdifici);
            ptmt.setInt(3, planta.getId());
            ptmt.executeUpdate();
            EstantDAO estantDao = new EstantDAO();
            for (int e: planta.getEstants().keySet()){
                estantDao.updateEstantPlanta(planta.getEstants().get(e),planta.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deletePlanta(int codi) throws SQLException {

        try {
            EstantDAO estnds = new EstantDAO();
            for (int e:selectPlanta(codi).getEstants().keySet()) {
                estnds.deleteEstant(selectPlanta(codi).getEstants().get(e).getId());
            }
            String queryString = "DELETE FROM Planta WHERE id=?";
            connection = getConnection();
            connection.setAutoCommit(false);
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, codi);
            ptmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if(connection!=null)
                    connection.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
        } finally {
            closeDbConn();
        }

    }

    public Planta selectPlanta(int id) {
        Planta planta = new Planta();
        try {
            String queryString = "SELECT * FROM Planta WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                EstantDAO Estant = new EstantDAO();
                planta = new Planta(resultSet.getInt("Num"), Estant.llistarEstantsPlanta(id));
                planta.setId(id);
                resultSet.close();
                return planta;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<Planta> llistarPlantas() {
        try {
            ArrayList<Planta> plantas = new ArrayList<>();
            String queryString = "SELECT * FROM Planta";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                plantas.add(selectPlanta(rs.getInt("id")));
            }
            rs.close();
            return plantas;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public ArrayList<Planta> llistarPlantasEdifici(int idEdifici) {
        try {
            ArrayList<Planta> plantas = new ArrayList<>();
            String queryString = "SELECT * FROM Planta WHERE idEdifici=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idEdifici);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                plantas.add(selectPlanta(rs.getInt("id")));
            }
            rs.close();
            return plantas;

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
