package sample;

import DAO.AdministradorEmpresaDAO;
import DAO.AdministradorFiraDAO;
import Usuaris.AdministradorEmpresa;
import Usuaris.AdministradorFira;

/**
 * Created by Marc Espinosa on 01/05/2017.
 */
public class CRUD {
    public static void main(String[] args) {
        AdministradorFiraDAO adiministradorFira = new AdministradorFiraDAO();
        AdministradorFira admin1 = new AdministradorFira();
        AdministradorEmpresaDAO adiministradorEmp = new AdministradorEmpresaDAO();
        AdministradorEmpresa admin2 = new AdministradorEmpresa();

        admin2.setNomEmpresa("Jaop");
        admin2.setPasswd("123");
        admin2.setUser("Adminasd");
        admin1.setUser("Guillem");
        admin1.setPasswd("hola2");

        adiministradorFira.insertAdministradorFira(admin1);
        adiministradorEmp.insertAdministradorEmpresa(admin2);
        adiministradorFira.deleteAdiministradorFira(5);
        adiministradorFira.deleteAdiministradorFira(6);
        adiministradorFira.deleteAdiministradorFira(7);
        adiministradorFira.deleteAdiministradorFira(8);

        for ( AdministradorFira a:adiministradorFira.llistarAdministradorFira()) {
            System.out.println(a.getId()+" "+a.getUser()+" "+a.getPasswd());
        }

        for ( AdministradorEmpresa a: adiministradorEmp.llistarAdministradorEmpresa()) {
            System.out.println(a.getId()+" "+a.getUser()+" "+a.getPasswd()+" "+a.getNomEmpresa());
        }

        System.out.println("-----------------------------------------------------------");
        //admin2 = adiministradorEmp.llistarAdministradorEmpresa().get(adiministradorEmp.llistarAdministradorEmpresa().size()-1);
        admin2.setNomEmpresa("POAJ");
        admin2.setUser("Masdaw");
        adiministradorEmp.updateAdministradorEmpresa(admin2);

        for ( AdministradorEmpresa a: adiministradorEmp.llistarAdministradorEmpresa()) {
            System.out.println(a.getId()+" "+a.getUser()+" "+a.getPasswd()+" "+a.getNomEmpresa());
        }
        System.out.println("-----------------------------------------------------------");

        //adiministradorEmp.deleteAdiministradorEmpresa(admin2.getId());

        for ( AdministradorEmpresa a: adiministradorEmp.llistarAdministradorEmpresa()) {
            System.out.println(a.getId()+" "+a.getUser()+" "+a.getPasswd()+" "+a.getNomEmpresa());
        }
    }
}
