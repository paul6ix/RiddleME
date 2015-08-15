package com.sixtech.paulchidi.riddleme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity
        extends Activity {
    protected TextView tvSplashText;
    protected View vSplashlayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvSplashText = ((TextView) findViewById(R.id.textView));
        vSplashlayout = findViewById(R.id.splashLayout);
        Animation scaleText = AnimationUtils.loadAnimation(this, R.anim.animate_splash_text);
        tvSplashText.setAnimation(scaleText);
        vSplashlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        System.exit(1);
        super.onBackPressed();
    }
}
