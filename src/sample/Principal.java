package sample;

import DAO.AdministradorEmpresaDAO;
import DAO.AdministradorFiraDAO;
import DAO.ImplTreballadorDAO;
import DAO.RecinteDAO;
import Empresa.Empresa;
import Espais.Edifici;
import Espais.Estants.Estant;
import Espais.Estants.Producte;
import Espais.Planta;
import Espais.Recinte;
import Exceptions.WrongCredentials;
import Fira.Fira;
import Usuaris.AdministradorEmpresa;
import Usuaris.AdministradorFira;
import Usuaris.ImplTreballador;
import sun.security.util.Password;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Marc on 29/04/2017.
 */
public class Principal {
    private static AdministradorFira Admin = new AdministradorFira("AdminFira","1234");

    public static void main(String args[]) {
        Fira.addAdministrador(new AdministradorEmpresa("AdminEmp","1234","I-Mas"));

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
                        try{
                            validarTreballador();
                        }catch (InputMismatchException ex){
                            System.out.println("Opció Erronea");
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Opció incorrecta");
                        break;
                }
            }while (opcio != 4);
        } catch (InputMismatchException ex) {
            System.out.println("Opció Erronea");
        }catch (NullPointerException ex){
            System.out.println("No n'hi ha constancia.");
        }catch (NoSuchElementException ex){
            System.out.println("Error al ecanejar.");
        }catch (IllegalStateException ex){
            System.out.println("No es permet escanejar.");
        }
    }

    public static void validarAdminFira(){
        try {
            String Usuari;
            String Contrasenya;
            int match = 0;

            System.out.println("Usuari:");
            Scanner entrada = new Scanner(System.in);
            Usuari = entrada.nextLine();

            System.out.println("Contrasenya:");
            entrada = new Scanner(System.in);
            Contrasenya = entrada.nextLine();

            AdministradorFiraDAO admin = new AdministradorFiraDAO();

            for (AdministradorFira a : admin.llistarAdministradorFira()) {
                if (a.getUser().equals(Usuari) && a.getPasswd().equals(Contrasenya)) {
                    match = 1;
                    menuAdminFira();
                }
            }
            if (match == 0) {
                throw new WrongCredentials("Usuari/Contrasenya Incorrectes");
            }
        }catch (WrongCredentials ex){
            System.out.println(ex);
        }
    }

    public static void validarAdminEmp(){

        try {
            String Usuari;
            String Contrasenya;
            int match = 0;

            System.out.println("Usuari:");
            Scanner entrada = new Scanner(System.in);
            Usuari = entrada.nextLine();

            System.out.println("Contrasenya:");
            entrada = new Scanner(System.in);
            Contrasenya = entrada.nextLine();
            AdministradorEmpresaDAO admin = new AdministradorEmpresaDAO();

            for (AdministradorEmpresa a : admin.llistarAdministradorEmpresa()) {
                if (a.getUser().equals(Usuari)) {
                    if (a.getPasswd().equals(Contrasenya))
                        match = 1;
                    menuAdminEmp(a);
                }
            }
            if (match == 0) {
                throw new WrongCredentials("Usuari/Contrasenya Incorrectes");
            }
        }catch (WrongCredentials ex){
            System.out.println(ex);
        }
    }

    public static void validarTreballador(){

        try {
            String Usuari;
            String Contrasenya;
            int match = 0;

            System.out.println("Usuari:");
            Scanner entrada = new Scanner(System.in);
            Usuari = entrada.nextLine();

            System.out.println("Contrasenya:");
            entrada = new Scanner(System.in);
            Contrasenya = entrada.nextLine();

            ImplTreballadorDAO trebs = new ImplTreballadorDAO();
            for (ImplTreballador a : trebs.llistarImplTreballador()) {
                if (a.getUser().equals(Usuari) && a.getPasswd().equals(Contrasenya)) {
                    match = 1;
                    menuTreballadors(a);
                }
            }

            if (match == 0) {
                throw new WrongCredentials("Usuari/Contrasenya Incorrectes");
            }
        }catch (WrongCredentials ex){
            System.out.println(ex);
        }
    }

    public static void menuAdminFira(){

        String Nom;
        int opcio = 0;
        RecinteDAO recinte = new RecinteDAO();

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
                        //recinte.insertRecinte(new Recinte(Nom));
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
                        if (recintes.keySet().size()<1)
                            throw new NullPointerException();
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
                        AdministradorEmpresa AdminEmp = new AdministradorEmpresa(Usuari, Pass, NomEmpresa);
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
            }catch (NoSuchElementException ex){
                System.out.println("Error al ecanejar.");
            }catch (IllegalStateException ex){
                System.out.println("No es permet escanejar.");
            }
        } while (opcio != 8);

    }

    public static void menuAdminEmp(AdministradorEmpresa AdminEmp){
        int opcio=0;
        do {
            try {

                System.out.println("1. Selecciona Estant");
                System.out.println("2. Afegeix Producte");
                System.out.println("3. Esborra Producte");
                System.out.println("4. Afegeix Treballador");
                System.out.println("5. Esborra Treballador");
                System.out.println("6. Surt");
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
                        System.out.println("Escull un Nº de Planta per Numero de Llista:");
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
                        AdminEmp.selEstant(nomEstant,estant);
                        System.out.println("Estant Creat");
                        break;
                    case 2:
                        Empresa empresa = Fira.getEmpresas().get(AdminEmp.getNomEmpresa());
                        System.out.println("Escull Nº d'estant:");
                        int nEstant = 0;
                        for (Estant a : empresa.getEstants()) {
                            System.out.println(nEstant+". "+a.getNom());
                            nEstant++;
                        }
                        entrada = new Scanner(System.in);
                        int sEstant = entrada.nextInt();
                        Estant estantSel = empresa.getEstants().get(sEstant);
                        System.out.println("Nom Producte:");
                        entrada = new Scanner(System.in);
                        String Nom = entrada.nextLine();
                        System.out.println("Preu Producte (ex:X.XX):");
                        entrada = new Scanner(System.in);
                        double preu = entrada.nextDouble();
                        estantSel.addProducte(new Producte(Nom,Nom,preu));
                        System.out.println("Producte Inserit");
                        break;
                    case 3:
                        empresa = Fira.getEmpresas().get(AdminEmp.getNomEmpresa());
                        System.out.println("Escull Nº d'estant:");
                        nEstant = 0;
                        for (Estant a : empresa.getEstants()) {
                            System.out.println(" |-"+nEstant+". "+a.getNom());
                        }
                        entrada = new Scanner(System.in);
                        sEstant = entrada.nextInt();
                        estantSel = empresa.getEstants().get(sEstant);
                        System.out.println("Nom Producte:");
                        for (String a : estantSel.getProducte().keySet()) {
                            System.out.println(" |-"+a);
                        }
                        entrada = new Scanner(System.in);
                        String nProducte = entrada.nextLine();
                        if (estantSel.getProducte().containsKey(nProducte)) {
                            estantSel.delProducte(nProducte);
                            System.out.println("Producte Eliminat");
                        }else
                            System.out.println("Nom Erroni");

                        break;
                    case 4:
                        empresa = Fira.getEmpresas().get(AdminEmp.getNomEmpresa());
                        System.out.println("Escull Nº d'estant:");
                        nEstant = 0;
                        for (Estant a : empresa.getEstants()) {
                            System.out.println(" |- "+nEstant+". "+a.getNom());
                            nEstant++;
                        }
                        entrada = new Scanner(System.in);
                        sEstant = entrada.nextInt();
                        estantSel = empresa.getEstants().get(sEstant);
                        System.out.println("Nom Treballador:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        System.out.println("Usuari Treballador:");
                        entrada = new Scanner(System.in);
                        String Usuari = entrada.nextLine();
                        System.out.println("Pass Treballador:");
                        entrada = new Scanner(System.in);
                        String Pass = entrada.nextLine();
                        ImplTreballador treballador = new ImplTreballador(Usuari,Pass);
                        treballador.setRespEstant(estantSel);
                        Fira.getEmpresas().get(AdminEmp.getNomEmpresa()).getTreballadors().add(treballador);
                        System.out.println("Treballador Creat Correctament");
                        break;
                    case 5:
                        System.out.println("Usuari Treballador:");
                        entrada = new Scanner(System.in);
                        Usuari = entrada.nextLine();
                        ArrayList<ImplTreballador> treballadors = Fira.getEmpresas().get(AdminEmp.getNomEmpresa()).getTreballadors();
                        int n1 = 0;
                        int match = 0;
                        for (ImplTreballador treballador1: treballadors) {

                            if (treballador1.getUser().equals(Usuari)){
                                match = n1;
                            }
                        }
                        if (match != 0){
                            treballadors.remove(treballadors.get(n1));
                            System.out.println("Treballador Esborrat Correctament");
                        }else{
                            System.out.println("Treballador No Existeix");
                        }

                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("opció incorrecta");
                        break;
                }
            } catch (InputMismatchException ex){
                System.out.println("Entrada incorrecta.");
            }catch (NullPointerException ex){
                System.out.println("No n'hi ha constancia.");
            }catch (NoSuchElementException ex){
                System.out.println("Error al ecanejar.");
            }catch (IllegalStateException ex){
                System.out.println("No es permet escanejar.");
            }catch (IndexOutOfBoundsException ex){
                System.out.println("No n'hi ha constancia.");
            }
        } while (opcio != 6) ;
    }

    public static void menuTreballadors(ImplTreballador Treballador){
        int opcio = 0;
        int nEstant;

        do {
            try {
                System.out.println("1. Efectuar Ingres");
                System.out.println("2. Efectuar Despesa");
                System.out.println("3. Surt");
                Scanner entrada = new Scanner(System.in);
                opcio = entrada.nextInt();
                ArrayList<Estant> estants = Treballador.getRespEstant();

                switch (opcio) {
                    case 1:
                        System.out.println("Selecciona Estant: ");

                        for (int n = 0; n < estants.size(); n++) {
                            System.out.println("|-" + n + " " + estants.get(n).getNom());
                        }
                        entrada = new Scanner(System.in);
                        nEstant = entrada.nextInt();
                        Estant estant = estants.get(nEstant);
                        estant.setIngressos(estant.getIngressos() + EfectuarCompra(estant));
                        break;
                    case 2:
                        System.out.println("Selecciona Estant: ");

                        for (int n = 0; n < estants.size(); n++) {
                            System.out.println("|-" + n + " " + estants.get(n).getNom());
                        }
                        entrada = new Scanner(System.in);
                        nEstant = entrada.nextInt();
                        estant = estants.get(nEstant);
                        estant.setIngressos(estant.getIngressos() - EfectuarCompra(estant));
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("opció incorrecta");
                        break;
                }
            } catch (InputMismatchException ex){
            System.out.println("Entrada incorrecta.");
            }catch (NullPointerException ex){
            System.out.println("No n'hi ha constancia.");
            }catch (NoSuchElementException ex){
            System.out.println("Error al esanejar.");
            }catch (IllegalStateException ex){
            System.out.println("No es permet escanejar.");
            }
        } while (opcio != 3);

    }

    public static double EfectuarCompra(Estant estant){

    int opcio = 0;
    int q = estant.getProducte().size();
    double money = 0;
    ArrayList<String> productes;

        do {
            int n = 0;
            System.out.println("Selecciona Productes:");
            productes = new ArrayList<String>();
            for (String prod: estant.getProducte().keySet()) {
                System.out.println("|- "+n+". "+prod);
                productes.add(prod);
                n++;
            }
            System.out.println("-"+n+"Nar A Caixa.");
            Scanner entrada = new Scanner(System.in);
            opcio = entrada.nextInt();
            money+=estant.getProducte().get(productes.get(n)).getPreu();
        } while (opcio != q);

        return money;
    }
}
