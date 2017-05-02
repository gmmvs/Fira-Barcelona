package Usuaris;

import Espais.Estants.Estant;
import Espais.Estants.Producte;

import java.util.ArrayList;

/**
 * Created by Marc on 29/04/2017.
 */
public interface Treballador{

    public void sumIngres(ArrayList<Producte> Productes,String estantNom);

    public void restIngres(ArrayList<Producte> Productes,String estantNom);

}
