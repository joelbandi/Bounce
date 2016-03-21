package com.example.joel.bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by joel on 3/20/16.
 */
public class BouncingThings extends View {

    Paint cyan = new Paint();

    Paint green = new Paint();

    //other miscellanous vars
    boolean one;
    boolean collision;

    // cyan ball (1) vars
    float posx1;
    float posy1;
    boolean x1;
    boolean y1;
    float radius1;

    // green ball (2) vars;

    float posx2;
    float posy2;
    boolean x2;
    boolean y2;
    float radius2;

    public float distance(double x1, double y1, double x2, double y2){

        return (float)Math.sqrt((Math.pow((x1-x2),2.0)+Math.pow((y1-y2),2.0)));

    }


    public void init1(){
        cyan.setColor(Color.CYAN);
        cyan.setStyle(Paint.Style.FILL_AND_STROKE);
        cyan.setAntiAlias(true);
        posx1 =10;
        posy1 =10;
        x1=y1=true;
        radius1 = 55f;
    }


    public void init2(){
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL_AND_STROKE);
        green.setAntiAlias(true);
        one = true;
        x2=y2=true;
        radius2 = 70f;
    }


    public BouncingThings(Context context) {
        super(context);
        init1();
        init2();
    }



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        //this is an evil hack  - please ignore
        if(one){
            posx2 = canvas.getWidth()-radius2;
            posy2 = canvas.getHeight()-radius2;
            one = false;
        }

        //drawing ball 1
        canvas.drawCircle(posx1,posy1,radius1,cyan);
        canvas.drawCircle(posx2,posy2,radius2,green);

        if(distance((float)posx1,(float)posy1,(float)posx2,(float)posy2)<=(radius2+radius1)){
            collision = true;
        }else{
            collision = false;
        }

        updateBall1(canvas,collision);
        updateBall2(canvas,collision);
        invalidate();

    }


    public void updateBall1(Canvas canvas,boolean collision){

        if(collision){
            y1 = !y1;
            x1 = !x1;
        }

        // x animation
        if(posx1 + radius1 >=canvas.getWidth() && x1){
            x1= false;
        }

        if(posx1 - radius1 <=0 && !x1){
            x1= true;
        }


        if(x1){
            posx1 = posx1 + 5;
        }else{
            posx1 = posx1 - 5;
        }

        // y animation

        if(posy1 + radius1 >=canvas.getHeight() && y1){
            y1= false;
        }

        if(posy1 - radius1 <=0 && !y1){
            y1= true;
        }

        // updation

        if(y1){
            posy1 = posy1 + 3;
        }else{
            posy1 = posy1 - 3;
        }
    }


    public void updateBall2(Canvas canvas,boolean collision){

        if(collision){
            y2 = !y2;
            x2 = !x2;
        }

        // x animation
        if(posx2 + radius2 >=canvas.getWidth() && x2){
            x2= false;
        }

        if(posx2 - radius2 <=0 && !x2){
            x2= true;
        }


        if(x2){
            posx2 = posx2 + 6;
        }else{
            posx2 = posx2 - 6;
        }

        // y animation

        if(posy2 + radius2 >=canvas.getHeight() && y2){
            y2= false;
        }

        if(posy2 - radius2 <=0 && !y2){
            y2= true;
        }



        //collision event


        if(y2){
            posy2 = posy2 + 5;
        }else{
            posy2 = posy2 - 5;
        }
    }



}
