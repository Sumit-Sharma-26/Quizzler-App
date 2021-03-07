package com.example.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    // disabling the back button of android phone
    @Override
    public void onBackPressed() { }

    TextView t, t_info;
    Button b;
    Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        t = findViewById(R.id.textView);
        t_info = findViewById(R.id.info);
        b = findViewById(R.id.back);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent i2 = getIntent();
        String[] user = i2.getStringExtra("k").split(" ;;", 7);
        String name = user[0];
        String score = user[1];
        String correct = user[2];
        String incorrect = user[3];

        String ss = "<b>" + "Final Results : " + "</b>";
        t.setText(Html.fromHtml(ss));
        t.append("\n\n");

        if(Integer.parseInt(score) < 0)
            score = "0";

        t.append("Dear " + name + ", you obtained " + score + " marks out of 14.\n");
        double per = (Double.parseDouble(score)/14.0)*100;
        t.append("\nCorrect attempts : " + correct);
        t.append("\nIncorrect attempts : " + incorrect);
        t.append("\n\n           ---  Percentage : " + String.format(String.valueOf(per), "%.2f") + "%  ---");

        String xx = " Hope You Enjoyed!, if you are attempt it again then press Play Again button and Submit your good name Thank You. \n\n Designed By :\n   Sumit Sharma\n   +91-9549582521\n";
        xx += "  (sumit.sharma.biet@gmail.com)";
        t_info.setText(xx);
    }

    public void back(View obj){
        v.vibrate(20);
        Intent i = new Intent(Result.this, StartActivity.class);
        startActivity(i);
    }
}
