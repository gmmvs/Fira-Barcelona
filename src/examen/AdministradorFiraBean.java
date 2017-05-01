package examen;

import java.io.Serializable;
import java.util.Date;

public class EmpleatBean implements Serializable {
    int codi;
    String nom;
    Date dataContracte;

    public EmpleatBean() {
    }

    public EmpleatBean(int codi, String nom, Date dataContracte) {
        this.codi = codi;
        this.nom = nom;
        this.dataContracte = dataContracte;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDataContracte() {
        return dataContracte;
    }

    public void setDataContracte(Date dataContracte) {
        this.dataContracte = dataContracte;
    }
}