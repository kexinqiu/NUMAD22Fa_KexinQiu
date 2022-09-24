package edu.northeastern.numad22fa_kexinqiu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                               "Name: Kexin Qiu" +
                                       "\nEmail: qiu.ke@northeastern.edu",
                                Toast.LENGTH_LONG)
                        .show();
            }
        });

        Button btnClicky = (Button)findViewById(R.id.btnClicky);
        btnClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicky();
            }
        });
    }

    public void Clicky() {
        Intent newActivity = new Intent(MainActivity.this, ClickyActivity.class);
        startActivity(newActivity);
    }

}