package com.sixtech.paulchidi.riddleme.game;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sixtech.paulchidi.riddleme.MainActivity;
import com.sixtech.paulchidi.riddleme.R;

public class GameActivity
        extends AppCompatActivity {
    public TextView tvScore;
    protected ImageButton ibPause;
    protected Button bOk;
    protected ImageButton ibnext;
    protected TextView tvRiddle;
    protected EditText etAnswer;
    protected Intent nextRiddle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_game);

        gameButtons();
        tvScore = (TextView) findViewById(R.id.textView_gamescore);
        tvRiddle = (TextView) findViewById(R.id.textViewRiddle);
        etAnswer = (EditText) findViewById(R.id.editTextAnswer);
        ibnext.setVisibility(View.INVISIBLE);
        String riddle = getResources().getString(R.string.r1);
        tvRiddle.setText(riddle);
        ibnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextRiddle = new Intent(GameActivity.this, GameActivity1.class);
                int gamescore = 10;
                nextRiddle.putExtra("score", gamescore);


                startActivity(nextRiddle);

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void gameButtons() {
        ibnext = (ImageButton) findViewById(R.id.btn_next);
        bOk = (Button) findViewById(R.id.btn_ok);
        bOk.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                String ans = etAnswer.getText().toString();
                String correctAns = getResources().getString(R.string.r1_ans);
                if (ans.equalsIgnoreCase(correctAns)) {
                    WinAnimation();
                    ibnext.setVisibility(View.VISIBLE);
                    WinsoundEffects();


                } else {
                    LoseAnimations();
                    LoseSoundEffects();
                    etAnswer.setText("");
                }

            }
        });

        ibPause = (ImageButton) findViewById(R.id.btn_pause);
        ibPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    protected void WinsoundEffects() {
        final MediaPlayer mpGame = MediaPlayer.create(this, R.raw.audience_applause);
        mpGame.start();
        mpGame.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mpGame.release();
            }
        });

    }

    protected void WinAnimation() {
        RotateAnimation Ranim = new RotateAnimation(0, 360, 50, 50);
        Ranim.setDuration(1500);
        bOk.startAnimation(Ranim);
        tvRiddle.setText(R.string.win_text);
        tvRiddle.setTextColor(getResources().getColor(R.color.button_win_textcolor));
        ibnext.setVisibility(View.VISIBLE);
        int score = 0;
        score += 10;
        String sScore = Integer.toString(score);
        tvScore.setText(sScore);
    }

    protected void LoseSoundEffects() {
        final MediaPlayer mpGame = MediaPlayer.create(this, R.raw.sad_trombone);
        mpGame.start();
        mpGame.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mpGame.release();
                tvRiddle.setText(getResources().getString(R.string.r1));
            }
        });
    }

    protected void LoseAnimations() {
        tvRiddle.setText(R.string.lose_txt);
        tvRiddle.setTextColor(getResources().getColor(R.color.lose_textcolor));
    }


}
