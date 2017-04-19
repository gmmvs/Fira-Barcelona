package Espais.Estants;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Producte {

    private String id;
    private String Nom;

    public Producte() {
    }

    public Producte(String id, String nom) {
        this.id = id;
        Nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
