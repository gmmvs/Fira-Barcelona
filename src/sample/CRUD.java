package sample;

import examen.AdministradorFiraBean;
import examen.AdministradorFiraDAO;

public class CRUD {
    public static void main(String[] args) {
        examen.AdministradorFiraDAO adiministradorFira = new AdministradorFiraDAO();
        AdministradorFiraBean admin1 = new AdministradorFiraBean();


        admin1.setnomUsuari("Guillem");
        admin1.setPassword("hola");

        adiministradorFira.addAdministradorFira(admin1);


        adiministradorFira.llistarAdministradorFira();


/*
        empleat.actualitzarEmpleat(empleat1);
        empleat.llistarEmpleats();
        //empleat.getTotalEmpleats();
        empleat.esborrar(1);
        empleat.esborrar(2);*/
    }
}