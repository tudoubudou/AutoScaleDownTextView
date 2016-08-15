package com.example.ziwenguo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView v = (TextView) findViewById(R.id.scaled);
        //addText
        v.setText("Hello");
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        v.setText(v.getText().toString() + v.getText().toString());
                    }
                });
            }
        },1000,1000);

        //removeText
        //final Timer timer = new Timer();
        //timer.scheduleAtFixedRate(new TimerTask() {
        //    @Override
        //    public void run() {
        //        v.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                if (v.getText().length() > 6) {
        //                    v.setText(TextUtils.substring(v.getText().toString(),5,v.getText().length()));
        //                } else {
        //                    timer.cancel();
        //                }
        //            }
        //        });
        //    }
        //},1000,1000);
    }
}
