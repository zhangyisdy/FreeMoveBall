package com.lenovo.watchface;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class LeCrash extends Activity {

    LeBallSurfaceView mLeBallSurfaceView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init trigonometric function
        LeCore.getInstance();

        mLeBallSurfaceView = new LeBallSurfaceView(this);
        mLeBallSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        for (LeBall mb : LeBallSurfaceView.ballList) {
                            if (isBallExist(mb, event.getX(), event.getY())) {
                                mLeBallSurfaceView.removeMoveBall(mb);
                                return false;
                            }
                        }
                        mLeBallSurfaceView.addMoveBall(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
        setContentView(mLeBallSurfaceView);
    }

    private boolean isBallExist(LeBall mb, float x, float y) {
        if (x >= mb.getxAxis() && x <= (mb.getxAxis() + LeCrashValues.ballSize) && y >= mb.getyAxis() && y <= (mb.getyAxis() + LeCrashValues.ballSize)){
            return true;
        }
        return false;
    }
}
