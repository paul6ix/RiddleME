package com.sixtech.paulchidi.riddleme.game;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sixtech.paulchidi.riddleme.R;

public class GameActivity2 extends AppCompatActivity {
    protected ImageButton ibPause;
    protected Button bOk;
    protected ImageButton ibnext;

    protected TextView tvRiddle;
    protected EditText etAnswer;
    public TextView tvScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        Intent intent = getIntent();
        final int score = intent.getExtras().getInt("score");
        gameButtons();
        tvScore = (TextView) findViewById(R.id.textView_gamescore);
        String sscore = Integer.toString(score);
        tvScore.setText(sscore);
        tvRiddle = (TextView) findViewById(R.id.textViewRiddle);
        etAnswer = (EditText) findViewById(R.id.editTextAnswer);
        ibnext.setVisibility(View.INVISIBLE);
        String riddle = getResources().getString(R.string.r3);
        tvRiddle.setText(riddle);
        ibnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextRiddle = new Intent(GameActivity2.this, GameActivity3.class);
                int newScore = score + 10;

                nextRiddle.putExtra("score", newScore);

                startActivity(nextRiddle);

            }
        });


    }

    private void gameButtons() {
        ibnext = (ImageButton) findViewById(R.id.btn_next);
        bOk = (Button) findViewById(R.id.btn_ok);
        bOk.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                String ans = etAnswer.getText().toString();
                String correctAns = getResources().getString(R.string.r3_ans);
                if (ans.equalsIgnoreCase(correctAns)) {
                    WinAnimation();
                    ibnext.setVisibility(View.VISIBLE);


                } else {
                    LoseAnimations();
                }

            }
        });
        ibPause = (ImageButton) findViewById(R.id.btn_pause);
        ibPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    protected void WinsoundEffects() {

    }

    protected void WinAnimation() {
        RotateAnimation Ranim = new RotateAnimation(0, 360, 50, 50);
        Ranim.setDuration(1500);
        bOk.startAnimation(Ranim);
        tvRiddle.setText(R.string.win_text);
        tvRiddle.setTextColor(getResources().getColor(R.color.button_win_textcolor));


    }

    protected void LoseSoundEffects() {

    }

    protected void LoseAnimations() {
        tvRiddle.setText(R.string.lose_txt);
        tvRiddle.setTextColor(getResources().getColor(R.color.lose_textcolor));

    }
}
