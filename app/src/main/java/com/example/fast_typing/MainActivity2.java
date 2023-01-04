package com.example.fast_typing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    static  double total=0;
    static  int i=0;
    static  int progress=0;
    private long lastPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ProgressBar simpleProgressBar=findViewById(R.id.ProgressBar);

        TextView Result=findViewById(R.id.result);

        TextView read=findViewById(R.id.txt1);
        EditText write=findViewById(R.id.ed);

        Button try_again=findViewById(R.id.t_again);
        Button submit=findViewById(R.id.sub);
        Button next=findViewById(R.id.nxt);
        Chronometer mChronometer = findViewById(R.id.time);

        String str[]={"Hello i am using fast typing","zebra is a animal which has black and white strip","pixel 3a XL api 30","domestic animal are cows,goat,sheep etc"};
        simpleProgressBar.setMax(str.length*10);
        read.setText(str[i]);
        if (lastPause != 0){
            mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
        }
        else{
            mChronometer.setBase(SystemClock.elapsedRealtime());
        }

        mChronometer.start();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>=str.length){
                    read.setText("FeedBack:");
                    write.setText("");
                    Toast.makeText(MainActivity2.this, "Thanks for your feedback", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(write.getText().toString().equals(str[i])){
                        total=total+1;
                        progress=progress+10;
                        simpleProgressBar.setProgress(progress);
                    }
                    else {
                        progress=progress+10;
                        simpleProgressBar.setProgress(progress);
                    }

                }
            }
        });
        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;

                if (lastPause != 0){
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
                mChronometer.start();
                i=0;
                progress=0;
                total=0.0;
                read.setText(str[i]);
                write.setText("");
                Result.setText("");
                simpleProgressBar.setProgress(progress);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write.setText("");
                i++;
                if(i<str.length){
                    read.setText(str[i]);
                }
                else{
                    lastPause = SystemClock.elapsedRealtime();
                    mChronometer.stop();
                    Result.setText("Accuracy:\n"+String.valueOf((total/(double) str.length)*100));
                    read.setText("FeedBack");
                    Toast.makeText(MainActivity2.this, "Thanks for using fast typing", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}