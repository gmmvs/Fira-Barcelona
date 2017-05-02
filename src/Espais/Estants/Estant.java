package Espais.Estants;

import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Estant {

    private int id;
    private String Nom;
    private String Empresa;
    private HashMap<String,Producte> Productes = new HashMap<String,Producte>();
    private double Ingressos = 0.00;

    public Estant() {
    }

    public Estant(int id) {
        this.id = id;
    }

    public Estant(int id, String nom, String empresa, HashMap<String, Espais.Estants.Producte> producte, double ingressos) {
        this.id = id;
        Nom = nom;
        Empresa = empresa;
        Productes = producte;
        Ingressos = ingressos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public HashMap<String, Espais.Estants.Producte> getProducte() {
        return Productes;
    }

    public void setProducte(HashMap<String, Espais.Estants.Producte> producte) {
        Productes = producte;
    }

    public double getIngressos() {
        return Ingressos;
    }

    public void setIngressos(double ingressos) {
        Ingressos = ingressos;
    }

    public void addProducte(Producte producte){
        Productes.put(producte.getNom(),producte);
    }

    public void delProducte(String name){
        Productes.remove(name);
    }
}
