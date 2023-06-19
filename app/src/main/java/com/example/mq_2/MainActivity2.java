package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {


    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    Random random = new Random();
    TextView tv_progress, timmer;
    ProgressBar progressBar;
    ProgressDialog pd;


    int score = 0, index=0, fpage, currentPosition=0;
    int totalquestion = 5;
    int qdata;//Question.question.length;
    int currentQuestionIndex = random.nextInt(10);
    String selectdAnswer = "", passStatus="", userid;
    String Qindex = "", Sindex = "", Qno="9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        qdata = currentQuestionIndex;

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        fpage = intent.getExtras().getInt("key");


        load();

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        timmer = findViewById(R.id.timmer);
        tv_progress = findViewById (R.id.tv_progress);
        tv_progress.setText(currentPosition + "/" + totalquestion);


       // repeat(index,totalquestion);
        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);


        Button clickedButton = (Button) view;
        if ( (clickedButton.getId() == R.id.submit_btn) ) {

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            currentPosition++;
            progressBar.setProgress(currentPosition);
            tv_progress = findViewById (R.id.tv_progress);
            tv_progress.setText(currentPosition + "/" + totalquestion);

            Qindex = Qindex + Qno;
            if ( (fpage==0) &&  selectdAnswer.equals(Question.correctAnswers[currentQuestionIndex])){
                score++;
                Sindex = Sindex + "1";
            }
            else if( (fpage==1) && selectdAnswer.equals(mc.correctAnswers[currentQuestionIndex])) {
                score++;
                Sindex = Sindex + "1";
            }
            else if ( (fpage==2) && selectdAnswer.equals(ai.correctAnswers[currentQuestionIndex])) {
                score++;
                Sindex = Sindex + "1";
            }
            else if ( (fpage==3) && selectdAnswer.equals(cns.correctAnswers[currentQuestionIndex])) {
                score++;
                Sindex = Sindex + "1";
            }
            else{
                Sindex = Sindex + "0";
            }
            currentQuestionIndex++;
            currentQuestionIndex=currentQuestionIndex%10;
            index++;
            Qno="9";
            loadNewQuestion();


        } else {

            if(clickedButton.getId() == R.id.ans_A) {
                Qno = "1";
                clickedButton.setBackgroundColor(Color.MAGENTA);
            }
            else if(clickedButton.getId() == R.id.ans_B){
                Qno="2";
                clickedButton.setBackgroundColor(Color.MAGENTA);
            }
            else if(clickedButton.getId() == R.id.ans_C) {
                Qno = "3";
                clickedButton.setBackgroundColor(Color.MAGENTA);
            }
            else if(clickedButton.getId() == R.id.ans_D) {
                Qno = "4";
                clickedButton.setBackgroundColor(Color.MAGENTA);
            }
            else
                Qno="9";


            selectdAnswer = clickedButton.getText().toString();

        }

    }

    void loadNewQuestion() {

        if (index == totalquestion) {
            finishQuiz();
            return;
        }


        if( fpage==0 ) {
            questionTextView.setText(((index + 1) + ". " + Question.question[currentQuestionIndex]));
            ansA.setText(Question.choices[currentQuestionIndex][0]);
            ansB.setText(Question.choices[currentQuestionIndex][1]);
            ansC.setText(Question.choices[currentQuestionIndex][2]);
            ansD.setText(Question.choices[currentQuestionIndex][3]);
        }
        else if(fpage==1){
            questionTextView.setText(((index + 1) + ". " + mc.question[currentQuestionIndex]));
            ansA.setText(mc.choices[currentQuestionIndex][0]);
            ansB.setText(mc.choices[currentQuestionIndex][1]);
            ansC.setText(mc.choices[currentQuestionIndex][2]);
            ansD.setText(mc.choices[currentQuestionIndex][3]);
        }
        else if (fpage==2) {
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
        passStatus = "";
        if (score >= totalquestion * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        if(fpage==0){

            DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");


            fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    fdb.child("QUser").child(userid).child("Marks").setValue(score*10);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is " + score + " out of " + totalquestion)
                    .setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            Intent intent = new Intent(MainActivity2.this, finish.class);
                            intent.putExtra("key", fpage);
                            intent.putExtra("key1", totalquestion);
                            intent.putExtra("key2", score);
                            intent.putExtra("key3", passStatus);
                            intent.putExtra("int1", qdata);
                            intent.putExtra("str1", Qindex);
                            intent.putExtra("str2", Sindex);
                            intent.putExtra("userid", userid);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        else {
            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is " + score + " out of " + totalquestion)
                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
                            intent.putExtra("key", fpage);
                            intent.putExtra("userid", userid);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Toast.makeText(MainActivity2.this, "qdata", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity2.this, Qindex, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity2.this, Sindex, Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(MainActivity2.this, finish.class);
                            intent.putExtra("key", fpage);
                            intent.putExtra("key1", totalquestion);
                            intent.putExtra("key2", score);
                            intent.putExtra("key3", passStatus);
                            intent.putExtra("int1", qdata);
                            intent.putExtra("str1", Qindex);
                            intent.putExtra("str2", Sindex);
                            intent.putExtra("userid", userid);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = random.nextInt(7);
        index=0;

        currentPosition=0;
        progressBar.setProgress(currentPosition);
        tv_progress = findViewById (R.id.tv_progress);
        tv_progress.setText(currentPosition + "/" + totalquestion);

        loadNewQuestion();
    }

    void repeat(int index, int totalquestion)
    {
        int s = index;
        int t = totalquestion;
        TextView textView = findViewById (R.id.timmer);
        CountDownTimer countDownTimer = new CountDownTimer(50000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

            }
          /*  if(s==t){

            }*/
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                textView.setText("00:00:00");
                finishQuiz();
            }
        }.start();
    }

    void load(){
        pd= new ProgressDialog(MainActivity2.this);
        pd.setMessage("Loading..."); // Setting Message
        pd.setTitle("Exam Page"); // Setting Title
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        pd.show(); // Display Progress Dialog
        pd.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }).start();
    }

}
