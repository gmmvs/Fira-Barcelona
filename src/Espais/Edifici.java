package Espais;

import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Edifici {

    private int id;
    private String Nom;
    private ArrayList<Planta> Plantes;

    public Edifici() {
    }

    public Edifici(String nom) {
        this.Nom = nom;
    }

    public Edifici(String Nom, ArrayList<Planta> plantes) {
        this.Nom = Nom;
        Plantes = plantes;
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

    public ArrayList<Planta> getPlantes() {
        return Plantes;
    }

    public void setPlantes(ArrayList<Planta> plantes) {
        Plantes = plantes;
    }

    public void addPlanta(Planta planta){
        Plantes.add(planta);
    }
}
