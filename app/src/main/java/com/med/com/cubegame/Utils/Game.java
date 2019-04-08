package com.med.com.cubegame.Utils;


/**
 * Created by Abad Mohamed Zayd on 5/2/2018.
 */

public class Game {
    private String id;                          //a
    private String id_application;              //b
    private String id_exercice;                 //c
    private String id_niveau;                   //d
    private String date_actuelle;               //e
    private String heure_debut;                 //f
    private String heure_fin;                   //g
    private String nombre_operation_reuss;      //h
    private String nombre_operation_echou;      //i
    private String minimum_temps_operation_sec; //j
    private String moyen_temps_operation_sec;   //k
//    private String id_apprenant;
//    private String id_accompagnant;
//    private String longitude;
//    private String latitude;
//    private String device;
//    private String flag;

    public Game(){
        this.id_application = null;
        this.id_exercice = null;
        this.id_niveau = null;
        this.date_actuelle = null;
        this.heure_debut = null;
        this.heure_fin = null;
        this.nombre_operation_reuss = null;
        this.nombre_operation_echou = null;
        this.minimum_temps_operation_sec = null;
        this.moyen_temps_operation_sec = null;
//        this.id_apprenant = "";
//        this.id_accompagnant = "";
//        this.longitude = "";
//        this.latitude = "";
//        this.device = "";
//        this.flag = "";
    }
    public Game(String id_application, String id_exercice, String id_niveau, String date_actuelle, String heure_debut, String heure_fin, String nombre_operation_reuss, String nombre_operation_echou, String minimum_temps_operation_sec, String moyen_temps_operation_sec /*, String id_apprenant, String id_accompagnant, String longitude, String latitude, String device, String flag*/) {
        this.id_application = id_application;
        this.id_exercice = id_exercice;
        this.id_niveau = id_niveau;
        this.date_actuelle = date_actuelle;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.nombre_operation_reuss = nombre_operation_reuss;
        this.nombre_operation_echou = nombre_operation_echou;
        this.minimum_temps_operation_sec = minimum_temps_operation_sec;
        this.moyen_temps_operation_sec = moyen_temps_operation_sec;
//        this.id_apprenant = id_apprenant;
//        this.id_accompagnant = id_accompagnant;
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.device = device;
//        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_application() {
        return id_application;
    }

    public void setId_application(String id_application) {
        this.id_application = id_application;
    }

    public String getId_exercice() {
        return id_exercice;
    }

    public void setId_exercice(String id_exercice) {
        this.id_exercice = id_exercice;
    }

    public String getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(String id_niveau) {
        this.id_niveau = id_niveau;
    }

    public String getDate_actuelle() {
        return date_actuelle;
    }

    public void setDate_actuelle(String date_actuelle) {
        this.date_actuelle = date_actuelle;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public String getNombre_operation_reuss() {
        return nombre_operation_reuss;
    }

    public void setNombre_operation_reuss(String nombre_operation_reuss) {
        this.nombre_operation_reuss = nombre_operation_reuss;
    }

    public String getNombre_operation_echou() {
        return nombre_operation_echou;
    }

    public void setNombre_operation_echou(String nombre_operation_echou) {
        this.nombre_operation_echou = nombre_operation_echou;
    }

    public String getMinimum_temps_operation_sec() {
        return minimum_temps_operation_sec;
    }

    public void setMinimum_temps_operation_sec(String minimum_temps_operation_sec) {
        this.minimum_temps_operation_sec = minimum_temps_operation_sec;
    }

    public String getMoyen_temps_operation_sec() {
        return moyen_temps_operation_sec;
    }

    public void setMoyen_temps_operation_sec(String moyen_temps_operation_sec) {
        this.moyen_temps_operation_sec = moyen_temps_operation_sec;
    }

//
//    public String getId_apprenant() {
//        return id_apprenant;
//    }
//
//    public void setId_apprenant(String id_apprenant) {
//        this.id_apprenant = id_apprenant;
//    }
//
//    public String getId_accompagnant() {
//        return id_accompagnant;
//    }
//
//    public void setId_accompagnant(String id_accompagnant) {
//        this.id_accompagnant = id_accompagnant;
//    }
//
//    public String getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public String getDevice() {
//        return device;
//    }
//
//    public void setDevice(String device) {
//        this.device = device;
//    }
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
}
