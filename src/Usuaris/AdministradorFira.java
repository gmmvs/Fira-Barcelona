package Usuaris;

import Espais.Estants.Estant;
import Empresa.Empresa;
import Espais.Recinte;
import Fira.Fira;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class AdministradorFira extends Usuari {


    public AdministradorFira(String nom, String user, String passwd) {
        super(nom, user, passwd);
    }

    public void addEmpresa(Empresa empresa){
        Fira.addEmpresa(empresa);
    }

    public void delEmpresa(String nom){
        Fira.delEmpresa(nom);
    }

    public void addRecinte(String nom){
        Recinte recinte = new Recinte(nom);
        Fira.addRecinte(recinte);
    }

    public void delRecinte(String nom){
        Fira.delRecinte(nom);
    }

    public void addAdministradorEmpresa( AdministradorEmpresa admin){
        Fira.addAdministrador(admin);
    }

    public void delAdministradorEmpresa(String User){
        Fira.getAdministradors().remove(User);
    }
}
