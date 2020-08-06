package com.akshaypatil.touchscreencounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button - btnMinus
        Button btnMinus = (Button)findViewById(R.id.btnMinus);
        btnMinus.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DecrementCount(motionEvent);
                return false;
            }
        });

        //Button - btnReset
        Button btnReset = (Button)findViewById(R.id.btnReset);
        btnReset.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ResetCount(motionEvent);
                return false;
            }
        });
        //MyTimer timer = new MyTimer(5000,1000,((TextView)findViewById(R.id.txtInfo)),((TextView)findViewById(R.id.txtInfo)));
        mPrefs = getSharedPreferences("count", 0);
        count = mPrefs.getInt("count",0);
        PrintCount();
    }


    void PrintCount() {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("count", count).commit();
        TextView txtCount = ((TextView) findViewById(R.id.txtCount));
        txtCount.setText(String.valueOf(count));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_UP) {
            count++;
            PrintCount();
        }
        return true;
    }

    private void DecrementCount(MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP)
        {
            if (count > 0) {
                count--;
                PrintCount();
            }
        }
    }

    private void ResetCount(MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP)
        {
            count = 0;
            PrintCount();
        }
    }
}