package edu.northeastern.numad22fa_kexinqiu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ClickyActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        TextView pressed = (TextView)findViewById(R.id.textViewPressed);

        Button btnA = (Button)findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed.setText("Pressed: A");
            }
        });

        Button btnB = (Button)findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed.setText("Pressed: B");
            }
        });

        Button btnC = (Button)findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed.setText("Pressed: C");
            }
        });

        Button btnD = (Button)findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed.setText("Pressed: D");
            }
        });

        Button btnE = (Button)findViewById(R.id.btnE);
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed.setText("Pressed: E");
            }
        });

        Button btnF = (Button)findViewById(R.id.btnF);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed.setText("Pressed: F");
            }
        });
    }

}
