package com.lenovo.watchface;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class LeBallSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private LeDrawThread drawThread;
    private LeBallThread ballThread;

    private Bitmap bg;
    private Bitmap[] bitmaps;

    private LeBall moveBall;
    private int mapCount = 0;
    private int ballCount = 0;
    private int currentCount = 0;

    public static int[] colors = new int[]{Color.BLACK,Color.DKGRAY,Color.GRAY,Color.LTGRAY,
                                            Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,
                                            Color.CYAN,Color.MAGENTA};

    public static List<LeBall> ballList = new Vector();

    public LeBallSurfaceView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        getHolder().addCallback(this);
        initBitmaps(getResources());
        addMoveBall(20, 20);
        drawThread = new LeDrawThread(this, getHolder());
        ballThread = new LeBallThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if (!drawThread.isAlive()) {
            drawThread.start();
        }
        if (!ballThread.isAlive()) {
            ballThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        ballList.clear();
        ballList = null;
        drawThread.setRunning(false);
        drawThread = null;
        ballThread.setRunning(false);
        ballThread = null;
    }

    public void doDraw(Canvas canvas) {
        canvas.drawBitmap(bg, 0, 0, null);
        if (null == ballList)
            return;
        for (LeBall mb : ballList) {
            mb.draw(canvas);
        }
    }

    private void initBitmaps(Resources r) {
        // TODO Auto-generated method stub
        bg = BitmapFactory.decodeResource(r, R.mipmap.bg);
        bitmaps = new Bitmap[] {
                BitmapFactory.decodeResource(r, R.mipmap.ic_launcher),
                BitmapFactory.decodeResource(r, R.mipmap.ic_launcher),
                BitmapFactory.decodeResource(r, R.mipmap.ic_launcher),
                BitmapFactory.decodeResource(r, R.mipmap.ic_launcher),
                BitmapFactory.decodeResource(r, R.mipmap.ic_launcher)};
        mapCount = bitmaps.length;
    }

    public void addMoveBall(float x, float y) {
        if (null == ballList)
            ballList = new Vector();
        int color = colors[new Random().nextInt(colors.length)];
        Bitmap bitmap = createBall(color);
        //moveBall = new LeBall(x, y, LeCrashValues.ballSize / 2, ballCount, bitmaps[new Random().nextInt(mapCount)]);
        moveBall = new LeBall(x, y, LeCrashValues.ballSize / 2, ballCount, bitmap);
        moveBall.color = color;
        ballList.add(moveBall);
        ballCount ++;
        currentCount ++;
        Log.v(this.getClass().getName(), "current ball count="+currentCount);
    }

    private Bitmap createBall(int color)
    {
        OvalShape circle = new OvalShape();
        ShapeDrawable drawable = new ShapeDrawable(circle);
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
        return bitmap;
    }

    public void removeMoveBall(LeBall mb) {
        ballList.remove(mb);
        mb = null;
        currentCount --;
        Log.v(this.getClass().getName(), "current ball count="+currentCount);
    }
}