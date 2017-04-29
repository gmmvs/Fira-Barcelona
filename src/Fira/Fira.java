package Fira;

import Espais.Recinte;
import Empresa.Empresa;
import Usuaris.AdministradorEmpresa;
import Usuaris.Usuari;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marc Espinosa on 19/04/2017.
 */
public class Fira {
    private static String Nom;
    private static Usuari Usuari;
    private static HashMap<String,Empresa> Empresas = new HashMap<String, Empresa>();
    private static HashMap<String,Recinte> Recintes = new HashMap<String, Recinte>();
    private static HashMap<String,AdministradorEmpresa> AdministradorsEmpresa = new HashMap<String, AdministradorEmpresa>();

    public Fira() {
    }

    public Fira(String nom, Usuaris.Usuari usuari, HashMap<String, Empresa> empresas, HashMap<String,Recinte> recintes) {
        Nom = nom;
        Usuari = usuari;
        Empresas = empresas;
        Recintes = recintes;
    }

    public static String getNom() {
        return Nom;
    }

    public static void setNom(String nom) {
        Nom = nom;
    }

    public static Usuaris.Usuari getUsuari() {
        return Usuari;
    }

    public static void setUsuari(Usuaris.Usuari usuari) {
        Usuari = usuari;
    }

    public static HashMap<String, Empresa> getEmpresas() {
        return Empresas;
    }

    public static void setEmpresas(HashMap<String, Empresa> empresas) {
        Empresas = empresas;
    }

    public static HashMap<String,Recinte> getRecintes() {
        return Recintes;
    }

    public static void setRecintes(HashMap<String,Recinte> recintes) {
        Recintes = recintes;
    }

    public static HashMap<String,AdministradorEmpresa> getAdministradors() {
        return AdministradorsEmpresa;
    }

    public static void setAdministradors(HashMap<String,AdministradorEmpresa> administradors) {
        AdministradorsEmpresa = administradors;
    }

    public static void addAdministrador(AdministradorEmpresa administrador) {
        AdministradorsEmpresa.put(administrador.getUser(),administrador);
    }

    public static void addEmpresa(Empresa empresa){
        Empresas.put(empresa.getNom(), empresa);
    }

    public static Empresa delEmpresa(String nom){
        return Empresas.remove(nom);
    }

    public static void delRecinte(String nom){
        Recintes.remove(nom);
    }

    public static void addRecinte(Recinte recinte){
        Recintes.put(recinte.getNom(),recinte);
    }

}
