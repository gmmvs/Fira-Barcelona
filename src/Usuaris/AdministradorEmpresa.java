package Usuaris;

import Empresa.Empresa;
import Espais.Estants.Estant;
import Espais.Estants.Producte;
import Fira.Fira;
import com.sun.istack.internal.Pool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */

public class AdministradorEmpresa extends Usuari implements Treballador{
    String nomEmpresa;

    public AdministradorEmpresa(String user, String passwd, String nomEmpresa) {
        super(user, passwd);
        this.nomEmpresa = nomEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void addTreballadors(ImplTreballador treballador) {
        Fira.getEmpresas().get(nomEmpresa).getTreballadors().add(treballador);
    }

    public void delTreballadors(String user) {
        ArrayList<ImplTreballador> treballadors = Fira.getEmpresas().get(nomEmpresa).getTreballadors();
        ImplTreballador trebBorrar = new ImplTreballador();

        for (ImplTreballador treb : treballadors) {
            if (treb.getUser().equals(user)){
                trebBorrar = treb;
            }
        }
        treballadors.remove(trebBorrar);
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public void addTreballador(ImplTreballador treballador){
        HashMap<String, Empresa> empresas = Fira.getEmpresas();
        Empresa empresa = empresas.get(nomEmpresa);
        empresa.addTreballador(treballador);
    }

    public void delTreballador(ImplTreballador treballador){
        HashMap<String, Empresa> empresas = Fira.getEmpresas();
        Empresa empresa = empresas.get(nomEmpresa);
        empresa.delTreballador(treballador);
    }

    public void selEstant(String nomEstant, Estant estant){
        estant.setEmpresa(nomEmpresa);
        estant.setNom(nomEstant);
        Fira.getEmpresas().get(nomEmpresa).getEstants().add(estant);
    }

    @Override
    public void sumIngres(ArrayList<Producte> Productes, String estantNom) {
        double money=0;
        for (Estant e : Fira.getEmpresas().get(nomEmpresa).getEstants()) {
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
        for (Estant e : Fira.getEmpresas().get(nomEmpresa).getEstants()) {
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
