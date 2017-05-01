package examen;

import java.io.Serializable;
import java.util.Date;

public class AdministradorFiraBean implements Serializable {
    int codi;
    String nomUsuari;
    String password;

    public AdministradorFiraBean() {
    }

    public AdministradorFiraBean(int codi, String user, String password) {
        this.codi = codi;
        this.nomUsuari = user;
        this.password = password;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getnomUsuari() {
        return nomUsuari;
    }

    public void setnomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}