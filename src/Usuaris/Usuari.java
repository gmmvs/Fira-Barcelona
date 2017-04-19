package Usuaris;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */

public abstract class Usuari {

    private String Nom;
    private String User;
    private String Passwd;

    public Usuari() {
    }

    public Usuari(String nom, String user, String passwd) {
        Nom = nom;
        User = user;
        Passwd = passwd;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }
}
