package sample;

import DAO.*;
import Empresa.Empresa;
import Espais.Edifici;
import Espais.Estants.Estant;
import Espais.Estants.Producte;
import Espais.Planta;
import Espais.Recinte;
import Exceptions.ExceptionNotAnOption;
import Exceptions.ExceptionNotAnUser;
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
            AdministradorFiraDAO adminFDAO = new AdministradorFiraDAO();
            adminFDAO.insertAdministradorFira(Admin);

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
        PlantaDAO plantas = new PlantaDAO();
        EdificiDAO edifici = new EdificiDAO();
        EmpresaDAO empresa = new EmpresaDAO();
        AdministradorEmpresaDAO adminEmp = new AdministradorEmpresaDAO();
        Scanner entrada;
        do {
            try {
                int match = 0;
                System.out.println("1. Crear Recinte");
                System.out.println("2. Borrar Recinte");
                System.out.println("3. Crear Edifici");
                System.out.println("4. Borrar Edifici");
                System.out.println("5. Crear Empresa");
                System.out.println("6. Borrar Empresa");
                System.out.println("7. Borrar Administrador Empresa");
                System.out.println("8. Surt");

                entrada = new Scanner(System.in);
                opcio = entrada.nextInt();


                switch (opcio) {
                    case 1:
                        System.out.println("Nom Recinte a crear:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        recinte.insertRecinte(new Recinte(Nom));
                        break;
                    case 2:
                        System.out.println("Id Recinte a borrar:");
                        for (Recinte r :recinte.llistarRecintes()) {
                            System.out.println(r.getId()+".-"+r.getNom());
                        }
                        entrada = new Scanner(System.in);
                        int oborrar = entrada.nextInt();
                        recinte.deleteRecinte(oborrar);
                        break;
                    case 3:
                        System.out.println("Recintes existents:");

                        if (recinte.llistarRecintes().size()<1)
                            throw new ExceptionNotAnOption("No es troba cap recinte.");
                        for (Recinte a : recinte.llistarRecintes()){
                            System.out.println(a.getId()+".-"+a.getNom());
                        }

                        System.out.println("Id/Número Recinte existent:");
                        entrada = new Scanner(System.in);
                        int iRecinte = entrada.nextInt();
                        for (Recinte r :recinte.llistarRecintes()) {
                            if (r.getId()==iRecinte){
                                System.out.println("Nom Edifici a crear:");
                                entrada = new Scanner(System.in);
                                String nomEdifici = entrada.nextLine();


                                System.out.println("Numero de Plantes:");
                                entrada = new Scanner(System.in);
                                int nPlantes = entrada.nextInt();
                                ArrayList<Planta> Plantes = new ArrayList<Planta>();
                                for (int n = 0;n<nPlantes;n++){
                                    System.out.println("Nº per a la Planta:");
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
                                edifici.insertEdifici(new Edifici(nomEdifici,Plantes),r.getId());
                                System.out.println("Edifici Creat");
                                match = 1;
                            }
                        }
                        if (match==0)
                            throw new ExceptionNotAnOption("No es troba el recinte.");


                        break;
                    case 4:
                        System.out.println("Recintes:");
                        for (Recinte a : recinte.llistarRecintes())
                            System.out.println(a.getNom());

                        System.out.println("Nom Recinte:");
                        entrada = new Scanner(System.in);
                        String NRecinte = entrada.nextLine();
                        int idRecinte = -1;

                        for (Recinte r:recinte.llistarRecintes()) {
                            if (r.getNom().equals(NRecinte)){
                                idRecinte = r.getId();
                            }
                        }

                        System.out.println("Edificis:");
                        ArrayList<Edifici> Edificis = edifici.llistarEdificiRecinte(idRecinte);
                        for (Edifici a : Edificis) {
                            System.out.println("Id: "+a.getId()+" Nom: "+a.getNom());
                        }

                        System.out.println("Id del Edifici a eliminar:");
                        entrada = new Scanner(System.in);
                        int iEdifici = entrada.nextInt();
                        edifici.deleteEdifici(iEdifici);

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
                        empresa.insertEmpresa(new Empresa(NomEmpresa, estants));
                        adminEmp.insertAdministradorEmpresa(new AdministradorEmpresa(Usuari, Pass, NomEmpresa));

                        break;
                    case 6:
                        match = 0;
                        System.out.println("Nom Empresa:");
                        entrada = new Scanner(System.in);
                        Nom = entrada.nextLine();
                        for (Empresa e :empresa.llistarEmpresa()) {
                            if (e.getNom().equals(Nom)){
                                empresa.deleteEmpresa(e.getId());
                                match = 1;
                            }
                        }
                        if (match == 0) {
                            throw new ExceptionNotAnOption("Aquesta empresa no existeix.");
                        }

                        break;
                    case 7:
                        System.out.println("Usuaris: ");
                        for (AdministradorEmpresa a:adminEmp.llistarAdministradorEmpresa()) {
                            System.out.println("id: "+a.getId()+" User: "+a.getUser()+" Empresa: "+a.getNomEmpresa());
                        }
                        System.out.println("Id Usuari:");
                        entrada = new Scanner(System.in);
                        int id = entrada.nextInt();
                        adminEmp.deleteAdiministradorEmpresa(id);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ExceptionNotAnOption e) {
                System.out.println(e);
            }
        } while (opcio!=8);

    }

    public static void menuAdminEmp(AdministradorEmpresa AdminEmp){
        int opcio=0;

        RecinteDAO recinte = new RecinteDAO();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        EstantDAO estantsdao = new EstantDAO();
        ProducteDAO productDAO = new ProducteDAO();
        ImplTreballadorDAO treballadorDAO = new ImplTreballadorDAO();

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

                        System.out.println("Escull un Recinte per Id:");
                        for (Recinte a : recinte.llistarRecintes()) {
                            System.out.println("id: "+a.getId()+" Nom: "+a.getNom());
                        }
                        entrada = new Scanner(System.in);
                        int idRecinte = entrada.nextInt();
                        Recinte recinte1 = null;
                        for (Recinte a : recinte.llistarRecintes()) {
                            if (a.getId()==idRecinte)
                                recinte1=a;
                        }
                        if (recinte1==null)
                            throw new NullPointerException();

                        System.out.println("Escull un Nº d'Edifici:");
                        ArrayList<Edifici> Edificis = recinte1.getEdificis();
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
                        System.out.println("Escull Nº d'estant:");
                        int nEstant = 0;
                        int idEmpresa=-1;
                        for (Empresa a:empresaDAO.llistarEmpresa()) {
                            if (a.getNom().equals(AdminEmp.getNomEmpresa())){
                                idEmpresa = a.getId();
                            }
                        }
                        for (Estant a : empresaDAO.selectEmpresa(idEmpresa).getEstants()) {
                            System.out.println(nEstant+". "+a.getNom());
                            nEstant++;
                        }
                        entrada = new Scanner(System.in);
                        int sEstant = entrada.nextInt();
                        Estant estantSel = empresaDAO.selectEmpresa(idEmpresa).getEstants().get(sEstant);
                        System.out.println("Nom Producte:");
                        entrada = new Scanner(System.in);
                        String Nom = entrada.nextLine();
                        System.out.println("Preu Producte (ex:X.XX):");
                        entrada = new Scanner(System.in);
                        double preu = entrada.nextDouble();
                        Producte prod = new Producte(Nom,preu);
                        productDAO.insertProducte(prod,estantSel.getId());
                        estantSel.addProducte(prod);
                        estantsdao.updateEstant(estantSel);
                        System.out.println("Producte Inserit");
                        break;
                    case 3:
                        System.out.println("Escull Nº d'estant:");
                        Empresa emp = null;
                        for (Empresa e:empresaDAO.llistarEmpresa()) {
                            if (e.getNom().equals(AdminEmp.getNomEmpresa())){
                                emp = e;
                            }
                        }


                        nEstant = 0;
                        for (Estant a : estantsdao.llistarEstantsEmpresa(emp.getId())) {
                            System.out.println(nEstant+". "+a.getNom());
                            nEstant++;
                        }
                        entrada = new Scanner(System.in);
                        sEstant = entrada.nextInt();
                        estantSel = estantsdao.llistarEstantsEmpresa(emp.getId()).get(nEstant);
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

                        System.out.println("Escull Nº d'estant:");

                        emp = null;
                        for (Empresa e:empresaDAO.llistarEmpresa()) {
                            if (e.getNom().equals(AdminEmp.getNomEmpresa())){
                                emp = e;
                            }
                        }

                        nEstant = 0;
                        for (Estant a : estantsdao.llistarEstantsEmpresa(emp.getId())) {
                            System.out.println(" |- "+nEstant+". "+a.getNom());
                            nEstant++;
                        }

                        entrada = new Scanner(System.in);
                        sEstant = entrada.nextInt();
                        estantSel = estantsdao.llistarEstantsEmpresa(emp.getId()).get(sEstant);
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
                        treballadorDAO.insertImplTreballador(treballador,emp.getId());
                        treballador.setRespEstant(estantSel);
                        treballadorDAO.updateImplTreballador(treballador);
                        System.out.println("Treballador Creat Correctament");
                        break;
                    case 5:
                        emp = null;
                        for (Empresa e:empresaDAO.llistarEmpresa()) {
                            if (e.getNom().equals(AdminEmp.getNomEmpresa())){
                                emp = e;
                            }
                        }
                        ArrayList<ImplTreballador> treballadors = treballadorDAO.llistarImplTreballadorEmpresa(emp.getId());
                        System.out.println("Trabajador");
                        for (ImplTreballador t : treballadors) {
                            System.out.println(t.getUser());
                        }

                        System.out.println("Usuari Treballador:");
                        entrada = new Scanner(System.in);
                        Usuari = entrada.nextLine();



                        int n1 = 0;
                        int match = -1;
                        for (ImplTreballador treballador1: treballadors) {

                            if (treballador1.getUser().equals(Usuari)){
                                match = n1;
                            }
                            n1++;
                        }
                        if (match != -1){
                            treballadorDAO.deleteImplTreballador(treballadorDAO.llistarImplTreballadorEmpresa(emp.getId()).get(match).getId());
                            System.out.println("Treballador Esborrat Correctament");
                        }else{
                            throw new ExceptionNotAnOption("Treballador No Existeix");
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
            }catch (NullPointerException | IndexOutOfBoundsException ex){
                System.out.println("No n'hi ha constancia.");
            }catch (NoSuchElementException ex){
                System.out.println("Error al ecanejar.");
            }catch (IllegalStateException ex){
                System.out.println("No es permet escanejar.");
            } catch (SQLException | ExceptionNotAnOption e) {
                e.printStackTrace();
            }
        } while (opcio != 6) ;
    }

    public static void menuTreballadors(ImplTreballador Treballador){
        int opcio = 0;
        int nEstant;

        EstantDAO estantDAo = new EstantDAO();

        do {
            try {
                System.out.println("1. Efectuar Ingres");
                System.out.println("2. Efectuar Despesa");
                System.out.println("3. Consultar Caixa");
                System.out.println("4. Surt");
                Scanner entrada = new Scanner(System.in);
                opcio = entrada.nextInt();
                ArrayList<Estant> estants = estantDAo.llistarEstantsTreballador(Treballador.getId());

                switch (opcio) {
                    case 1:
                        System.out.println("Selecciona Numero Estant: ");

                        for (int n = 0; n < estants.size(); n++) {
                            System.out.println("|-" + n + " " + estants.get(n).getNom());
                        }
                        entrada = new Scanner(System.in);
                        nEstant = entrada.nextInt();
                        Estant estant = estants.get(nEstant);
                        estant.setIngressos(estant.getIngressos() + EfectuarCompra(estant));
                        System.out.println("ingresos"+ estant.getIngressos());
                        estantDAo.updateEstant(estant);
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
                        estantDAo.updateEstant(estant);
                        break;
                    case 3:
                        System.out.println("Selecciona Numero Estant: ");

                        for (int n = 0; n < estants.size(); n++) {
                            System.out.println("|-" + n + " " + estants.get(n).getNom());
                        }
                        entrada = new Scanner(System.in);
                        nEstant = entrada.nextInt();
                        System.out.println(estants.get(nEstant).getIngressos());
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
            }catch (NoSuchElementException ex){
            System.out.println("Error al esanejar.");
            }catch (IllegalStateException ex){
            System.out.println("No es permet escanejar.");
            }catch (IndexOutOfBoundsException ex){
                System.out.println("No existeix.");
            }
        } while (opcio != 4);
    }

    public static double EfectuarCompra(Estant estant){

    String opcio;
    int q = estant.getProducte().size();
    double money = 0;

        do {
            int n = 0;
            System.out.println("Selecciona Productes:");
            for (String prod: estant.getProducte().keySet()) {
                System.out.println(prod);
                n++;
            }
            System.out.println("Exit");
            Scanner entrada = new Scanner(System.in);
            opcio = entrada.nextLine();
            if (opcio.equals("Exit")) {
                return money;
            }else {
                System.out.println(money+"+"+estant.getProducte().get(opcio).getPreu());
                money += estant.getProducte().get(opcio).getPreu();
            }


        } while (true);
    }
}
