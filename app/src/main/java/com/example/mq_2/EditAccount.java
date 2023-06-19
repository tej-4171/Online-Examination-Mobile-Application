package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAccount extends AppCompatActivity {

    EditText e1, e3, e4;
    TextView e2;
    String userid, name, regno, email, mobile;
    Button update;
    ProgressDialog progressBar;
        private int progressBarStatus = 0;
        private Handler progressBarHandler = new Handler();
        private long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        load();

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        update = findViewById(R.id.btn);



        DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");

        fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                name = snapshot.child(userid).child("Name").getValue(String.class);
                regno = snapshot.child(userid).child("Regno").getValue(String.class);
                email = snapshot.child(userid).child("Email").getValue(String.class);
                mobile = snapshot.child(userid).child("Mobile").getValue(String.class);

                e1.setText(name);
                e2.setText(regno);
                e3.setText(email);
                e4.setText(mobile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        name = e1.getText().toString();
                        email = e3.getText().toString();
                        mobile = e4.getText().toString();

                        fdb.child("QUser").child(userid).child("Name").setValue(name);
                        fdb.child("QUser").child(userid).child("Email").setValue(email);
                        fdb.child("QUser").child(userid).child("Mobile").setValue(mobile);


                        Toast.makeText(EditAccount.this, "details are stored", Toast.LENGTH_SHORT).show();

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

