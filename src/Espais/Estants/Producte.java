package Espais.Estants;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Producte {

    private String id;
    private String Nom;
    private Double Preu;

    public Producte() {
    }

    public Producte(String id, String nom, Double preu) {
        this.id = id;
        Nom = nom;
        this.Preu=preu;
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

    public Double getPreu() {
        return Preu;
    }

    public void setPreu(double preu) {
        Preu = preu;
    }
}
