package Espais;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Recinte {

    private String Nom;
    private ArrayList<Edifici> Edificis =new ArrayList<>();

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

    public void addEdifici(Edifici edifici){
        Edificis.add(edifici);
    }

    public void delEdifici(Edifici edifici){
        Edificis.remove(edifici);
    }

    public Edifici getEdifici(String id){
        for (Edifici a :
                Edificis) {
            if (a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }
}
