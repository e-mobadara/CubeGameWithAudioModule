package com.med.com.cubegame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.med.com.cubegame.Levels.Level1;
import com.med.com.cubegame.Levels.Level2;
import com.med.com.cubegame.Levels.Level3;
import com.med.com.cubegame.Levels.Level4;
import com.med.com.cubegame.Utils.Game;
import com.med.com.cubegame.Utils.GamesBDD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.med.com.cubegame.MainActivity.music;


public class Result extends AppCompatActivity {

    private ViewGroup mContentView;
    private ImageView etoile1, etoile2, etoile3;
    private TextView textView;
    private int level, nbrTentative, time;
    private Game game;

    private static int TENTATIVE_LVL_1 = 3, TIME_LVL_1 = 10,
            TENTATIVE_LVL_2 = 3, TIME_LVL_2 = 4,
            TENTATIVE_LVL_3 = 4, TIME_LVL_3 = 6,
            TENTATIVE_LVL_4 = 5, TIME_LVL_4 = 8,
            DELTA_TENTATIVE = 2, DELTA_TIME = 5;

    private String id_application = "2018_1_5_1",//check
            id_exercice = "T_5_5",//check
            id_niveau,//check
            date_actuelle,//check
            heure_debut,//check
            heure_fin,//check
            nbr_op_reussi,//check
            nbr_op_echoue,//check
            min_temps_op_sec,//check
            moy_temps_op_sec,//check
            id_apprenant,
            id_accompagnant,
            longitude,
            latitude,
            device,
            flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Initialisation des données. Ici, on aura notre variable Game(game) initialisé & set
        init();

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        //Showing the result
        showResult();

        // Création d'une variable Game
        game = new Game(id_application, id_exercice, id_niveau, date_actuelle, heure_debut, heure_fin, nbr_op_reussi, nbr_op_echoue, min_temps_op_sec, moy_temps_op_sec);

