package com.lenovo.watchface;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mWatchFace1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWatchFace1 = (Button) findViewById(R.id.bt_face1);

        mWatchFace1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,LeCrash.class);
        switch (view.getId()){
            case R.id.bt_face1:
                intent.putExtra("WatchFace",1);
                startActivity(intent);
                break;
        }
    }
}
