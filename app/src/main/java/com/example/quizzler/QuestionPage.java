package com.example.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionPage extends AppCompatActivity {




    // disabling the back button of android phone
    @Override
    public void onBackPressed() { }

    String questions[] = {
            "(1) Who developed Python ?",
            "(2) What is full-form of FIFA (football) ?",
            "(3) Which module is used to provide GUI in Python Applications ? ",
            "(4) Who developed Android ?",
            "(5) What is Android version name starting with alphabet 'H' ?",
            "(6) What is latest Version of Android ?",
            "(7) If a computer has more than one processor then it is known as?"
    };
    String options[] = {
            "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum", "None of these",
            "Federation Internationale de Football Association", "Final Intercontinental Football Association", "Federation of International Football Associations", "None of these",
            "sklearn", "openCV", "tkinter", "BeautifulSoup",
            "Bjarne Stroustrup", "Andy Rubin", "Robert Roode", "None of these",
            "Honeybee", "Honey", "Honeymoon", "Honeycomb",
            "Marshmallow", "Nought", "Jellybee", "Q",
            "Uniprocess", "Multiprocessor", "Multithreaded", "Multiprogramming"
    };
    String answers[] = {
            "Guido van Rossum", "Federation Internationale de Football Association",
            "tkinter", "Andy Rubin", "Honeycomb", "Q", "Multiprocessor"
    };

    TextView t;
    RadioGroup rg;
    RadioButton a, b, c, d;
    Button bt, bt_prev, bt_end, bt_skip;


    int score = 0;
    int correct=0, incorrect=0;
    int flag = 0;
    int[] marks = {0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question_page);


        // user-name
        Intent i2 = getIntent();
        final String fullname = i2.getStringExtra("k").toUpperCase();

        t = findViewById(R.id.question);
        rg = findViewById(R.id.options);
        a = findViewById(R.id.option1);
        b = findViewById(R.id.option2);
        c = findViewById(R.id.option3);
        d = findViewById(R.id.option4);
        bt = findViewById(R.id.next);
        bt_prev = findViewById(R.id.prev);
        bt_end = findViewById(R.id.endtest);
        bt_skip = findViewById(R.id.skip);

        t.setText(questions[flag]);
        a.setText(options[0]);
        b.setText(options[1]);
        c.setText(options[2]);
        d.setText(options[3]);
        bt_end.setVisibility(View.INVISIBLE);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_end.setVisibility(View.INVISIBLE);
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(QuestionPage.this, "Select an option or Press 'End Test'", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton rr = findViewById(rg.getCheckedRadioButtonId());
                String ans = rr.getText().toString();
                if(ans.equals(answers[flag])){
                    score += 2;
                    marks[flag] = 2;
                    correct++;
                }
                else{
                    score -= 1;
                    marks[flag] = -1;
                    incorrect++;
                }
                if(flag != questions.length-1)
                    Toast.makeText(QuestionPage.this, "Response submitted", Toast.LENGTH_SHORT).show();
                flag++;
                if(flag < questions.length)
                {
                    t.setText(questions[flag]);
                    a.setText(options[flag*4]);
                    b.setText(options[flag*4 +1]);
                    c.setText(options[flag*4 +2]);
                    d.setText(options[flag*4 +3]);
                }
                else{
                    Toast.makeText(QuestionPage.this,
                            "Response submitted... No more questions. Press 'End Test' to see your result.",
                            Toast.LENGTH_LONG).show();
                    bt_end.setVisibility(View.VISIBLE);
                    bt.setEnabled(false);
                }
                if(flag != questions.length)
                    rg.clearCheck();
            }
        });

        // previous question
        bt_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_skip.setEnabled(true);
                bt.setEnabled(true);
                if(flag != 0){
                    flag--;
                    // reverting marks as well
                    int ss = marks[flag];
                    if(ss == -1)    {score++;incorrect--;}
                    if(ss == 4)     {score-=2;correct--;}
                    t.setText(questions[flag]);
                    a.setText(options[flag*4]);
                    b.setText(options[flag*4 +1]);
                    c.setText(options[flag*4 +2]);
                    d.setText(options[flag*4 +3]);
                }else{
                    Toast.makeText(QuestionPage.this, "No previous questions !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //end-test button clicked
        bt_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuestionPage.this, Result.class);
                String pass = fullname + " ;;" + score + " ;;" + correct + " ;;" + incorrect;
                i.putExtra("k", pass);
                startActivity(i);
            }
        });

        // skip question
        bt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag++;
                if(flag < questions.length)
                {
                    t.setText(questions[flag]);
                    a.setText(options[flag*4]);
                    b.setText(options[flag*4 +1]);
                    c.setText(options[flag*4 +2]);
                    d.setText(options[flag*4 +3]);
                }
                else{
                    bt_skip.setEnabled(false);
                    flag--;
                    bt_end.setVisibility(View.VISIBLE);
                }
                System.out.println(score);
            }
        });
    }



}