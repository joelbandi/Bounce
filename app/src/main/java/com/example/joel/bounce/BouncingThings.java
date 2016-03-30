package com.example.joel.bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by joel on 3/20/16.
 */


public class BouncingThings extends View {

    // drawing resources
    Paint cyan = new Paint();
    Paint green = new Paint();

    //other miscellanous vars
    boolean collision;
    int tempx, tempy;
    int pers = 10;
    boolean x1;
    boolean y1;
    boolean x2;
    boolean y2;




    Ball greenball;
    Ball cyanball;


    public void init(){
        cyan.setColor(Color.CYAN);
        cyan.setStyle(Paint.Style.FILL_AND_STROKE);
        cyan.setAntiAlias(true);
        cyanball = new Ball(150f,150f,5,5,100f,cyan);

        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL_AND_STROKE);
        green.setAntiAlias(true);
        greenball = new Ball(350f,350f,6,5,150f,green);

        x1=y1=x2=y2=true;
    }



    public BouncingThings(Context context) {
        super(context);
        init();
    }


    public float distanceBetweenBalls(double x1, double y1, double x2, double y2){

        return (float)Math.sqrt((Math.pow((x1-x2),2.0)+Math.pow((y1-y2),2.0)));

    }


    public void onCollide(){


        tempx = (int) cyanball.speedx;
        tempy = (int) cyanball.speedy;


        // using equations derived from law of conservation of momentum and energy : modeled after perfect Elastic collisions of objects of different masses
        // for simplicity ive assumed density of the two balls same and mass is proportional to radius instead of radius^3

        cyanball.speedx = (cyanball.speedx * (cyanball.radius - greenball.radius) + (2 * greenball.radius * greenball.speedx))/(greenball.radius + cyanball.radius);
        cyanball.speedy = (cyanball.speedy * (cyanball.radius - greenball.radius) + (2 * greenball.radius * greenball.speedy))/(greenball.radius + cyanball.radius);
        greenball.speedx = (greenball.speedx * (greenball.radius - cyanball.radius) + (2 * cyanball.radius * tempx))/(greenball.radius + cyanball.radius);
        greenball.speedy = (greenball.speedy * (greenball.radius - cyanball.radius) + (2 * cyanball.radius * tempy))/(greenball.radius + cyanball.radius);
        cyanball.posx += cyanball.speedx;
        cyanball.posy += cyanball.speedy;
        greenball.posx +=greenball.speedx;
        greenball.posy +=greenball.speedy;


        // capping the speeds to a max level to maintain persistance defined by variable pers
        if(cyanball.speedx>pers){
            cyanball.speedx = pers;
        }
        if(greenball.speedx>pers){
            greenball.speedx = pers;
        }
        if(cyanball.speedy>pers){
            cyanball.speedy = pers;
        }
        if(greenball.speedy>pers){
            greenball.speedy = pers;
        }


    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        //drawing ball 1
//        canvas.drawCircle(posx1, posy1,radius1,cyan);
//        canvas.drawCircle(posx2, posy2,radius2,green);

        cyanball.render(canvas);
        greenball.render(canvas);

        if(distanceBetweenBalls(cyanball.posx,  cyanball.posy,  greenball.posx,  greenball.posy)<=(cyanball.radius+greenball.radius)){
            collision = true;
            onCollide();
            invalidate();
        }else{
            collision = false;
        }



        updateCyanBall(canvas, collision);
        updateGreenBall(canvas, collision);
        invalidate();

    }


    public void updateCyanBall(Canvas canvas, boolean collision){



        // x animation
        if(cyanball.posx + cyanball.radius >=canvas.getWidth()){
            if(x1) {
                cyanball.speedx = -cyanball.speedx;
                x1 = false;
            }
            cyanball.posx += cyanball.speedx - 5;
        }else if(cyanball.posx - cyanball.radius <=5){
            if(x1) {
                cyanball.speedx = -cyanball.speedx;
                x1 =false;
            }
            cyanball.posx += cyanball.speedx;
        } else{
            cyanball.posx += cyanball.speedx;
            x1 = true;
        }




        // y animation

        if(cyanball.posy + cyanball.radius >=canvas.getHeight()){
            if(y1) {
                cyanball.speedy = -cyanball.speedy;
                y1 = false;
            }
            cyanball.posy += cyanball.speedy - 5;
        }else if(cyanball.posy - cyanball.radius <=5){
            if(y1) {
                cyanball.speedy = -cyanball.speedy;
                y1 = false;
            }
            cyanball.posy += cyanball.speedy;
        }else{
            cyanball.posy += cyanball.speedy;
            y1 = true;
        }



    }


    public void updateGreenBall(Canvas canvas, boolean collision){


        // x animation
        if(greenball.posx + greenball.radius >=canvas.getWidth()){
            if(x2) {
                greenball.speedx = -greenball.speedx;
                x2= false;
            }
            greenball.posx += greenball.speedx;
        }else if(greenball.posx - greenball.radius <=5){
            if(x2) {
                greenball.speedx = -greenball.speedx;
                x2 = false;
            }
            greenball.posx += greenball.speedx;
        } else{
            greenball.posx += greenball.speedx;
            x2 = true;
        }




        // y animation

        if(greenball.posy + greenball.radius >=canvas.getHeight()){
            if(y2) {
                greenball.speedy = -greenball.speedy;
                y2 = false;
            }
            greenball.posy += greenball.speedy;
        }else if(greenball.posy - greenball.radius <=5){
            if(y2) {
                greenball.speedy = -greenball.speedy;
                y2  = false;
            }
            greenball.posy += greenball.speedy;
        }else{
            greenball.posy += greenball.speedy;
            y2 = true;
        }



    }



}
