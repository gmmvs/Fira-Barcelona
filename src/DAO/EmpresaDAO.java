package DAO;

import Empresa.Empresa;
import Espais.Estants.Estant;
import Usuaris.ImplTreballador;
import Usuaris.Treballador;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class EmpresaDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public EmpresaDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void insertEmpresa(Empresa empresa) {
        try {
            String queryString = "INSERT INTO Empresa(Nom) VALUES(?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, empresa.getNom());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()){
                empresa.setId(resultSet.getInt(1));
            }
            EstantDAO estantDao = new EstantDAO();
            for (Estant p: empresa.getEstants()){
                estantDao.updateEstantEmpresa(p,empresa.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public void updateEmpresa(Empresa empresa){

        try {String queryString = "UPDATE Empresa SET Nom=? WHERE Id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, empresa.getNom());
            ptmt.setInt(2, empresa.getId());
            ptmt.executeUpdate();
            EstantDAO estDAO = new EstantDAO();
            for (Estant p: empresa.getEstants()){
                estDAO.updateEstantEmpresa(p,empresa.getId());
            }
            ImplTreballadorDAO trebDAO = new ImplTreballadorDAO();
            for (ImplTreballador t: empresa.getTreballadors()){
                trebDAO.updateImplTreballador(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
    }

    public void deleteEmpresa(int codi) throws SQLException {

        try {

            String queryString = "DELETE FROM Empresa WHERE Id=?";
            connection = getConnection();
            connection.setAutoCommit(false);
            ImplTreballadorDAO trebsDAO = new ImplTreballadorDAO();
            for ( ImplTreballador t: selectEmpresa(codi).getTreballadors()) {
                trebsDAO.deleteImplTreballador(t.getId());
            }
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, codi);
            ptmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            closeDbConn();
        }

    }

    public Empresa selectEmpresa(int id) {
        Empresa empresa = new Empresa();
        try {
            String queryString = "SELECT * FROM Empresa WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeQuery();
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                ImplTreballadorDAO treb = new ImplTreballadorDAO();
                EstantDAO est = new EstantDAO();
                empresa = new Empresa(resultSet.getString("Nom"),est.llistarEstantsEmpresa(id),treb.llistarImplTreballadorEmpresa(id));
                empresa.setId(id);
                resultSet.close();
                return empresa;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbConn();
        }
        return null;
    }


    public ArrayList<Empresa> llistarEmpresa() {
        try {
            ArrayList<Empresa> empresa = new ArrayList<>();
            String queryString = "SELECT * FROM Empresa";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                empresa.add(selectEmpresa(rs.getInt("Id")));
            }
            rs.close();
            return empresa;

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

