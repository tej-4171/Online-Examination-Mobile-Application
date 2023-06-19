package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class A_EditPassword extends AppCompatActivity {

    EditText e1, e2, e3;

    String s1, s2, s3, userid, userpass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aedit_password);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");


                fdb.child("QAdmin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        s1 = e1.getText().toString();
                        s2 = e2.getText().toString();
                        s3 = e3.getText().toString();

                        userpass = snapshot.child(userid).child("Password").getValue(String.class);

                        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                            Toast.makeText(A_EditPassword.this, "please fill the empty fields", Toast.LENGTH_SHORT).show();
                        }
                        else if (userpass.equals(s1)) {

                            if(s2.equals(s3)){

                                fdb.child("QAdmin").child(userid).child("Password").setValue(s2);
                                Toast.makeText(A_EditPassword.this, "details updated", Toast.LENGTH_SHORT).show();

                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                            }
                            else {
                                Toast.makeText(A_EditPassword.this, "New Password is not matched", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(A_EditPassword.this, "Updation Failed! Wrong Password", Toast.LENGTH_SHORT).show();
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