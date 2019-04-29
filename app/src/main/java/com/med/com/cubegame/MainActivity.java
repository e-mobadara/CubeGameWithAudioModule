package com.med.com.cubegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.med.com.cubegame.Levels.Level1;
import com.med.com.cubegame.Levels.Level2;
import com.med.com.cubegame.Levels.Level3;
import com.med.com.cubegame.Levels.Level4;

//id exercice : T_5_5
//id application : 2018_1_5_1

public class MainActivity extends AppCompatActivity {

    public static String audioLang="en";
    private ViewGroup mContentView;
    private static int level = 1;
    private CharSequence[] items = {"Level 1", "Level 2","Level 3","Level 4"};
    private AlertDialog.Builder builder;
    private Intent intent, setting;
    private Button musicBtn;
    public static MediaPlayer music;
    static boolean isMuted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        // Init
        init();

        // Setting the fullscreen mode if the user clicked on the screen
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        if(!music.isPlaying() && isMuted==false) {
            music.start();
        }else if(isMuted){
            musicBtn.setBackgroundResource(R.drawable.music_off);
        }

        //Setting up the music
        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(music.isPlaying()) {
                    musicBtn.setBackgroundResource(R.drawable.music_off);
                    music.pause();
                    isMuted = true;
                }else {
                    musicBtn.setBackgroundResource(R.drawable.music_on);
                    music.start();
                    isMuted = false;
                }
            }
        });

        music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                music.start();
            }
        });

    }

    private void init(){

        /* Getting the reference of the main layout & the play button */
        mContentView = findViewById(R.id.activity_main);

        musicBtn = findViewById(R.id.musicBtn);

        if(music == null) {

            music = MediaPlayer.create(this, R.raw.music2);
        }

        setting = new Intent(this, SettingActivity.class);

        // initialise the dialog builder for selecting the level
        String title = getResources().getString(R.string.level);
        String titlePosBtn  =  getResources().getString(R.string.enregistrer);

        builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                level = 1;
                                break;
                            case 1:
                                level = 2;
                                break;
                            case 2:
                                level = 3;
                                break;
                            case 3:
                                level = 4;
                                break;
                        }
                    }
                })
                .setPositiveButton(titlePosBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (level){
                            case 1:
                                intent = new Intent(getApplicationContext(), Level1.class);
                                break;
                            case 2:
                                intent = new Intent(getApplicationContext(), Level2.class);
                                break;
                            case 3:
                                intent = new Intent(getApplicationContext(), Level3.class);
                                break;
                            case 4:
                                intent = new Intent(getApplicationContext(), Level4.class);
                                break;
                        }
                        startActivity(intent);
                    }
                });
    }

    public void startLevel(View view) {
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static int getLevel(){
        return level;
    }


    /* Fonctions for fullscreen mode. */
    private void setToFullScreen(){
        ViewGroup rootLayout = findViewById(R.id.activity_main);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }

    public void settings(View view) {
        startActivity(setting);
    }

}
