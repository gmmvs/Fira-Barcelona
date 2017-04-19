package Espais.Estants;

import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Estant {

    private int id;
    private String Nom;
    private String Empresa;
    private HashMap<String,Producte> Producte;
    private int Ingressos;

    public Estant() {
    }

    public Estant(int id, String nom, String empresa, HashMap<String, Espais.Estants.Producte> producte, int ingressos) {
        this.id = id;
        Nom = nom;
        Empresa = empresa;
        Producte = producte;
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
        return Producte;
    }

    public void setProducte(HashMap<String, Espais.Estants.Producte> producte) {
        Producte = producte;
    }

    public int getIngressos() {
        return Ingressos;
    }

    public void setIngressos(int ingressos) {
        Ingressos = ingressos;
    }

    public void addProducte(){}

    public void delProducte(){}
}