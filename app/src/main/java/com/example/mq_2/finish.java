package com.example.mq_2;

// Import the required libraries
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class finish
        extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    TextView tvR, tvPython, Total, status, tvCPP, tvJava;
    ImageView share;
    PieChart pieChart;
    int a, b, total, fpage;
    String passStatus, userid, id, body;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        // tvCPP = findViewById(R.id.tvCPP);
        // tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);
        Total = findViewById(R.id.total);
        status = findViewById(R.id.status);
        share = findViewById(R.id.share);

        Intent intent = getIntent();
        fpage = intent.getExtras().getInt("key");
        int number1 = intent.getExtras().getInt("key1");
        int number2 = intent.getExtras().getInt("key2");
        int qdata = intent.getExtras().getInt("int1");
        String str1 = intent.getStringExtra("str1");
        String str2 = intent.getStringExtra("str2");
        userid = intent.getStringExtra("userid");
        passStatus = intent.getStringExtra("key3");
        total = number1;
        a = number2;
        b = total - a;

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();

        DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");


        fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                id = snapshot.child(userid).child("Regno").getValue(String.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                body = userid + ",\nYour Score - " + number2 * 10 + "\nStatus\t     - " + passStatus;
                String sub = "Your Subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                if(fpage==1 || fpage==2 || fpage==3){
                    Intent i = new Intent(finish.this, folder.class);
                    i.putExtra("key",fpage);
                    i.putExtra("userid", userid);
                    finish();
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(finish.this, home.class);
                    i.putExtra("userid", userid);
                    finish();
                    startActivity(i);
                }
            }
        });

        Button Review = findViewById(R.id.review);
        Review.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent i = new Intent(finish.this, MainActivity3.class);
                i.putExtra("key",fpage);
                i.putExtra("key1",number1);
                i.putExtra("key2",number2);
                i.putExtra("int1",qdata);
                i.putExtra("str1",str1);
                i.putExtra("str2",str2);
                i.putExtra("userid", userid);
                i.putExtra("key3",passStatus);
                startActivity(i);
            }
        });

    }

    private void setData()
    {

        // Set the percentage of language used
        tvR.setText(Integer.toString(a));
        tvPython.setText(Integer.toString(b));
        Total.setText((Integer.toString(total)));
        status.setText(""+passStatus);

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#FFA726")));


        // To animate the pie chart
        pieChart.startAnimation();
    }

}
