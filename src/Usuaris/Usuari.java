package Usuaris;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */

public abstract class Usuari {

    private int id;
    private String User;
    private String Passwd;

    public Usuari() {
    }

    public Usuari(String user, String passwd) {
        User = user;
        Passwd = passwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
