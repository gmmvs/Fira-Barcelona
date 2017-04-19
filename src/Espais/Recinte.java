package Espais;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Recinte {

    private String Nom;
    private ArrayList<Edifici> Edificis;

    public Recinte() {
    }

    public Recinte(String nom) {
        Nom = nom;
    }

    public Recinte(String nom, ArrayList<Edifici> edificis) {
        Nom = nom;
        Edificis = edificis;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public ArrayList<Edifici> getEdificis() {
        return Edificis;
    }

    public void setEdificis(ArrayList<Edifici> edificis) {
        Edificis = edificis;
    }
}
