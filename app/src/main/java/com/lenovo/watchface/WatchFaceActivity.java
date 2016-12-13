package com.lenovo.watchface;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class WatchFaceActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchface);
        int face = getIntent().getIntExtra("WatchFace",-1);

        FrameLayout contain = (FrameLayout) findViewById(R.id.contain);

        /* 判断WatchFace类型 */
        if(face == 1){
        }
    }
}
