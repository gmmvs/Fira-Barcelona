package Usuaris;

import Espais.Estants.Estant;
import Espais.Estants.Producte;

import java.util.ArrayList;

/**
 * Created by Marc on 30/04/2017.
 */
public class ImplTreballador extends Usuari implements Treballador{
    private ArrayList<Estant> RespEstant;

    public ImplTreballador() {
    }

    public ImplTreballador(String nom, String user, String passwd) {
        super(nom, user, passwd);
    }

    public void setRespEstant(Estant estant){
        RespEstant.add(estant);
    }

    public ArrayList<Estant> getRespEstant() {
        return RespEstant;
    }

    @Override
    public void sumIngres(ArrayList<Producte> Productes,String estantNom) {
        double money=0;
        for (Estant e : RespEstant) {
            if (e.getNom().equals(estantNom)) {
                for (Producte p : Productes) {
                    money+=p.getPreu();
                }
                if (money<0) {
                    e.setIngressos(e.getIngressos() + money);
                }else{
                    throw new IndexOutOfBoundsException();
                }
            }
        }
    }

    public void restIngres(ArrayList<Producte> Productes,String estantNom) {
        double money=0;
        for (Estant e : RespEstant) {
            if (e.getNom().equals(estantNom)) {
                for (Producte p : Productes) {
                    money+=p.getPreu();
                }
                if (money<e.getIngressos()) {
                    e.setIngressos(e.getIngressos() - money);
                }else{
                    throw new IndexOutOfBoundsException();
                }
            }
        }
    }
}
