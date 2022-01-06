package com.example.ttt;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {
    private static int sp=2800;
    TextView t;
    LottieAnimationView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        a=findViewById(R.id.b);
        t=findViewById(R.id.t1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash.this,MainActivity.class));
                finish();
            }
        },sp);
    }
}