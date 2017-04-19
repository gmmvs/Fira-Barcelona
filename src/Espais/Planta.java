package Espais;

import Espais.Estants.Estant;

import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Planta {

    private int Num;
    private HashMap<Integer,Estant> Estants;

    public Planta() {
    }

    public Planta(int num) {
        Num = num;
    }

    public Planta(int num, HashMap<Integer, Estant> estants) {
        Num = num;
        Estants = estants;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public HashMap<Integer, Estant> getEstants() {
        return Estants;
    }

    public void setEstants(HashMap<Integer, Estant> estants) {
        Estants = estants;
    }

    public void createEstant(){}
}
