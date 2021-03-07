package com.example.quizzler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Termpage extends AppCompatActivity {
    public void onBackPressed() { }

    Vibrator v;
    String fullName;
    TextView t, terms;
    CheckBox c;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_termpage);

        t = findViewById(R.id.username);
        terms = findViewById(R.id.terms);
        c = findViewById(R.id.checkBox);
        b = findViewById(R.id.start);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /* Displaying Name of user with Welcome */
        Intent i2 = getIntent();
        fullName = i2.getStringExtra("k");
        String[] user = i2.getStringExtra("k").split(" ", 2);
        final String name = user[0].substring(0,1).toUpperCase() + user[0].substring(1).toLowerCase();
        t.setText("Welcome "+ name);

        String ss = "<b>" + "Terms for Examination : " + "</b>";
        terms.setText(Html.fromHtml(ss));
        terms.append("\n\n");

        terms.append("  (1) All questions have single correct answer.\n");
        terms.append("  (2) Each correct attempt will give you +2 marks.\n");
        terms.append("  (3) Each incorrect attempt will give you -1 mark.\n");
        terms.append("  (4) Use of Internet is permitted.\n");
        terms.append("  (5) There are total 7 question in the quiz.\n");
        terms.append("  (6) Pressing 'prev' button will take you to previous question and CLEARS your previous submission. So, you NEED to attempt that question again.\n");
        terms.append("  (7) Result will be shown once you complete the exam.\n");
        terms.append("    --- --- --- --- --- --- ---\n");
        terms.append("  All the best ...\n");
    }

    public void nextpage(View obj){


        if(c.isChecked()){
            Intent i = new Intent(Termpage.this, QuestionPage.class);
            i.putExtra("k", fullName);
            startActivity(i);
        }
        else {
            Toast.makeText(Termpage.this, "Accept the Terms", Toast.LENGTH_SHORT).show();
        }
        v.vibrate(20);
    }
}