        //Insetion de la variable game
        insertGame(game);


    }

    private void insertGame(Game game) {
        //Insertion dans la base de donnée de la variable Game.
        GamesBDD gamesBDD = new GamesBDD(this);

        //On ouvre la base de données pour écrire dedans
        gamesBDD.open();

        //On insère la variable Game que l'on a crée
        Long id = gamesBDD.insertGameInfo(game);
        if( id ==-1){
            Toast.makeText(this,"Insertion échoué.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Insertion réussi, id = "+id, Toast.LENGTH_SHORT).show();
        }

        //On ferme notre bdd
        gamesBDD.close();
    }

    private void init(){
        mContentView = findViewById(R.id.activity_result);
        etoile1 = findViewById(R.id.etoile1);
        etoile2 = findViewById(R.id.etoile2);
        etoile3 = findViewById(R.id.etoile3);
        textView = findViewById(R.id.textView);
        level = MainActivity.getLevel();
        id_niveau = String.valueOf(level);

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        date_actuelle = df.format( new Date());

        switch (level){
            case 1:
                nbrTentative = Level1.getNbrTentative();
                heure_debut = Level1.getStartTime();
                heure_fin = Level1.getEndTime();
                min_temps_op_sec=Level1.getMinTime();
                moy_temps_op_sec=Level1.getMoyTime();
                nbr_op_reussi = String.valueOf(level);
                nbr_op_echoue = String.valueOf(nbrTentative-level);
                break;
            case 2:
                nbrTentative = Level2.getNbrTentative();
                heure_debut = Level2.getStartTime();
                heure_fin = Level2.getEndTime();
                min_temps_op_sec=Level2.getMinTime();
                moy_temps_op_sec=Level2.getMoyTime();
                nbr_op_reussi = String.valueOf(level);
                nbr_op_echoue = String.valueOf(nbrTentative-level);
                break;
            case 3:
                nbrTentative = Level3.getNbrTentative();
                heure_debut = Level3.getStartTime();
                heure_fin = Level3.getEndTime();
                min_temps_op_sec=Level3.getMinTime();
                moy_temps_op_sec=Level3.getMoyTime();
                nbr_op_reussi = String.valueOf(level);
                nbr_op_echoue = String.valueOf(nbrTentative-level);
                break;
            case 4:
                nbrTentative = Level4.getNbrTentative();
                heure_debut = Level4.getStartTime();
                heure_fin = Level4.getEndTime();
                min_temps_op_sec=Level4.getMinTime();
                moy_temps_op_sec=Level4.getMoyTime();
                nbr_op_reussi = String.valueOf(level);
                nbr_op_echoue = String.valueOf(nbrTentative-level);
                break;
        }
    }

    // utils fonctions
    private void setVisEtoile(int i){
        switch (i){
            case 1:
                etoile1.setVisibility(View.VISIBLE);
                break;
            case 2:
                etoile1.setVisibility(View.VISIBLE);
                etoile2.setVisibility(View.VISIBLE);
                break;
            case 3:
                etoile1.setVisibility(View.VISIBLE);
                etoile2.setVisibility(View.VISIBLE);
                etoile3.setVisibility(View.VISIBLE);
                break;
        }
    }
    private int randInt(int min, int max){
        int range = (max-min)+1;
        return (int)(Math.random() * range) + min;
    }
    private void setAudio(int i, int indice){

        if(MainActivity.audioLang.equals("en")) {
            MediaPlayer sound_3[] = {MediaPlayer.create(this, R.raw.gifted_en),
                    MediaPlayer.create(this, R.raw.amazing_en),
                    MediaPlayer.create(this, R.raw.welldone_en),
                    MediaPlayer.create(this, R.raw.proud_en)};

            MediaPlayer sound_2[] = {MediaPlayer.create(this, R.raw.welldone_en),
                    MediaPlayer.create(this, R.raw.alone_en),
                    MediaPlayer.create(this, R.raw.efforts_en),
                    MediaPlayer.create(this, R.raw.courage_en)};

            MediaPlayer sound_1[] = {MediaPlayer.create(this, R.raw.resources_en),
                    MediaPlayer.create(this, R.raw.time_en),
                    MediaPlayer.create(this, R.raw.timepractice_en),
                    MediaPlayer.create(this, R.raw.persistent_en)};
            switch (i){
                case 1:
                    sound_1[indice].start();
                    break;
                case 2:
                    sound_2[indice].start();
                    break;
                case 3:
                    sound_3[indice].start();
                    break;
            }
        }else if(MainActivity.audioLang.equals("fr")){
            MediaPlayer sound_3[] = {MediaPlayer.create(this, R.raw.gifted_fr),
                    MediaPlayer.create(this, R.raw.amazing_fr),
                    MediaPlayer.create(this, R.raw.welldone_fr),
                    MediaPlayer.create(this, R.raw.proud_fr)};

            MediaPlayer sound_2[] = {MediaPlayer.create(this, R.raw.welldone_fr),
                    MediaPlayer.create(this, R.raw.alone_fr),
                    MediaPlayer.create(this, R.raw.efforts_fr),
                    MediaPlayer.create(this, R.raw.courage_fr)};

            MediaPlayer sound_1[] = {MediaPlayer.create(this, R.raw.resources_fr),
                    MediaPlayer.create(this, R.raw.time_fr),
                    MediaPlayer.create(this, R.raw.timepractice_fr),
                    MediaPlayer.create(this, R.raw.persistent_fr)};
            switch (i){
                case 1:
                    sound_1[indice].start();
                    break;
                case 2:
                    sound_2[indice].start();
                    break;
                case 3:
                    sound_3[indice].start();
                    break;
            }
        }

    }
    private void setMessage(int i, int indice){
        String exp_1etoile[] = { getResources().getString(R.string.exp_1etoiles_1),
                getResources().getString(R.string.exp_1etoiles_2),
                getResources().getString(R.string.exp_1etoiles_3),
                getResources().getString(R.string.exp_1etoiles_4) };
        String exp_2etoile[] = { getResources().getString(R.string.exp_2etoiles_1),
                getResources().getString(R.string.exp_2etoiles_2),
                getResources().getString(R.string.exp_2etoiles_3),
                getResources().getString(R.string.exp_2etoiles_4) };
        String exp_3etoile[] = { getResources().getString(R.string.exp_3etoiles_1),
                getResources().getString(R.string.exp_3etoiles_2),
                getResources().getString(R.string.exp_3etoiles_3),
                getResources().getString(R.string.exp_3etoiles_4) };
        switch (i){
            case 1:;
                textView.setText(exp_1etoile[indice]);
                break;
            case 2:
                textView.setText(exp_2etoile[indice]);
                break;
            case 3:
                textView.setText(exp_3etoile[indice]);
                break;
        }
    }

    // fonction that display results
    private void showResult() {

        int indice = randInt(0, 3);

        switch (level){
            case 1:
                if(nbrTentative <= TENTATIVE_LVL_1){
                    if(time <= TIME_LVL_1){// show 3 étoiles
                        setVisEtoile(3);
                        setMessage(3, indice);
                        setAudio(3, indice);
                    }else if (time > TIME_LVL_1 && time <= TIME_LVL_1+DELTA_TIME){// show 2 étoiles
                        setVisEtoile(2);
                        setMessage(2, indice);
                        setAudio(2, indice);
                    }else{// show 1 étoile
                        setVisEtoile(1);
                        setMessage(1, indice);
                        setAudio(1, indice);
                    }
                }else if (nbrTentative > TENTATIVE_LVL_1 && nbrTentative <= TENTATIVE_LVL_1+DELTA_TENTATIVE){// show 2 étoiles
                    setVisEtoile(2);
                    setMessage(2, indice);setAudio(2, indice);
                }else{// show 1 étoile
                    setVisEtoile(1);
                    setMessage(1, indice);
                    setAudio(1, indice);
                }
                break;

            case 2:
                if(nbrTentative <= TENTATIVE_LVL_2){
                    if(time <= TIME_LVL_2){// show 3 étoiles
                        setVisEtoile(3);
                        setMessage(3, indice);
                        setAudio(3, indice);
                    }else if (time > TIME_LVL_2 && time <= TIME_LVL_2+DELTA_TIME){// show 2 étoiles
                        setVisEtoile(2);
                        setAudio(2, indice);
                        setMessage(2, indice);
                    }else{// show 1 étoile
                        setVisEtoile(1);
                        setMessage(1, indice);
                        setAudio(1, indice);
                    }
                }else if (nbrTentative > TENTATIVE_LVL_2 && nbrTentative <= TENTATIVE_LVL_2+DELTA_TENTATIVE){// show 2 étoiles
                    setVisEtoile(2);
                    setMessage(2, indice);
                    setAudio(2, indice);
                }else{// show 1 étoile
                    setVisEtoile(1);
                    setAudio(1, indice);
                    setMessage(1, indice);
                }
                break;

            case 3:
                if(nbrTentative <= TENTATIVE_LVL_3){
                    if(time <= TIME_LVL_3){// show 3 étoiles
                        setVisEtoile(3);
                        setMessage(3, indice);
                        setAudio(3, indice);
                    }else if (time > TIME_LVL_3 && time <= TIME_LVL_3+DELTA_TIME){// show 2 étoiles
                        setVisEtoile(2);
                        setMessage(2, indice);
                        setAudio(2, indice);
                    }else{// show 1 étoile
                        setVisEtoile(1);
                        setAudio(1, indice);
                        setMessage(1, indice);
                    }
                }else if (nbrTentative > TENTATIVE_LVL_3 && nbrTentative <= TENTATIVE_LVL_3+DELTA_TENTATIVE){// show 2 étoiles
                    setVisEtoile(2);
                    setMessage(2, indice);
                    setAudio(2, indice);
                }else{// show 1 étoile
                    setVisEtoile(1);
                    setAudio(1, indice);
                    setMessage(1, indice);
                }
                break;

            case 4:
                if(nbrTentative <= TENTATIVE_LVL_4){
                    if(time <= TIME_LVL_4){// show 3 étoiles
                        setVisEtoile(3);
                        setMessage(3, indice);
                        setAudio(3, indice);
                    }else if (time > TIME_LVL_4 && time <= TIME_LVL_4+DELTA_TIME){// show 2 étoiles
                        setVisEtoile(2);
                        setMessage(2, indice);
                        setAudio(2, indice);
                    }else{// show 1 étoile
                        setVisEtoile(1);
                        setAudio(1, indice);
                        setMessage(1, indice);
                    }
                }else if (nbrTentative > TENTATIVE_LVL_4 && nbrTentative <= TENTATIVE_LVL_4+DELTA_TENTATIVE){// show 2 étoiles
                    setVisEtoile(2);
                    setMessage(2, indice);
                    setAudio(2, indice);
                }else{// show 1 étoile
                    setVisEtoile(1);
                    setAudio(1, indice);
                    setMessage(1, indice);
                }
                break;
        }
    }

    /* Fonctions that make the fullscreen mode. */
    public void homePage(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);
    }
    private void setToFullScreen(){
        ViewGroup rootLayout = findViewById(R.id.activity_result);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    @Override
    public void onResume() {
        super.onResume();
        setToFullScreen();
    }

}
