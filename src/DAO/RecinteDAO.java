package DAO;

import Espais.Edifici;
import Espais.Recinte;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class RecinteDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public RecinteDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertRecinte(Recinte recinte) {
        try {
            String queryString = "INSERT INTO Recinte( Nom) VALUES(?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, recinte.getNom());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                recinte.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateRecinte(Recinte recinte){

        try {String queryString = "UPDATE Recinte SET Nom=? WHERE Id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, recinte.getNom());
            ptmt.setInt(2, recinte.getId());
            ptmt.executeUpdate();
            EdificiDAO edifDAO = new EdificiDAO();
            for (Edifici e: recinte.getEdificis()){
                edifDAO.updateEdifici(e,recinte.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteRecinte(int codi) throws SQLException {

        try {

            String queryString = "DELETE FROM Recinte WHERE Id=?";
            EdificiDAO edifDAO = new EdificiDAO();
            for (Edifici e: selectRecinte(codi).getEdificis()){
                edifDAO.updateEdifici(e,codi);
            }
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

    public Recinte selectRecinte(int id) {
        Recinte recinte = new Recinte();
        try {
            String queryString = "SELECT * FROM Recinte WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                EdificiDAO edifDAO = new EdificiDAO();
                recinte = new Recinte(resultSet.getString("Nom"), edifDAO.llistarEdificiRecinte(id));
                recinte.setId(id);
                resultSet.close();
                return recinte;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<Recinte> llistarRecintes() {
        try {
            ArrayList<Recinte> recintes = new ArrayList<>();
            String queryString = "SELECT * FROM Recinte";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                recintes.add(selectRecinte(rs.getInt("Id")));
            }
            rs.close();
            return recintes;

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
