package Empresa;

import Espais.Estants.Estant;
import Usuaris.AdministradorEmpresa;
import Usuaris.ImplTreballador;
import Usuaris.Treballador;
import Usuaris.Usuari;

import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */

public class Empresa{
    private String Nom;
    private ArrayList<Estant> Estants = new ArrayList<Estant>();
    private ArrayList<ImplTreballador> Treballadors = new ArrayList<ImplTreballador>();

    public Empresa() {
    }

    public Empresa(String nom1, ArrayList<Estant> estants) {
        Nom = nom1;
        Estants = estants;
    }

    public ArrayList<ImplTreballador> getTreballadors() {
        return Treballadors;
    }

    public void setTreballadors(ArrayList<ImplTreballador> treballadors) {
        Treballadors = treballadors;
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

    public void addTreballador(ImplTreballador treballador){
        Treballadors.add(treballador);
    }

    public void delTreballador(ImplTreballador treballador){
        Treballadors.remove(treballador);
    }

}
