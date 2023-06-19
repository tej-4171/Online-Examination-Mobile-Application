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


public class MainActivity extends AppCompatActivity {            //implements View.OnClickListener {

    // creating variables for our edit text and buttons.
    EditText userNameEdt, e1, e2;
    Button loginBtn, registerbtn;
    //int currentUserIndex=0;
    //AlertDialog.Builder builder;
    String s_user, userpass;
    LinearLayout admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our edit text and buttons.
        userNameEdt = findViewById(R.id.id1);
        e1 = findViewById(R.id.id1);
        e2 = findViewById(R.id.id2);
        loginBtn = findViewById(R.id.id3);
        registerbtn = findViewById(R.id.id4);
        admin = findViewById(R.id.admin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(MainActivity.this, A_log.class);
                finish();
                startActivity(i4);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MainActivity.this, register.class);
                startActivity(i3);
            }
        });

        //loginBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                s_user = userNameEdt.getText().toString();
                s_user = s_user.toUpperCase();

                if(s_user.contains("~") || s_user.contains("`") ||  s_user.contains("!") || s_user.contains("@") || s_user.contains("#") || s_user.contains("$") || s_user.contains("%") || s_user.contains("^") || s_user.contains("&") || s_user.contains("*") ||  s_user.contains("(") || s_user.contains(")") || s_user.contains("_") || s_user.contains("-") || s_user.contains("+") || s_user.contains("=") || s_user.contains("{") || s_user.contains("}") ||  s_user.contains("[") || s_user.contains("]") || s_user.contains(":") || s_user.contains(";") || s_user.contains("<") || s_user.contains(">") || s_user.contains(",") || s_user.contains(".") ||  s_user.contains("?") || s_user.contains("/") || s_user.contains("|") || s_user.contains("'") )
                {
                    //e1.setError("enter valid RegNO");
                    Toast.makeText(MainActivity.this, "enter valid RegNo", Toast.LENGTH_SHORT).show();
                }

                else {
                    DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");


                    fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(s_user)) {


                                String user = e1.getText().toString();
                                user = user.toUpperCase();
                                String pass = e2.getText().toString();

                                userpass = snapshot.child(user).child("Password").getValue(String.class);

                                if (user.isEmpty() || pass.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "please fill the empty fields", Toast.LENGTH_SHORT).show();
                                } else if (userpass.equals(pass)) {
                                    Intent i = new Intent(MainActivity.this, home.class);
                                    i.putExtra("userid", user);
                                    startActivity(i);

                                    e1.setText("");
                                    e2.setText("");
                                } else {
                                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(MainActivity.this, "user not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

    }


    /*
    @Override
    public void onClick(View view){


        Button clickedButton = (Button) view;
        String str1 = userNameEdt.getText().toString();
        str1 = str1.toUpperCase();

        if (clickedButton.getId() == R.id.id3) {
           // Toast.makeText(MainActivity.this, "hii " + str1 , Toast.LENGTH_SHORT).show();
            currentUserIndex=0;
            if (TextUtils.isEmpty(str1)) {
                Toast.makeText(MainActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
            }
            else {
                while (currentUserIndex != userid.users.length) {
                    // Toast.makeText(MainActivity.this, " help " + userid.users[currentUserIndex] , Toast.LENGTH_SHORT).show();
                    if (str1.equals(userid.users[currentUserIndex])) {
                        Intent intent = new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                        currentUserIndex = 0;
                        break;
                    } else {
                        currentUserIndex++;
                    }
                }
                if(currentUserIndex!=0){
                    Toast.makeText(MainActivity.this, "Please enter valid user name", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    */


}