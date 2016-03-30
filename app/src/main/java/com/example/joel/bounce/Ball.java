package com.example.joel.bounce;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by joel on 3/22/16.
 */


// templating class for the ball


public class Ball{

    // made public as im only gonna instantiate two objects like these
    public float posx,posy,speedx,speedy,radius;
    public Paint paint;

    public Ball(float posx, float posy, float speedx, float speedy, float radius, Paint paint) {
        this.posx = posx;
        this.posy = posy;
        this.speedx = speedx;
        this.speedy = speedy;
        this.radius = radius;
        this.paint = paint;
    }

    public void render(Canvas canvas){

        canvas.drawCircle(posx, posy, radius, paint);

    }


    public void reposition(float posx, float posy){
        this.posx = posx;
        this.posy = posy;
    }


}
