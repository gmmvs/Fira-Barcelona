package Usuaris;

import Empresa.Empresa;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class AdministradorEmpresa extends Usuari {
    Empresa empresa;

    public AdministradorEmpresa(String nom, String user, String passwd, Empresa nomEmpresa) {
        super(nom, user, passwd);
        this.empresa = nomEmpresa;
    }

    public void addTreballador(Treballador treballador){
        empresa.addTreballador(treballador);
    }

    public void delTreballador(Treballador treballador){
        empresa.delTreballador(treballador);
    }

    public void selEstant(){}
}
