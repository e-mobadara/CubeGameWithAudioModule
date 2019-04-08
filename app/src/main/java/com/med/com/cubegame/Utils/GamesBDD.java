package com.med.com.cubegame.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Abad Mohamed Zayd on 5/3/2018.
 */

public class GamesBDD {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Game";

    // Table infos
    private static final String TABLE_GAME = "Game_Infos";
    private static final String a = "id";
    private static final int num_a = 0;
    private static final String b = "id_application";
    private static final int num_b = 1;
    private static final String c = "id_exercice";
    private static final int num_c = 2;
    private static final String d = "id_niveau";
    private static final int num_d = 3;
    private static final String e = "date_actuelle";
    private static final int num_e = 4;
    private static final String f = "heure_debut";
    private static final int num_f = 5;
    private static final String g = "heure_fin";
    private static final int num_g = 6;
    private static final String h = "Nombre_operation_reuss";
    private static final int num_h = 7;
    private static final String i = "Nombre_operation_echou";
    private static final int num_i = 8;
    private static final String j = "minimum_temps_operation_sec";
    private static final int num_j = 9;
    private static final String k = "moyen_temps_operation_sec";
    private static final int num_k = 10;
//    private static final String l = "id_apprenant";
//    private static final int num_l = 11;
//    private static final String m = "id_accompagnant";
//    private static final int num_m = 12;
//    private static final String n = "longitude";
//    private static final int num_n = 13;
//    private static final String o = "latitude";
//    private static final int num_o = 14;
//    private static final String p = "device";
//    private static final int num_p = 15;
//    private static final String q = "flag";
//    private static final int num_q = 16;

    private SQLiteDatabase bdd;

    private DatabaseHelper databaseHelper;

    public GamesBDD(Context context){
        databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        bdd = databaseHelper.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public long insertGameInfo(Game game){
        //Création d'un ContentValues.
        ContentValues values = new ContentValues();
        //Ajout des valeurs associés à une clé.
        values.put(b, game.getId_application());
        values.put(c, game.getId_exercice());
        values.put(d, game.getId_niveau());
        values.put(e, game.getDate_actuelle());
        values.put(f, game.getHeure_debut());
        values.put(g, game.getHeure_fin());
        values.put(h, game.getNombre_operation_reuss());
        values.put(i, game.getNombre_operation_echou());
        values.put(j, game.getMinimum_temps_operation_sec());
        values.put(k, game.getMoyen_temps_operation_sec());
/*      values.put(l, game.getId_apprenant());
        values.put(m, game.getId_accompagnant());
        values.put(n, game.getLongitude());
        values.put(o, game.getLatitude());
        values.put(p, game.getDevice());
        values.put(q, game.getFlag());
        */
        //Insert statement
        return bdd.insert(TABLE_GAME, null, values);
    }

    public int updateGame(int id, Game game){
        ContentValues values = new ContentValues();
        values.put(b, game.getId_application());
        values.put(c, game.getId_exercice());
        values.put(d, game.getId_niveau());
        values.put(e, game.getDate_actuelle());
        values.put(f, game.getHeure_debut());
        values.put(g, game.getHeure_fin());
        values.put(h, game.getNombre_operation_reuss());
        values.put(i, game.getNombre_operation_echou());
        values.put(j, game.getMinimum_temps_operation_sec());
        values.put(k, game.getMoyen_temps_operation_sec());
/*      values.put(l, game.getId_apprenant());
        values.put(m, game.getId_accompagnant());
        values.put(n, game.getLongitude());
        values.put(o, game.getLatitude());
        values.put(p, game.getDevice());
        values.put(q, game.getFlag());
        */
        //Update Statement
        return bdd.update(TABLE_GAME, values, a + " = " + id, null);
    }

    public int removeGameWithId(int id){
        //Delete Statement
        return bdd.delete(TABLE_GAME, a +"="+ id, null);
    }

    //Do not forget to add other variables
    public Game getGameByIdExercice(String id_exercice){
        Cursor cursor = bdd.query(TABLE_GAME, new String[] {a, b, c, d, e, f, g, h, i, j, k}, c + " LIKE \"" + id_exercice +" \"", null, null, null, null);
        return cursorToGame(cursor);
    }

    //Do not forget to add other variables
    public Game getGameByIdApplication(String id_application){
        Cursor cursor = bdd.query(TABLE_GAME, new String[] {a, b, c, d, e, f, g, h, i, j, k}, b + " LIKE \"" + id_application +" \"", null, null, null, null);
        return cursorToGame(cursor);
    }

    private Game cursorToGame(Cursor cursor){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (cursor.getCount() == 0)
            return null;

        //Sinon, on se place sur le premier élément :
        cursor.moveToFirst();

        //On crée un game
        Game game = new Game();
        game.setId(cursor.getString(num_a));
        game.setId_application(cursor.getString(num_b));
        game.setId_exercice(cursor.getString(num_c));
        game.setId_niveau(cursor.getString(num_d));
        game.setDate_actuelle(cursor.getString(num_e));
        game.setHeure_debut(cursor.getString(num_f));
        game.setHeure_fin(cursor.getString(num_g));
        game.setNombre_operation_reuss(cursor.getString(num_h));
        game.setNombre_operation_echou(cursor.getString(num_i));
        game.setMinimum_temps_operation_sec(cursor.getString(num_j));
        game.setMoyen_temps_operation_sec(cursor.getString(num_k));
/*      game.setId_apprenant(c.getString(num_l));
        game.setId_accompagnant(c.getString(num_m));
        game.setLongitude(c.getString(num_n));
        game.setLatitude(c.getString(num_o));
        game.setDevice(c.getString(num_p));
        game.setFlag(c.getString(num_q));
*/
        //On ferme le cursor
        cursor.close();

        //on retourne Game
        return game;
    }
}
































