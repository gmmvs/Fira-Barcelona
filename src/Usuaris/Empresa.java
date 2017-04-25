package Usuaris;

import Espais.Estants.Estant;

import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Empresa {
    private String Nom;
    private ArrayList<Estant> Estants;
    private ArrayList<String> Administradors;

    public Empresa() {
    }

    public Empresa(String nom, ArrayList<Estant> estants, ArrayList<String> administradors) {
        Nom = nom;
        Estants = estants;
        Administradors = administradors;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public ArrayList<Estant> getEstants() {
        return Estants;
    }

    public void setEstants(ArrayList<Estant> estants) {
        Estants = estants;
    }

    public ArrayList<String> getAdministradors() {
        return Administradors;
    }

    public void setAdministradors(ArrayList<String> administradors) {
        Administradors = administradors;
    }
}
