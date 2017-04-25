package Fira;

import Espais.Recinte;
import Usuaris.Empresa;
import Usuaris.Usuari;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Fira {
    private String Nom;
    private Usuari Usuari;
    private HashMap<String, Empresa> Empresas;
    private ArrayList<Recinte> Recintes;

    public Fira() {
    }

    public Fira(String nom, Usuaris.Usuari usuari, HashMap<String, Empresa> empresas, ArrayList<Recinte> recintes) {
        Nom = nom;
        Usuari = usuari;
        Empresas = empresas;
        Recintes = recintes;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Usuaris.Usuari getUsuari() {
        return Usuari;
    }

    public void setUsuari(Usuaris.Usuari usuari) {
        Usuari = usuari;
    }

    public HashMap<String, Empresa> getEmpresas() {
        return Empresas;
    }

    public void setEmpresas(HashMap<String, Empresa> empresas) {
        Empresas = empresas;
    }

    public ArrayList<Recinte> getRecintes() {
        return Recintes;
    }

    public void setRecintes(ArrayList<Recinte> recintes) {
        Recintes = recintes;
    }
}
