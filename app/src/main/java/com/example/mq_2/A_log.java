package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class A_log extends AppCompatActivity {

    EditText userNameEdt, e1, e2;
    Button loginBtn;
    String s_user, userpass;
    LinearLayout ulogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alog);

        // initializing our edit text and buttons.
        userNameEdt = findViewById(R.id.id1);
        e1 = findViewById(R.id.id1);
        e2 = findViewById(R.id.id2);
        loginBtn = findViewById(R.id.id3);
        ulogin = findViewById(R.id.ulogin);

        ulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(A_log.this, MainActivity.class);
                finish();
                startActivity(it);
            }
        });


        //loginBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                s_user = userNameEdt.getText().toString();
                s_user = s_user.toUpperCase();

                DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");


                fdb.child("QAdmin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(s_user)) {


                            String user = e1.getText().toString(); user = user.toUpperCase();
                            String pass = e2.getText().toString();

                            userpass = snapshot.child(user).child("Password").getValue(String.class);

                            if(user.isEmpty() || pass.isEmpty()){
                                Toast.makeText(A_log.this, "please fill the empty fields", Toast.LENGTH_SHORT).show();
                            }
                            else if (userpass.equals(pass)) {
                                Intent i = new Intent(A_log.this, Admin.class);
                                i.putExtra("userid", user);
                                startActivity(i);

                                e1.setText("");
                                e2.setText("");
                            }
                            else {
                                Toast.makeText(A_log.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(A_log.this, "Admin ID not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }



}