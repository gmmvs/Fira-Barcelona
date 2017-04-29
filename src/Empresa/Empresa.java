package Empresa;

import Espais.Estants.Estant;
import Usuaris.AdministradorEmpresa;
import Usuaris.Treballador;
import Usuaris.Usuari;

import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Empresa{
    private String Nom;
    private ArrayList<Estant> Estants;
    private ArrayList<Treballador> Treballadors;

    public Empresa() {
    }

    public Empresa(String nom1, ArrayList<Estant> estants) {
        Nom = nom1;
        Estants = estants;
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

    public void addTreballador(Treballador treballador){
        Treballadors.add(treballador);
    }

    public void delTreballador(Treballador treballador){
        Treballadors.remove(treballador);
    }

}
