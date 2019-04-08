package com.med.com.cubegame;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.med.com.cubegame.audiomanaging.AudioSettingsActivity;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    Button confirm, cancel,change_audio;
    ViewGroup viewGroup;
    RadioGroup textRG, audioRG;
    Intent homePage;
    int textRBID, audioRBID;
    private static final String TAG = "MAIN page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        Log.d(TAG, "onCreate tablayout.");

        change_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _settings = new Intent(SettingActivity.this,AudioSettingsActivity.class);
                startActivity(_settings);
            }
        });
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToFullScreen();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePage);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textRBID = textRG.getCheckedRadioButtonId();
                audioRBID = audioRG.getCheckedRadioButtonId();
                RadioButton text = findViewById(textRBID);
                RadioButton audio = findViewById(audioRBID);
                if(text!=null) {
                    if (text.getText().equals("Français")) {
                        setLocale("fr");
                    } else if (text.getText().equals("English")) {
                        setLocale("en");
                    } else if (text.getText().equals("العربية")) {
                        setLocale("ar");
                    }
                }
                if(audio!=null) {
                    if (audio.getText().equals("Français")) {
                        MainActivity.audioLang = "fr";
                    } else if (audio.getText().equals("English")) {
                        MainActivity.audioLang = "en";
                    } else if (audio.getText().equals("العربية")) {
                        MainActivity.audioLang = "ar";
                    }
                }
                startActivity(homePage);
            }
        });

    }


    private void init(){
        confirm = findViewById(R.id.confirmBtn);
        cancel = findViewById(R.id.cancelButton);
        change_audio = findViewById(R.id.change_audio);
        viewGroup = findViewById(R.id.activity_setting);
        textRG = findViewById(R.id.LangRadioBtn);
        audioRG = findViewById(R.id.AudioRadioBtn);
        homePage = new Intent(this, MainActivity.class);
    }



    /* Fonctions for fullscreen mode. */
    private void setToFullScreen(){
        ViewGroup rootLayout = findViewById(R.id.activity_setting);
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

    public void setLocale(String lang){
        Locale locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }



}

