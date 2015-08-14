package com.sixtech.paulchidi.riddleme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sixtech.paulchidi.riddleme.game.GameActivity;

public class MainActivity
        extends AppCompatActivity {
    protected Button bSettigns;
    protected Button bExit;
    protected Button bHelp;
    protected Button bNewGame;
    protected Button bResume;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuButtons();
        if (savedInstanceState == null) {
            this.bResume.setVisibility(View.GONE);
        } else {
            this.bResume.setVisibility(View.VISIBLE);
        }
    }

    protected void MenuButtons() {
        bResume = ((Button) findViewById(R.id.btn_resume));
        bSettigns = ((Button) findViewById(R.id.btn_settings));
        bExit = ((Button) findViewById(R.id.btn_exit));
        bNewGame = ((Button) findViewById(R.id.btn_new_game));
        bHelp = ((Button) findViewById(R.id.btn_help));
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }


        });

        bNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);

            }
        });

        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog();

            }
        });

        bSettigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AlertDialog() {
        AlertDialog.Builder ExitDialog = new AlertDialog.Builder(this);
        ExitDialog.setMessage(R.string.exit_message);
        ExitDialog.setTitle(R.string.exit_title);

        ExitDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        ExitDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });

        Dialog dialog = ExitDialog.create();
        dialog.show();
    }
}


