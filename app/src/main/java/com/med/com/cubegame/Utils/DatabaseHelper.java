package com.med.com.cubegame.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abad Mohamed Zayd on 5/3/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table name
    private static final String TABLE_GAME = "Game_Infos";

    // Table Columns names
    private static final String a = "id";
    private static final String b = "id_application";
    private static final String c = "id_exercice";
    private static final String d = "id_niveau";
    private static final String e = "date_actuelle";
    private static final String f = "heure_debut";
    private static final String g = "heure_fin";
    private static final String h = "Nombre_operation_reuss";
    private static final String i = "Nombre_operation_echou";
    private static final String j = "minimum_temps_operation_sec";
    private static final String k = "moyen_temps_operation_sec";
//    private static final String l="id_apprenant";
//    private static final String m="id_accompagnant";
//    private static final String n="longitude";
//    private static final String o="latitude";
//    private static final String p="device";
//    private static final String q="flag";


    private static final String CREATE_DB = "CREATE TABLE "+TABLE_GAME+ " ("+
            a + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            b + " TEXT, "+
            c  + " TEXT, "+
            d + " TEXT, "+
            e + " TEXT, "+
            f + " TEXT, "+
            g + " TEXT, "+
            h + " TEXT, "+
            i + " TEXT, "+
            j + " TEXT, "+
            k  + " TEXT); ";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_GAME + ";");
        onCreate(sqLiteDatabase);
    }
}
