package sample;

import Empresa.Empresa;
import Espais.Edifici;
import Espais.Estants.Estant;
import Espais.Planta;
import Espais.Recinte;
import Fira.Fira;
import Usuaris.AdministradorEmpresa;
import Usuaris.AdministradorFira;
import sun.security.util.Password;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Marc on 29/04/2017.
 */
public class Principal {
    private static AdministradorFira Admin = new AdministradorFira("Administrador Fira","AdminFira","1234");

    public static void main(String args[]) {
        Fira.addAdministrador(new AdministradorEmpresa("Smith","AdminEmp","1234","I-Mas"));

        int opcio = 0;
        try {
            do {
                System.out.println("Tipos Usuari: ");
                System.out.println("1. Administrador Fira");
                System.out.println("2. Administrador Empresa");
                System.out.println("3. Treballador");
                System.out.println("4. Sortir");
                Scanner entrada = new Scanner(System.in);
                opcio = entrada.nextInt();

                switch (opcio) {
                    case 1:
                            try{
                                validarAdminFira();
                            }catch (InputMismatchException ex){
                                System.out.println("Opció Erronea");
                            }
                        break;
                    case 2:
                        try{
                            validarAdminEmp();
                        }catch (InputMismatchException ex){
                            System.out.println("Opció Erronea");
                        }
                        break;
                    case 3:
                        entrada = new Scanner(System.in);
                        System.out.println("nota:");
                        String condicio = entrada.nextLine();
                        //consultarAlumnes(condicio);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("opció incorrecta");
                        break;
                }
               // menu();
            }while (opcio != 4);
        } catch (InputMismatchException ex) {
            System.out.println("Opció Erronea");
        }
    }

    public static void validarAdminFira(){
        String Usuari;
        String Contrasenya;

            System.out.println("Usuari:");
            Scanner entrada = new Scanner(System.in);
            Usuari = entrada.nextLine();

            System.out.println("Contrasenya:");
            entrada = new Scanner(System.in);
            Contrasenya = entrada.nextLine();

            if (Admin.getUser().equals(Usuari) && Admin.getPasswd().equals(Contrasenya)){
                menuAdminFira();
            }else {
                System.out.println("Error de credencials:" + Usuari + "," + Contrasenya);
            }
    }

    public static void validarAdminEmp(){
        String Usuari;
        String Contrasenya;
        int match=0;

        System.out.println("Usuari:");
        Scanner entrada = new Scanner(System.in);
        Usuari = entrada.nextLine();

        System.out.println("Contrasenya:");
        entrada = new Scanner(System.in);
        Contrasenya = entrada.nextLine();

        HashMap<String,AdministradorEmpresa> Admins = Fira.getAdministradors();
        for (String a : Admins.keySet()) {
            if (a.equals(Usuari)){
                if (Admins.get(a).getPasswd().equals(Contrasenya))
                    match = 1;
                    menuAdminEmp(Admins.get(a));
            }
        }
        if (match == 0){
        }else {
            System.out.println("Error de credencials:" + Usuari + "," + Contrasenya);
        }
    }

