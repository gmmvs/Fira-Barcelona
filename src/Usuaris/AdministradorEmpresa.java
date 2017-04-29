package Usuaris;

import Empresa.Empresa;
import Fira.Fira;

import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */

public class AdministradorEmpresa extends Usuari {
    String nomEmpresa;

    public AdministradorEmpresa(String nom, String user, String passwd, String nomEmpresa) {
        super(nom, user, passwd);
        this.nomEmpresa = nomEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public void addTreballador(Treballador treballador){
        HashMap<String, Empresa> empresas = Fira.getEmpresas();
        Empresa empresa = empresas.get(nomEmpresa);
        empresa.addTreballador(treballador);
    }

    public void delTreballador(Treballador treballador){
        HashMap<String, Empresa> empresas = Fira.getEmpresas();
        Empresa empresa = empresas.get(nomEmpresa);
        empresa.delTreballador(treballador);
    }

    public void selEstant(){

    }
}
