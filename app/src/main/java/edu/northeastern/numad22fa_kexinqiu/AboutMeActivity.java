package edu.northeastern.numad22fa_kexinqiu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {
    private TextView aboutMe_name;
    private TextView aboutMe_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        aboutMe_name = (TextView) findViewById(R.id.aboutme_name);
        aboutMe_email = (TextView) findViewById(R.id.aboutme_email);
    }
}
