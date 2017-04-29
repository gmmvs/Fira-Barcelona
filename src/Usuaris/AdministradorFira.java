package Usuaris;

import Espais.Estants.Estant;
import Empresa.Empresa;
import Espais.Recinte;
import Fira.Fira;

import java.util.ArrayList;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class AdministradorFira extends Usuari {

    public void addEmpresa(String nom1, ArrayList<Estant> estants, ArrayList<AdministradorEmpresa> administradors){
        Empresa empresa = new Empresa(nom1,estants);
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

    public void crearAdministradorEmpresa(String nom, String user, String passwd, Empresa Empresa){
        AdministradorEmpresa AdminEmp = new AdministradorEmpresa(nom,user,passwd,Empresa);
    }

    public void delAdministradorEmpresa(){}
}
