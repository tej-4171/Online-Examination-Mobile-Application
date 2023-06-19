package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ExamPage extends AppCompatActivity {

    String s1, s2, userid;
    Integer fpage;
    TextView e1, e2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_page);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        fpage = intent.getExtras().getInt("key");

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        btn = findViewById(R.id.btn);

        DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");

        fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                s1 = snapshot.child(userid).child("Name").getValue(String.class);
                s2 = snapshot.child(userid).child("Regno").getValue(String.class);

                e1.setText(s1);
                e2.setText(s2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        repeat();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ExamPage.this, "Please Wait Until the Timmer goes", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void repeat()
    {
        TextView textView = findViewById (R.id.timmer);
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                textView.setText("00:00:00");
                finishPage();
            }
        }.start();
    }

    void finishPage(){
        Intent i = new Intent(ExamPage.this, MainActivity2.class);
        i.putExtra("key",fpage);
        i.putExtra("userid", userid);
        finish();
        startActivity(i);
    }
}