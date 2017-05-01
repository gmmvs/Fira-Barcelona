package sample;

import DAO.AdministradorFiraDAO;
import Usuaris.AdministradorFira;

/**
 * Created by Marc Espinosa on 01/05/2017.
 */
public class CRUD {
    public static void main(String[] args) {
        AdministradorFiraDAO adiministradorFira = new AdministradorFiraDAO();
        AdministradorFira admin1 = new AdministradorFira();


        admin1.setUser("Guillem");
        admin1.setPasswd("hola");

        adiministradorFira.insertAdministradorFira(admin1);


        for ( AdministradorFira a:adiministradorFira.llistarAdministradorFira()) {
            System.out.println(a.getId()+" "+a.getUser()+" "+a.getPasswd());
        }


/*
        empleat.actualitzarEmpleat(empleat1);
        empleat.llistarEmpleats();
        //empleat.getTotalEmpleats();
        empleat.esborrar(1);
        empleat.esborrar(2);*/
    }
}
