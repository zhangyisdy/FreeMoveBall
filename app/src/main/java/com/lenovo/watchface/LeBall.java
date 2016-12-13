package com.lenovo.watchface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import java.util.Random;

public class LeBall {

    private float xAxis;
    private float yAxis;
    private float radius;
    private int angle;
    private int order;

    public float speed;
    public int color=0;

    private Bitmap bitmap;

    public LeBall(float x, float y, float r, int o, Bitmap bitmap) {
        // TODO Auto-generated constructor stub
        this.xAxis = x;
        this.yAxis = y;
        this.radius = r;
        this.order = o;
        this.bitmap = bitmap;
        this.angle = new Random().nextInt(LeCrashValues.degree);
    }

    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        canvas.drawBitmap(this.bitmap, xAxis, yAxis, null);
    }

    public void updataColor(){
        OvalShape circle = new OvalShape();
        ShapeDrawable drawable = new ShapeDrawable(circle);
        int color = LeBallSurfaceView.colors[new Random().nextInt(LeBallSurfaceView.colors.length)];
        while (color == this.color){
            color = LeBallSurfaceView.colors[new Random().nextInt(LeBallSurfaceView.colors.length)];
        }
        drawable.getPaint().setColor(color);
        drawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(200,200,config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, 200, 200);
        drawable.draw(canvas);
        this.bitmap = bitmap;
        this.color = color;
    }

    public void resize(){

    }

    public float getxAxis() {
        return xAxis;
    }


    public void setxAxis(float xAxis) {
        this.xAxis = xAxis;
    }


    public float getyAxis() {
        return yAxis;
    }


    public void setyAxis(float yAxis) {
        this.yAxis = yAxis;
    }


    public float getRadius() {
        return radius;
    }


    public void setRadius(float radius) {
        this.radius = radius;
    }


    public int getAngle() {
        return angle;
    }


    public void setAngle(int angle) {
        this.angle = angle;
    }


    public int getOrder() {
        return order;
    }


    public void setOrder(int order) {
        this.order = order;
    }

}