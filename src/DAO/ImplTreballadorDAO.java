package DAO;

import Espais.Estants.Estant;
import Exceptions.ExceptionNotAnUser;
import Usuaris.AdministradorEmpresa;
import Usuaris.ImplTreballador;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class ImplTreballadorDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public ImplTreballadorDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertImplTreballador(ImplTreballador treballador, int idEmpresa) throws SQLException{
        try {
            String queryString = "INSERT INTO Treballador( Usuari, Passwd, idEmpresa) VALUES(?,?,?)";
            connection = getConnection();
            connection.setAutoCommit(false);
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, treballador.getUser());
            ptmt.setString(2, treballador.getPasswd());
            ptmt.setInt(3, idEmpresa);
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                treballador.setId(resultSet.getInt(1));
                EstantDAO estants = new EstantDAO();
                for (Estant e:treballador.getRespEstant()){
                    estants.updateEstantTreballador(e,treballador.getId());
                }
            }
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

    public void updateImplTreballador(ImplTreballador treballador) throws SQLException {

        try {
            String queryString = "UPDATE Treballador SET Usuari=?, Passwd =? WHERE id=?";
            connection = getConnection();
            connection.setAutoCommit(false);
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, treballador.getUser());
            ptmt.setString(2, treballador.getPasswd());
            ptmt.setInt(3, treballador.getId());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            connection.commit();
            EstantDAO estants = new EstantDAO();
            for (Estant e:treballador.getRespEstant()){
                estants.updateEstantTreballador(e,treballador.getId());
            }
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

    public void deleteImplTreballador(int codi) {

        try {
            String queryString = "DELETE FROM Treballador WHERE id=?";
            EstantDAO estants = new EstantDAO();
            for (Estant e:selectImplTreballador(codi).getRespEstant()){
                estants.updateEstantTreballador(e, Integer.parseInt(null));
            }
            connection = getConnection();
            connection.setAutoCommit(false);
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, codi);
            ptmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public ImplTreballador selectImplTreballador(int id) {
        ImplTreballador admin = new ImplTreballador();
        try {
            String queryString = "SELECT * FROM Treballador WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                EstantDAO estants = new EstantDAO();
                admin = new ImplTreballador(resultSet.getString("Usuari"),resultSet.getString("Passwd"),estants.llistarEstantsTreballador(resultSet.getInt("id")));
                admin.setId(resultSet.getInt("id"));
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


    public ArrayList<ImplTreballador> llistarImplTreballador() {
        try {
            ArrayList<ImplTreballador> Administradors = new ArrayList<>();
            String queryString = "SELECT * FROM Treballador";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Administradors.add(selectImplTreballador(rs.getInt("Id")));
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

    public ArrayList<ImplTreballador> llistarImplTreballadorEmpresa(int idEmpresa) {
        try {
            ArrayList<ImplTreballador> Administradors = new ArrayList<>();
            String queryString = "SELECT * FROM Treballador WHERE idEmpresa =?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1,idEmpresa);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Administradors.add(selectImplTreballador(rs.getInt("Id")));
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
