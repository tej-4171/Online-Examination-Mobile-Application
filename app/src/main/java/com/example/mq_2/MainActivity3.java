package com.example.mq_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {


    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    TextView tv_progress;
    ImageView image;
    ProgressBar progressBar;


    int score = 0, index=0, qdata, currentPosition=0;
    int totalquestion = 5;//Question.question.length;
    int currentQuestionIndex;
    String passStatus;
    String str1, str2, userid;

    int fpage, number1, number2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        fpage = intent.getExtras().getInt("key");
        number1 = intent.getExtras().getInt("key1");
        number2 = intent.getExtras().getInt("key2");
        qdata = intent.getExtras().getInt("int1");
        str1 = intent.getStringExtra("str1");
        str2 = intent.getStringExtra("str2");
        userid = intent.getStringExtra("userid");
        passStatus = intent.getStringExtra("key3");
        currentQuestionIndex = qdata;


        image = findViewById(R.id.image);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);


        submitBtn.setOnClickListener(this);


        tv_progress = findViewById (R.id.tv_progress);
        tv_progress.setText(currentPosition + "/" + totalquestion);


        loadNewQuestion();

        if( str1.charAt(index) == '1'){
            if ( str2.charAt(index) == '1') {
                ansA.setBackgroundColor(Color.GREEN);
                image.setImageResource(R.drawable.img10);
            }
            else {
                ansA.setBackgroundColor(Color.RED);
                image.setImageResource(R.drawable.img11);
            }
        }
        else if( str1.charAt(index) == '2'){
            if ( str2.charAt(index) == '1') {
                ansB.setBackgroundColor(Color.GREEN);
                image.setImageResource(R.drawable.img10);
            }
            else {
                ansB.setBackgroundColor(Color.RED);
                image.setImageResource(R.drawable.img11);
            }
        }
        else if( str1.charAt(index) == '3'){
            if ( str2.charAt(index) == '1') {
                ansC.setBackgroundColor(Color.GREEN);
                image.setImageResource(R.drawable.img10);
            }
            else {
                ansC.setBackgroundColor(Color.RED);
                image.setImageResource(R.drawable.img11);
            }
        }
        else if( str1.charAt(index) == '4'){
            if ( str2.charAt(index) == '1') {
                ansD.setBackgroundColor(Color.GREEN);
                image.setImageResource(R.drawable.img10);
            }
            else {
                ansD.setBackgroundColor(Color.RED);
                image.setImageResource(R.drawable.img11);
            }
        }
        else if( str1.charAt(index) == '9'){
            if ( str2.charAt(index) == '0') {
                image.setImageResource(R.drawable.img11);
            }
        }
        else{
            ansA.setBackgroundColor(Color.WHITE);
            ansB.setBackgroundColor(Color.WHITE);
            ansC.setBackgroundColor(Color.WHITE);
            ansD.setBackgroundColor(Color.WHITE);
        }


    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);


        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            currentPosition++;
            progressBar.setProgress(currentPosition);
            tv_progress = findViewById (R.id.tv_progress);
            tv_progress.setText(currentPosition + "/" + totalquestion);


            currentQuestionIndex++;
            currentQuestionIndex=currentQuestionIndex%10;
            index++;
            loadNewQuestion();


            if (index == totalquestion) {
                finishQuiz();
                return;
            }

            if( str1.charAt(index) == '1'){
                if ( str2.charAt(index) == '1') {
                    ansA.setBackgroundColor(Color.GREEN);
                    image.setImageResource(R.drawable.img10);
                }
                else {
                    ansA.setBackgroundColor(Color.RED);
                    image.setImageResource(R.drawable.img11);
                }
            }
            else if( str1.charAt(index) == '2'){
                if ( str2.charAt(index) == '1') {
                    ansB.setBackgroundColor(Color.GREEN);
                    image.setImageResource(R.drawable.img10);
                }
                else {
                    ansB.setBackgroundColor(Color.RED);
                    image.setImageResource(R.drawable.img11);
                }
            }
            else if( str1.charAt(index) == '3'){
                if ( str2.charAt(index) == '1') {
                    ansC.setBackgroundColor(Color.GREEN);
                    image.setImageResource(R.drawable.img10);
                }
                else {
                    ansC.setBackgroundColor(Color.RED);
                    image.setImageResource(R.drawable.img11);
                }
            }
            else if( str1.charAt(index) == '4'){
                if ( str2.charAt(index) == '1') {
                    ansD.setBackgroundColor(Color.GREEN);
                    image.setImageResource(R.drawable.img10);
                }
                else {
                    ansD.setBackgroundColor(Color.RED);
                    image.setImageResource(R.drawable.img11);
                }
            }
            else if( str1.charAt(index) == '9'){
                if ( str2.charAt(index) == '0') {
                    image.setImageResource(R.drawable.img11);
                }
            }
            else{
                ansA.setBackgroundColor(Color.WHITE);
                ansB.setBackgroundColor(Color.WHITE);
                ansC.setBackgroundColor(Color.WHITE);
                ansD.setBackgroundColor(Color.WHITE);
            }

        }

    }

    void loadNewQuestion() {

        if (index == totalquestion) {
            finishQuiz();
            return;
        }


        if ( fpage==0 ) {
            questionTextView.setText(((index + 1) + ". " + Question.question[currentQuestionIndex]));
            ansA.setText(Question.choices[currentQuestionIndex][0]);
            ansB.setText(Question.choices[currentQuestionIndex][1]);
            ansC.setText(Question.choices[currentQuestionIndex][2]);
            ansD.setText(Question.choices[currentQuestionIndex][3]);
        }
        else if( fpage==1 ) {
            questionTextView.setText(((index + 1) + ". " + mc.question[currentQuestionIndex]));
            ansA.setText(mc.choices[currentQuestionIndex][0]);
            ansB.setText(mc.choices[currentQuestionIndex][1]);
            ansC.setText(mc.choices[currentQuestionIndex][2]);
            ansD.setText(mc.choices[currentQuestionIndex][3]);
        } else if (fpage==2) {
            questionTextView.setText(((index + 1) + ". " + ai.question[currentQuestionIndex]));
            ansA.setText(ai.choices[currentQuestionIndex][0]);
            ansB.setText(ai.choices[currentQuestionIndex][1]);
            ansC.setText(ai.choices[currentQuestionIndex][2]);
            ansD.setText(ai.choices[currentQuestionIndex][3]);
        }
        else if (fpage==3) {
            questionTextView.setText(((index + 1) + ". " + cns.question[currentQuestionIndex]));
            ansA.setText(cns.choices[currentQuestionIndex][0]);
            ansB.setText(cns.choices[currentQuestionIndex][1]);
            ansC.setText(cns.choices[currentQuestionIndex][2]);
            ansD.setText(cns.choices[currentQuestionIndex][3]);
        }

    }

    void finishQuiz() {
        //passStatus = "";

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Review completed")
                .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ii) {
                        Intent i = new Intent(MainActivity3.this, finish.class);


                        i.putExtra("key",fpage);
                        i.putExtra("key1",number1);
                        i.putExtra("key2",number2);
                        i.putExtra("int1",qdata);
                        i.putExtra("str1",str1);
                        i.putExtra("str2",str2);
                        i.putExtra("userid", userid);
                        i.putExtra("key3",passStatus);
                        finish();
                        startActivity(i);
                    }
                })
                .setCancelable(false)
                .show();

    }
}
