package Espais;

import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Edifici {

    private String id;
    private ArrayList<Planta> Plantes;

    public Edifici() {
    }

    public Edifici(String id) {
        this.id = id;
    }

    public Edifici(String id, ArrayList<Planta> plantes) {
        this.id = id;
        Plantes = plantes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Planta> getPlantes() {
        return Plantes;
    }

    public void setPlantes(ArrayList<Planta> plantes) {
        Plantes = plantes;
    }

    public void addPlanta(){}
}
