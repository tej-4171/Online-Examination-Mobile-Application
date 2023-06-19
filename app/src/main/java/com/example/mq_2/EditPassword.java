package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPassword extends AppCompatActivity {

    EditText e1, e2, e3;

    String s1, s2, s3, userid, userpass;
    Button btn;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        load();

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");


                fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        s1 = e1.getText().toString();
                        s2 = e2.getText().toString();
                        s3 = e3.getText().toString();

                        userpass = snapshot.child(userid).child("Password").getValue(String.class);

                        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                            Toast.makeText(EditPassword.this, "please fill the empty fields", Toast.LENGTH_SHORT).show();
                        }
                        else if (userpass.equals(s1)) {

                            if(s2.equals(s3)){

                                fdb.child("QUser").child(userid).child("Password").setValue(s2);
                                Toast.makeText(EditPassword.this, "details updated", Toast.LENGTH_SHORT).show();

                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                            }
                            else {
                                Toast.makeText(EditPassword.this, "New Password is not matched", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(EditPassword.this, "Updation Failed! Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    void load(){
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Fetching ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        //reset progress bar and filesize status
        progressBarStatus = 0;
        fileSize = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {
                    // performing operation
                    progressBarStatus = doOperation();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                // performing operation if file is downloaded,
                if (progressBarStatus >= 100) {
                    // sleeping for 1 second after operation completed
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // close the progress bar dialog
                    progressBar.dismiss();
                }
            }
        }).start();
    }
    public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 1000) {
                return 10;
            } else if (fileSize == 2000) {
                return 20;
            } else if (fileSize == 3000) {
                return 30;
            } else if (fileSize == 4000) {
                return 40; // you can add more else if
            }
         /* else {
                return 100;
            }*/
        }//end of while
        return 100;
    }//end of doOperation
}