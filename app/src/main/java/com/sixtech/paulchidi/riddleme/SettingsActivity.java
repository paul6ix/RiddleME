package com.sixtech.paulchidi.riddleme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

public class SettingsActivity
        extends AppCompatActivity {
    protected ToggleButton tbSoundSettings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.tbSoundSettings = ((ToggleButton) findViewById(R.id.toggleSound));
    }


}
