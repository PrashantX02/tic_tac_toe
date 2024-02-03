package com.example.xo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    EditText inputx;
    EditText inputo;

    TextView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inputo = findViewById(R.id.input_o);
        inputx = findViewById(R.id.input_x);
        start = findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = inputo.getText().toString();
                String m2 = inputx.getText().toString();

                if(m1.trim().equals("")|| m2.trim().equals("")) {
                    if (m1.equals("")) {
                        inputo.setHint("Enter name of O");
                        inputo.setHintTextColor(getColor(R.color.red));
                        inputo.setBackgroundResource(R.drawable.toprectwrong);
                    }
                    if (m2.equals("")) {
                        inputx.setHint("Enter name of X");
                        inputx.setHintTextColor(getColor(R.color.red));
                        inputx.setBackgroundResource(R.drawable.toprectwrong);
                    }
                }
                else if(!m2.equals("") && !m1.equals(m2)){
                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);

                    intent.putExtra("keyx", m1);
                    intent.putExtra("keyo", m2);

                    startActivity(intent);

                    finish();
                }
            }
        });

    }
}