package examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmpleatDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public EmpleatDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DAOFactory.getInstance().getConnection();
        return conn;
    }

    public void crearEmpleat(EmpleatBean empleatBean) {
        try {
            String queryString = "INSERT INTO empleat(Codi, Nom, dataContracte) VALUES(?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, empleatBean.getCodi());
            ptmt.setString(2, empleatBean.getNom());
            ptmt.setDate(3, (java.sql.Date) empleatBean.getDataContracte());
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

    public void actualitzarEmpleat(EmpleatBean empleatBean) {

        try {
            String queryString = "UPDATE empleat SET Nom=? WHERE Codi=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, empleatBean.getNom());
            ptmt.setInt(2, empleatBean.getCodi());
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
            String queryString = "DELETE FROM empleat WHERE Codi=?";
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

    public void llistarEmpleats() {
        try {
            String queryString = "SELECT * FROM empleat";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("Codi  " + resultSet.getInt("Codi")
                        + ", Nom " + resultSet.getString("Nom"));
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

    public int getTotalEmpleats() {
        int numeroEmpleats = 0;
        try {
            String queryString = "SELECT DISTINCT COUNT(*) FROM empleat";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();

            numeroEmpleats = resultSet.getInt("Codi");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(numeroEmpleats);
        return numeroEmpleats;
    }
}

