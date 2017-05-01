package examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

    public void addAdministradorFira(AdministradorFiraBean administradorFiraBean) {
        try {
            String queryString = "INSERT INTO AdministradorFira( nomUsuari, password) VALUES(?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, administradorFiraBean.getnomUsuari());
            ptmt.setString(2, administradorFiraBean.getPassword());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public void updateAdiministradorFira(AdministradorFiraBean administradorFiraBean) {

        try {
            String queryString = "UPDATE AdministradorFira SET nomUsuari=? WHERE Codi=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(2, administradorFiraBean.getnomUsuari());
            ptmt.setInt(1, administradorFiraBean.getCodi());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public void esborrar(int codi) {

        try {
            String queryString = "DELETE FROM AdministradorFira WHERE Codi=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, codi);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public void llistarAdministradorFira() {
        try {
            String queryString = "SELECT * FROM AdministradorFira";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("Codi  " + resultSet.getInt("Codi")
                        + ", nomUsuari " + resultSet.getString("nomUsuari"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
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

}

