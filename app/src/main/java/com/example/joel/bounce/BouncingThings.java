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

    // drawing resources
    Paint cyan = new Paint();
    Paint green = new Paint();

    //other miscellanous vars
    boolean one;
    boolean collision;
    int tx,ty;
    int pers = 10;

    // cyan ball (1) vars
    float posx1;
    float posy1;
    boolean x1;
    boolean y1;
    float radius1;
    float speedx1,speedy1;

    // green ball (2) vars;

    float posx2;
    float posy2;
    boolean x2;
    boolean y2;
    float radius2;
    float speedx2,speedy2;



    public void init1(){
        cyan.setColor(Color.CYAN);
        cyan.setStyle(Paint.Style.FILL_AND_STROKE);
        cyan.setAntiAlias(true);
        posx1 =150f;
        posy1 =150f;
        x1=y1=true;
        radius1 = 100f;
        speedx1= 5;
        speedy1=5;
    }


    public void init2(){
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL_AND_STROKE);
        green.setAntiAlias(true);
        one = true;
        x2=y2=true;
        radius2 = 125f;
        speedx2 = 6;
        speedy2 = 5;
    }


    public BouncingThings(Context context) {
        super(context);


        init1();
        init2();
    }


    public float distance(double x1, double y1, double x2, double y2){

        return (float)Math.sqrt((Math.pow((x1-x2),2.0)+Math.pow((y1-y2),2.0)));

    }


    public void onCollide(){

        tx = (int) speedx1;
        ty = (int) speedy1;
        speedx1 = (speedx1 * (radius1 - radius1) + (2 * radius1 * speedx2))/(radius1 + radius1);
        speedy1 = (speedy1 * (radius1 - radius1) + (2 * radius1 * speedy2))/(radius1 + radius1);
        speedx2 = (speedx2 * (radius1 - radius1) + (2 * radius1 * tx))/(radius1 + radius1);
        speedy2 = (speedy2 * (radius1 - radius1) + (2 * radius1 * ty))/(radius1 + radius1);
        posx1 += speedx1;
        posy1 += speedy1;
        posx2 +=speedx2;
        posy2 +=speedy2;

        if(speedx1>pers){
            speedx1 = pers;
        }
        if(speedx2>pers){
            speedx2 = pers;
        }
        if(speedy1>pers){
            speedy1 = pers;
        }
        if(speedy2>pers){
            speedy2 = pers;
        }





    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        //this is an evil hack  - please ignore

        if(one){
            posx2 = canvas.getWidth()-radius2-5;
            posy2 = canvas.getHeight()-radius2-5;
            one = false;
        }

        //drawing ball 1
        canvas.drawCircle(posx1, posy1,radius1,cyan);
        canvas.drawCircle(posx2, posy2,radius2,green);

        if(distance((float) posx1, (float) posy1, (float) posx2, (float) posy2)<=(radius2+radius1)){
            collision = true;
            onCollide();
            invalidate();
        }else{
            collision = false;
        }



        updateBall1(canvas,collision);
        updateBall2(canvas,collision);
        invalidate();

    }


    public void updateBall1(Canvas canvas,boolean collision){



        // x animation
        if(posx1 + radius1 >=canvas.getWidth()){
            if(x1) {
                speedx1 = -speedx1;
                x1 = false;
            }
            posx1 += speedx1 - 5;
        }else if(posx1 - radius1 <=0){
            if(x1) {
                speedx1 = -speedx1;
                x1 =false;
            }
            posx1 += speedx1;
        } else{
            posx1 += speedx1;
            x1 = true;
        }




        // y animation

        if(posy1 + radius1 >=canvas.getHeight()){
            if(y1) {
                speedy1 = -speedy1;
                y1 = false;
            }
            posy1 += speedy1 - 5;
        }else if(posy1 - radius1 <=0){
            if(y1) {
                speedy1 = -speedy1;
                y1 = false;
            }
            posy1 += speedy1;
        }else{
            posy1 += speedy1;
            y1 = true;
        }



    }


    public void updateBall2(Canvas canvas,boolean collision){


        // x animation
        if(posx2 + radius2 >=canvas.getWidth()){
            if(x2) {
                speedx2 = -speedx2;
                x2= false;
            }
            posx2 += speedx2;
        }else if(posx2 - radius2 <=0){
            if(x2) {
                speedx2 = -speedx2;
                x2 = false;
            }
            posx2 += speedx2;
        } else{
            posx2 += speedx2;
            x2 = true;
        }




        // y animation

        if(posy2 + radius2 >=canvas.getHeight()){
            if(y2) {
                speedy2 = -speedy2;
                y2 = false;
            }
            posy2 += speedy2;
        }else if(posy2 - radius2 <=0){
            if(y2) {
                speedy2 = -speedy2;
                y2  = false;
            }
            posy2 += speedy2;
        }else{
            posy2 += speedy2;
            y2 = true;
        }



    }



}
