package edu.northeastern.numad22fa_kexinqiu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    private Button btn;
    private Button aboutMe_btn;
    private Button clicky_btn;
    private Button linkCollector_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn = (Button)findViewById(R.id.about_me_btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),
//                               "Name: Kexin Qiu" +
//                                       "\nEmail: qiu.ke@northeastern.edu",
//                                Toast.LENGTH_LONG)
//                        .show();
//            }
//        });

        //aboutme
        aboutMe_btn = (Button)findViewById(R.id.about_me_btn);
        aboutMe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutMe();
            }
        });

        //clicky
        clicky_btn = (Button)findViewById(R.id.clicky_btn);
        clicky_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicky();
            }
        });

        linkCollector_btn = (Button)findViewById(R.id.link_collector_btn);
        linkCollector_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkCollector();
            }
        });
    }

    public void AboutMe() {
        Intent newActivity = new Intent(MainActivity.this, AboutMeActivity.class);
        startActivity(newActivity);
    }

    public void Clicky() {
        Intent newActivity = new Intent(MainActivity.this, ClickyActivity.class);
        startActivity(newActivity);
    }

    public void LinkCollector() {
        Intent newActivity = new Intent(MainActivity.this, LinkCollectorActivity.class);
        startActivity(newActivity);
    }

}