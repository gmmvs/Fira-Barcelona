package Usuaris;

import Espais.Estants.Estant;

import java.util.ArrayList;

/**
 * Created by Marc on 29/04/2017.
 */
public class Treballador extends Usuari{

    private ArrayList<Estant> RespEstant;

    public Treballador(String nom, String user, String passwd) {
        super(nom, user, passwd);
    }

    public void setRespEstant(Estant estant){
        RespEstant.add(estant);
    }
}