    public static void menuAdminFira(){
        String Nom;
        int opcio = 0;


        do {
            try {

                System.out.println("1. Crear Recinte");
                System.out.println("2. Borrar Recinte");
                System.out.println("3. Crear Edifici");
                System.out.println("4. Borrar Edifici");
                System.out.println("5. Crear Empresa");
                System.out.println("6. Borrar Empresa");
                System.out.println("7. Borrar Administrador Empresa");
                System.out.println("8. Surt");

                Scanner entrada = new Scanner(System.in);
                opcio = entrada.nextInt();

                switch (opcio) {
                    case 1:
                        System.out.println("Nom Recinte a crear:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        Admin.addRecinte(Nom);
                        break;
                    case 2:
                        System.out.println("Nom Recinte a borrar:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        Admin.delRecinte(Nom);
                        break;
                    case 3:
                        System.out.println("Recintes existent:");
                        HashMap<String, Recinte> recintes = Fira.getRecintes();
                        ArrayList<String> NRecintes = new ArrayList<>();
                        for (String a : recintes.keySet()){
                            System.out.println(a);
                            NRecintes.add(a);
                        }

                        System.out.println("Nom Recinte existent:");
                        entrada = new Scanner(System.in);
                        String nomRecinte = entrada.nextLine();
                        if (!NRecintes.contains(nomRecinte)){
                            throw new NullPointerException();
                        }

                        System.out.println("Nom Edifici a crear:");
                        entrada = new Scanner(System.in);
                        String nomEdifici = entrada.nextLine();

                        System.out.println("Numero de Plantes:");
                        entrada = new Scanner(System.in);
                        int nPlantes = entrada.nextInt();
                        ArrayList<Planta> Plantes = new ArrayList<Planta>();
                        for (int n = 0;n<nPlantes;n++){
                            System.out.println("Nº per la Planta:");
                            entrada = new Scanner(System.in);
                            int nPlanta = entrada.nextInt();

                            System.out.println("Estants per a la Planta:");
                            entrada = new Scanner(System.in);
                            int nPlantaEstants = entrada.nextInt();
                            HashMap<Integer,Estant> Estants = new HashMap<Integer,Estant>();
                            for (int j = 0; j<nPlantaEstants; j++)
                                Estants.put(j,new Estant(j));

                            Plantes.add(new Planta(nPlanta,Estants));
                        }
                        Recinte recinteSeleccionat = recintes.get(nomRecinte);
                        System.out.println("Recinte Creat");
                        recinteSeleccionat.addEdifici(new Edifici(nomEdifici,Plantes));

                        break;
                    case 4:
                        System.out.println("Recintes:");
                        HashMap<String, Recinte> Recintes = Fira.getRecintes();
                        for (String a : Recintes.keySet())
                            System.out.println(a);

                        System.out.println("Nom Recinte:");
                        entrada = new Scanner(System.in);
                        String NRecinte = entrada.nextLine();
                        Recinte recinteSel = Recintes.get(NRecinte);

                        System.out.println("Edificis:");
                        ArrayList<Edifici> Edificis = recinteSel.getEdificis();
                        for (Edifici a :
                                Edificis) {
                            System.out.println(a.getId());
                        }

                        System.out.println("Nom Edifici a eliminar:");
                        entrada = new Scanner(System.in);
                        String NEdifici = entrada.nextLine();
                        recinteSel.delEdifici(recinteSel.getEdifici(NEdifici));

                        break;
                    case 5:
                        System.out.println("Nom Empresa:");
                        entrada = new Scanner(System.in);
                        String NomEmpresa = entrada.nextLine();
                        System.out.println("Nom Administrador:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        System.out.println("Usuari Administrador:");
                        entrada = new Scanner(System.in);
                        String Usuari = entrada.nextLine();
                        System.out.println("Pass Administrador:");
                        entrada = new Scanner(System.in);
                        String Pass = entrada.nextLine();
                        ArrayList<Estant> estants = new ArrayList<Estant>();
                        Empresa empresa = new Empresa(NomEmpresa, estants);
                        Admin.addEmpresa(empresa);
                        AdministradorEmpresa AdminEmp = new AdministradorEmpresa(Nom, Usuari, Pass, NomEmpresa);
                        Admin.addAdministradorEmpresa(AdminEmp);

                        break;
                    case 6:
                        System.out.println("Nom Empresa:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        if (Admin.delEmpresa(Nom) != null) {
                            System.out.print("S'ha borrat Correctament");
                        }
                        break;
                    case 7:
                        Set<String> Keys = Fira.getAdministradors().keySet();
                        System.out.println("Usuaris: ");
                        for (String a:Keys) {
                            System.out.println(a);
                        }
                        System.out.println("Nom Usuari:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        Admin.delAdministradorEmpresa(Nom);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("opció incorrecta");
                        break;
                }
            }catch (InputMismatchException ex){
                System.out.println("opció incorrecta.");
            }catch (NullPointerException ex){
                System.out.println("No n'hi ha constancia.");
            }
        } while (opcio != 8);

    }

    public static void menuAdminEmp(AdministradorEmpresa AdminEmp){
        int opcio=0;
        do {
            try {

                System.out.println("1. Selecciona Estant");
                System.out.println("2. Consulta tots els alumnes");
                System.out.println("3. Consulta alumnes segons nota");
                System.out.println("4. Surt");
                Scanner entrada = new Scanner(System.in);
                opcio = entrada.nextInt();

                switch (opcio) {
                    case 1:
                        Set<String> Recintes = Fira.getRecintes().keySet();
                        System.out.println("Escull un Recinte:");
                        for (String a : Recintes) {
                            System.out.println(a);
                        }
                        entrada = new Scanner(System.in);
                        String Recinte = entrada.nextLine();
                        if (!Recintes.contains(Recinte))
                            throw new NullPointerException();
                        System.out.println("Escull un Nº d'Edifici:");
                        Recinte recinte = Fira.getRecintes().get(Recinte);
                        ArrayList<Edifici> Edificis = recinte.getEdificis();
                        int sEdifici = 0;
                        for (Edifici a : Edificis) {
                            System.out.println(sEdifici+". "+a.getId());
                            sEdifici++;
                        }
                        entrada = new Scanner(System.in);
                        int Edifici = entrada.nextInt();
                        System.out.println("Escull un Nº de Planta:");
                        ArrayList<Planta> Plantes = Edificis.get(Edifici).getPlantes();
                        ArrayList<Integer> b;
                        int sPlanta = 0;
                        for (Planta a : Plantes) {
                            b = a.getEstantsBuits();
                            System.out.println(sPlanta+". Planta: "+a.getNum()+" Estants Disponibles: "+b.size());
                            sPlanta++;
                        }
                        entrada = new Scanner(System.in);
                        int Planta = entrada.nextInt();
                        Planta pPlanta = Plantes.get(Planta);
                        b = pPlanta.getEstantsBuits();
                        System.out.println("Escull un Nº d'Estant:");
                        for (int a : b) {
                            System.out.println(a);
                        }
                        entrada = new Scanner(System.in);
                        int Estant = entrada.nextInt();

                        System.out.println("Nom Estant:");
                        entrada = new Scanner(System.in);
                        String nomEstant = entrada.nextLine();

                        Estant estant = pPlanta.getEstants().get(Estant);
                        estant.setEmpresa(AdminEmp.getNomEmpresa());
                        estant.setNom(nomEstant);
                        System.out.println("Estant Creat");
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("opció incorrecta");
                        break;
                }
            } catch (InputMismatchException ex){
                System.out.println("Entrada incorrecta.");
            }catch (NullPointerException ex){
                System.out.println("No n'hi ha constancia.");
            }
        } while (opcio != 4) ;
    }

    /*public static void menu() {

        int opcio;
        do {
            System.out.println("1. Afegeix alumne");
            System.out.println("2. Consulta tots els alumnes");
            System.out.println("3. Consulta alumnes segons nota");
            System.out.println("4. Surt");
            Scanner entrada = new Scanner(System.in);
            opcio = entrada.nextInt();

            switch (opcio) {
                case 1:
                    System.out.print("codi:");
                    entrada = new Scanner(System.in);
                    int codi = entrada.nextInt();
                    entrada = new Scanner(System.in);
                    System.out.print("nom:");
                    String nom = entrada.nextLine();
                    entrada = new Scanner(System.in);
                    System.out.print("nota:");
                    float nota = entrada.nextFloat();
                    afegirAlumne(codi, nom, nota);
                    break;
                case 2:
                    consultarAlumnes();
                    break;
                case 3:
                    entrada = new Scanner(System.in);
                    System.out.print("nota:");
                    String condicio = entrada.nextLine();
                    consultarAlumnes(condicio);
                    break;
                case 4:
                    conn.close();
                    break;
                default:
                    System.out.println("opció incorrecta");
                    break;
            }
        } while (opcio != 4);
    }*/
}
