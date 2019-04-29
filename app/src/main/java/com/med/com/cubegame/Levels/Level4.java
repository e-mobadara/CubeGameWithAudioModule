package com.med.com.cubegame.Levels;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.med.com.cubegame.MainActivity;
import com.med.com.cubegame.R;
import com.med.com.cubegame.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Level4 extends AppCompatActivity {

    private ViewGroup mContentView;
    private Chronometer chrono;
    private ImageView square, square2, square3, square4, objectif, objectif2, objectif3, objectif4;
    private DisplayMetrics metrics;
    private float  x, y, tab[][] = new float [4][2];
    public static int nbrTentative=0;
    private int time=0;
    private MediaPlayer mp;
    private static String startTime, endTime, minTime, moyTime;
    private ArrayList<Integer> duree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);
        init();
        chrono.start();
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        /* Setting up the background image */
        getWindow().setBackgroundDrawableResource(R.drawable.gamebg);
        /* Getting into fullscreen mode when the user click on the screen. */
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });
        /* Handling the on touch event -- Action move */
        mContentView.setOnTouchListener(new View.OnTouchListener() {/* Needs Change when changing activity */
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();
            /* Retrieving the pressed square */
            int squarePressed = isPressed(motionEvent);

            switch(motionEvent.getAction()){

                case MotionEvent.ACTION_DOWN:
                    switch (squarePressed){

                        default:
                            nbrTentative++;
                            chrono.setBase(SystemClock.elapsedRealtime() );
                            chrono.start();
                            break;
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    switch (squarePressed){
                        case 1 :
                            if(!isOut(square, mContentView)){ /* moving square 1 withing the limites */
                                square.setX(x - (square.getWidth()/2));
                                square.setY(y - (square.getHeight()/2));
                            }
                            break;
                        case 2 :
                            if(!isOut(square2, mContentView)){ /* moving square 2 withing the limites */
                                square2.setX(x - (square2.getWidth()/2));
                                square2.setY(y - (square2.getHeight()/2));
                            }
                            break;
                        case 3 :
                            if(!isOut(square3, mContentView)){ /* moving square 2 withing the limites */
                                square3.setX(x - (square3.getWidth()/2));
                                square3.setY(y - (square3.getHeight()/2));
                            }
                            break;
                        case 4 :
                            if(!isOut(square4, mContentView)){ /* moving square 2 withing the limites */
                                square4.setX(x - (square4.getWidth()/2));
                                square4.setY(y - (square4.getHeight()/2));
                            }
                            break;
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    switch (squarePressed){
                        case 1:
                            if (isClose(square, objectif)) { /* if square 1 is close to objecticf 1 */
                                square.setX(objectif.getX());
                                square.setY(objectif.getY());
                                //Getting the time of the operation
                                chrono.stop();
                                time = (int) (SystemClock.elapsedRealtime() - chrono.getBase())/1000;
                                duree.add(time);
                            }else{
                                gravity(square, 1);
                            }
                            break;
                        case 2:
                            if(isClose(square2, objectif2 )){/* if square 2 is close to objecticf 2 */
                                square2.setX(objectif2.getX());
                                square2.setY(objectif2.getY());
                                //Getting the time of the operation
                                chrono.stop();
                                time = (int) (SystemClock.elapsedRealtime() - chrono.getBase())/1000;
                                duree.add(time);
                            }else{
                                gravity(square2, 2);
                            }
                            break;
                        case 3:
                            if(isClose(square3, objectif3)){/* if square 2 is close to objecticf 2 */
                                square3.setX(objectif3.getX());
                                square3.setY(objectif3.getY());
                                //Getting the time of the operation
                                chrono.stop();
                                time = (int) (SystemClock.elapsedRealtime() - chrono.getBase())/1000;
                                duree.add(time);
                            }else{
                                gravity(square3, 3);
                            }
                            break;
                        case 4:
                            if(isClose(square4, objectif4)){/* if square 2 is close to objecticf 2 */
                                square4.setX(objectif4.getX());
                                square4.setY(objectif4.getY());
                                //Getting the time of the operation
                                chrono.stop();
                                time = (int) (SystemClock.elapsedRealtime() - chrono.getBase())/1000;
                                duree.add(time);
                            }else{
                                gravity(square4,4);
                            }
                            break;
                    }

                    if(allObjectifsAcheived()){
                        //end time :
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        endTime = sdf.format(new Date());

                        //Moyenne time:
                        moyTime = String.valueOf(moy(duree));

                        //Minimum time:
                        minTime = String.valueOf(min(duree));

                        Intent result = new Intent(getApplicationContext(), Result.class);
                        finish();
                        startActivity(result);
                    }

                    break;
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
        mContentView = findViewById(R.id.activity_level4);/* Needs Change when changing activity */
        square = findViewById(R.id.square);
        square2 = findViewById(R.id.square2);
        square3 = findViewById(R.id.square3);
        square4 = findViewById(R.id.square4);
        objectif = findViewById(R.id.objectif);
        objectif2 = findViewById(R.id.objectif2);
        objectif3 = findViewById(R.id.objectif3);
        objectif4 = findViewById(R.id.objectif4);
        duree = new ArrayList<>();
        chrono = findViewById(R.id.chrono);

        /* Getting the positions of the squares */
        tab[0][0] = Level1.convertPixelsToDp(square.getX(), this);
        tab[0][1] = Level1.convertPixelsToDp(square.getY(), this);
        tab[1][0] = Level1.convertPixelsToDp(square2.getX(), this);
        tab[1][1] = Level1.convertPixelsToDp(square2.getX(), this);
        tab[2][0] = Level1.convertPixelsToDp(square3.getX(), this);
        tab[2][1] = Level1.convertPixelsToDp(square3.getX(), this);
        tab[3][0] = Level1.convertPixelsToDp(square4.getX(), this);
        tab[3][1] = Level1.convertPixelsToDp(square4.getX(), this);

        nbrTentative=0;

        mp = moblibAudioFileManager.getRandomAudioFile(this,"excellent","FR");

        //start time :
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        startTime = sdf.format(new Date());
    }

    public void gravity(ImageView view, int i) {
        switch(i){
            case 1 :
                view.animate()
                        .translationX(tab[0][0])
                        .translationY(tab[0][1])
                        .setInterpolator(new AccelerateInterpolator())
                        .setDuration(3000);
                break;
            case 2 :
                view.animate()
                        .translationX(tab[1][0])
                        .translationY(tab[1][1])
                        .setInterpolator(new AccelerateInterpolator())
                        .setDuration(3000);
                break;
            case 3 :
                view.animate()
                        .translationX(tab[2][0])
                        .translationY(tab[2][1])
                        .setInterpolator(new AccelerateInterpolator())
                        .setDuration(3000);
                break;
            case 4 :
                view.animate()
                        .translationX(tab[3][0])
                        .translationY(tab[3][1])
                        .setInterpolator(new AccelerateInterpolator())
                        .setDuration(3000);
                break;
        }
    }

    private boolean allObjectifsAcheived(){
        boolean achieved = false;
        if (square.getX() == objectif.getX() && square.getY() == objectif.getY()){
            if (square2.getX() == objectif2.getX() && square2.getY() == objectif2.getY()){
                if (square3.getX() == objectif3.getX() && square3.getY() == objectif3.getY()){
                    if (square4.getX() == objectif4.getX() && square4.getY() == objectif4.getY()){
                        achieved = true;
                    }
                }
            }
        }
        return achieved;
    }

    /* Fonction that checks if a square is pressed */
    public int isPressed(MotionEvent event){
        int squarePressed = 0;
        /* Calculating */
        float wSquare = square.getX() + square.getMaxWidth();
        float hSquare = square.getY() + square.getMaxHeight();
        float wSquare2 = square2.getX() + square2.getMaxWidth();
        float hSquare2 = square2.getY() + square2.getMaxHeight();
        float wSquare3 = square3.getX() + square3.getMaxWidth();
        float hSquare3 = square3.getY() + square3.getMaxHeight();
        float wSquare4 = square4.getX() + square4.getMaxWidth();
        float hSquare4 = square4.getY() + square4.getMaxHeight();

        if(square.getX() != objectif.getX() || square.getY() != objectif.getY()){
            if( x >= square.getX() && x <= wSquare && y >= square.getY() && y <= hSquare ){
                squarePressed = 1;
            }
        }
        else if (square2.getX() != objectif2.getX() || square2.getY() != objectif2.getY()) {
            if (x >= square2.getX() && x <= wSquare2 && y >= square2.getY() && y <= hSquare2 ){
                squarePressed = 2;
            }
        }
        else if (square3.getX() != objectif3.getX() || square3.getY() != objectif3.getY()) {
            if (x >= square3.getX() && x <= wSquare3 && y >= square3.getY() && y <= hSquare3 ){
                squarePressed = 3;
            }
        }
        else if (square4.getX() != objectif4.getX() || square4.getY() != objectif4.getY()) {
            if (x >= square4.getX() && x <= wSquare4 && y >= square4.getY() && y <= hSquare4 ){
                squarePressed = 4;
            }
        }

        return squarePressed;
    }

    private boolean isClose(ImageView square, ImageView objectif){
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

    /* Fonction that check if the screen is not out of the screen */
    private boolean isOut(ImageView square, ViewGroup view){
        boolean isout = false;
        ViewGroup leftLayout = findViewById(R.id.left_layout);
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

    public void reset(View view) { /* Needs Change when changing activity */
        Intent intent = new Intent(this, Level4.class);
        finish();
        startActivity(intent);
    }


    /* Home page fonction */
    public void homePage(View view) {
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
    private void setToFullScreen(){ /* Needs Change when changing activity */
        ViewGroup rootLayout = findViewById(R.id.activity_level4);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /* Allows to turn to full screen mode quickly. */
    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
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
