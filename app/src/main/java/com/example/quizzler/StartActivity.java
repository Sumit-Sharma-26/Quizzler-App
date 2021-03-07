package com.example.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {



    TextView user;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        user = findViewById(R.id.username);
        b = findViewById(R.id.Login);



        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ( TextUtils.isEmpty(user.getText())){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "The name field are required to move ahead !",
                            Toast.LENGTH_SHORT);toast.show();
                    user.setError( "Your name is required!" );
                }else {
                    Intent intent = new Intent(StartActivity.this, Termpage.class);
                    String name = String.valueOf(user.getText());
                    intent.putExtra("k", name);
                    startActivity(intent);
                }
            }

        });

    }

}