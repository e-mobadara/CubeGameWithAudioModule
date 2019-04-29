package com.med.com.cubegame.Levels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.med.com.cubegame.MainActivity;
import com.med.com.cubegame.R;
import com.med.com.cubegame.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Level1 extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ViewGroup mContentView;
    ImageView objectif, hand, square;
    Chronometer chrono;
    float x, y, yInit, xInit;
    static int nbrTentative=0;
    int time=0, repeat=3;
    ArrayList<Integer> duree;
    MediaPlayer mp;
    static String startTime, endTime, minTime, moyTime;
    boolean repeatHelp=true;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        init();

        // Setting up the background image
        getWindow().setBackgroundDrawableResource(R.drawable.gamebg);

        // Getting into fullscreen mode when the user click on the screen.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        // Handling the on touch event -- Action move
        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x = motionEvent.getX();
                y = motionEvent.getY();


                if (isPressed(square, x, y)) {


                    switch (motionEvent.getAction()){

                        case MotionEvent.ACTION_DOWN:
                            hand.setVisibility(View.INVISIBLE);
                            repeatHelp = false;
                            nbrTentative++;
                            chrono.setBase(SystemClock.elapsedRealtime() );
                            chrono.start();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            if(!isOut(square, mContentView)){
                                square.setX(x - (square.getWidth()/2));
                                square.setY(y - (square.getHeight()/2));
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            //Getting the time of the operation
                            chrono.stop();
                            time = (int) (SystemClock.elapsedRealtime() - chrono.getBase())/1000;
                            duree.add(time);

                            if(isClose(square, objectif )){
                                square.setX(objectif.getX());
                                square.setY(objectif.getY());
                                repeat--;

                                try {
                                    mp.start();
                                } catch (Exception e) {
                                    Log.d("exception:" ," " + e);
                                }


                                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        switch (repeat){
                                            case 1 :
                                                square.setX(relativeLayout.getX());
                                                square.setY(relativeLayout.getHeight()*80/100);
                                                xInit = convertPixelsToDp(square.getX(), getApplicationContext());
                                                yInit = convertPixelsToDp(square.getY(), getApplicationContext());
                                                break;
                                            case 2 :
                                                square.setX(relativeLayout.getWidth()-square.getWidth());
                                                square.setY(relativeLayout.getHeight()*80/100);
                                                break;

                                        }
                                    }
                                });

                                if(repeat==0) {
                                    try {
                                        mp.stop();
                                    }catch (Exception e) {
                                        Log.d("exception:" ," " + e);
                                    }

                                    //end time :
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                    endTime = sdf.format(new Date());

                                    //Moyenne time:
                                    moyTime = String.valueOf(moy(duree));

                                    //Minimum time:
                                    minTime = String.valueOf(min(duree));

                                    //Start result activity:
                                    Intent result = new Intent(getApplicationContext(), Result.class);
                                    finish();
                                    startActivity(result);
                                }
                            }
                            else{
                                gravity(square);
                            }
                            break;
                    }
                }
                return false;
            }
        });
    }

    //Moyenne d'une arraylist
    private double moy(ArrayList<Integer> list){
        double moy=0;
        for(int i=0; i<list.size(); i++) {
            moy += list.get(i);
        }
        moy=moy/list.size();
        return moy;
    }

    //Minimum d'une arraylist
    private int min(ArrayList<Integer> list){
        int min=list.get(0);
        for(int i=0; i<list.size(); i++){
            if(min>=duree.get(i)){
                min=duree.get(i);
            }
        }
        return min;
    }

    private void init(){
        /* Getting the reference of the main layout & exit button & squares */
        mContentView = findViewById(R.id.activity_level1);
        square = findViewById(R.id.square);
        objectif = findViewById(R.id.objectif);
        hand = findViewById(R.id.hand);
        chrono = findViewById(R.id.chrono);
        duree = new ArrayList<>();
        relativeLayout = findViewById(R.id.middleLayout);

        /* Getting the position of the square */
        xInit = square.getX();
        yInit = square.getY();

        /* Init nbr tentative */
        nbrTentative = 0;

        /* Initiate sound */
        /**
         * inside this two conditions, we will :
         * create variable for each sound.
         * give those variables a default sound as it is shown now( from shared preferences).
         * create a public static function to change those variables whenever the user wanted to change the sound.
         */
        if(MainActivity.audioLang.equals("en")) {
            mp = moblibAudioFileManager.getRandomAudioFile(this,"excellent","AN");
        }else if(MainActivity.audioLang.equals("fr")){
            mp = moblibAudioFileManager.getRandomAudioFile(this,"excellent","FR");
        }else{
            mp = moblibAudioFileManager.getRandomAudioFile(this,"excellent","AR");
        }

        //start time :
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        startTime = sdf.format(new Date());
    }

    public void gravity(ImageView view) {
        view.animate()
                .translationX(convertPixelsToDp(xInit, this))
                .translationY(convertPixelsToDp(yInit, this))
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(3000);
    }

    /* Fonction that checks if a square is pressed */
    public static boolean isPressed(ImageView square, float x, float y ){
        boolean ispressed = false;
        /* Calculating */
        float width = square.getX() + square.getMaxWidth();
        float height = square.getY() + square.getMaxHeight();
        if( x >= square.getX() && x <= width ){
            if(y >= square.getY() && y <= height){
                ispressed = true;
            }
        }
        return ispressed;
    }

    /* Fonction that check if the screen is not out of the screen */
    private boolean isOut(ImageView square, ViewGroup view){
        boolean isout = false;
        ViewGroup leftLayout =  findViewById(R.id.left_layout);
        ViewGroup rightLayout = findViewById(R.id.right_layout);

        if (square.getX() < leftLayout.getWidth()) {
            isout = true;
            square.setX(leftLayout.getWidth());
        }

        if (square.getY() < -1){
            isout = true;
            square.setY(0);
        }

        if ((square.getX()+square.getWidth()) > rightLayout.getX()){
            isout = true;
            square.setX(rightLayout.getX()-square.getWidth());
        }

        if ((square.getY()+square.getHeight()) > view.getHeight()){
            isout = true;
            square.setY(view.getHeight()-square.getHeight());
        }

        return isout;
    }

    /* Check if a square is close to the objectif */
    public boolean isClose(ImageView square, ImageView objectif){
        boolean close = false;
        float xObj = objectif.getX(), yObj = objectif.getY();
        float widthObj = objectif.getWidth(), heightObj = objectif.getHeight();
        float xS = square.getX(), yS = square.getY();
        if ( xS >= (xObj-(widthObj/3)) && xS <= (xObj+(widthObj/3)) ){
            if( yS >= (yObj-(heightObj/3)) && yS <= (yObj+(heightObj/3)) ){
                close = true;
            }
        }
        return close;
    }

    /* Fonction that allows to reset the squares */
    public void reset(View view) {
        Intent intent = new Intent(this, Level1.class);
        finish();
        startActivity(intent);
    }

    /* Home page function */
    public void homePage(View view){
        chrono.stop();
        AlertDialog.Builder exitAlert = new AlertDialog.Builder(this);
        String message, yes, no;
        message = getResources().getString(R.string.existMessage);
        yes = getResources().getString(R.string.yes);
        no = getResources().getString(R.string.no);
        exitAlert.setMessage(message)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chrono.start();
                    }
                })
                .create();
        exitAlert.show();
    }

    /* Fonction that make the fullscreen mode. */
    private void setToFullScreen(){
        ViewGroup rootLayout = findViewById(R.id.activity_level1);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /* Allows to turn to full screen mode quickly. */
    @Override
    public void onResume() {
        super.onResume();
        setToFullScreen();
    }

    /* How to play button */
    public void help(View view) throws InterruptedException {
        // Moving hand
        hand.setX(square.getX() + (square.getWidth() / 4));
        hand.setY(square.getY() + (square.getHeight() / 4));
        hand.setVisibility(View.VISIBLE);

        hand.animate()
                .translationX((float) (objectif.getX() + objectif.getWidth() * 0.04))
                .translationY((float) (-hand.getHeight() * 0.04))
                .setDuration(3000);
    }

    /* Converting pixels to dp */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    // Random Fonction
    private int randInt(int min, int max){
        int range = (max-min)+1;
        return (int)(Math.random() * range) + min;
    }

    //Getters
    public static int getNbrTentative(){
        return nbrTentative;
    }
    public static String getStartTime() {
        return startTime;
    }
    public static String getEndTime() {
        return endTime;
    }
    public static String getMinTime() {
        return minTime;
    }
    public static String getMoyTime() {
        return moyTime;
    }

}
