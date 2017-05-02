package DAO;

import Espais.Edifici;
import Espais.Planta;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class EdificiDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public EdificiDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertEdifici(Edifici edifici, int idRecinte) {
        try {
            String queryString = "INSERT INTO Edifici( Nom, idRecinte) VALUES(?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, edifici.getNom());
            ptmt.setInt(2, idRecinte);
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                edifici.setId(resultSet.getInt(1));
            }
            PlantaDAO plantDao = new PlantaDAO();
            for (Planta p: edifici.getPlantes()){
                plantDao.insertPlanta(p,edifici.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateEdifici(Edifici edifici, int idRecinte){

        try {String queryString = "UPDATE Edifici SET Nom=?, idRecinte=? WHERE Id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, edifici.getNom());
            ptmt.setInt(2, idRecinte);
            ptmt.setInt(3, edifici.getId());
            ptmt.executeUpdate();
            PlantaDAO plantDao = new PlantaDAO();
            for (Planta p: edifici.getPlantes()){
                plantDao.updatePlanta(p,edifici.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteEdifici(int codi) throws SQLException {

        try {

            String queryString = "DELETE FROM Edifici WHERE Id=?";
            connection = getConnection();
            connection.setAutoCommit(false);
            PlantaDAO plantas = new PlantaDAO();
            for ( Planta planta: selectEdifici(codi).getPlantes()) {
                plantas.deletePlanta(planta.getId());
            }
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

    public Edifici selectEdifici(int id) {
        Edifici edifici = new Edifici();
        try {
            String queryString = "SELECT * FROM Edifici WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                PlantaDAO plantas = new PlantaDAO();
                edifici = new Edifici(resultSet.getString("Nom"), plantas.llistarPlantasEdifici(id));
                edifici.setId(id);
                resultSet.close();
                return edifici;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<Edifici> llistarEdificis() {
        try {
            ArrayList<Edifici> edifici = new ArrayList<>();
            String queryString = "SELECT * FROM Edifici";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                edifici.add(selectEdifici(rs.getInt("Id")));
            }
            rs.close();
            return edifici;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }

    public ArrayList<Edifici> llistarEdificiRecinte(int idRecinte) {
        try {
            ArrayList<Edifici> edifici = new ArrayList<>();
            String queryString = "SELECT * FROM Edifici WHERE idRecinte=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idRecinte);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                edifici.add(selectEdifici(rs.getInt("Id")));
            }
            rs.close();
            return edifici;

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

