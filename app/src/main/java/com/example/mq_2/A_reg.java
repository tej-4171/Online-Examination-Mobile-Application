package com.example.mq_2;

        import androidx.activity.result.contract.ActivityResultContracts;
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

public class A_reg extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5;
    Button btn1, btn2;
    String s1, s2, s3, s4, s5;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areg);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.regno);
        e3 = findViewById(R.id.email);
        e4 = findViewById(R.id.password);
        e5 = findViewById(R.id.pass);

        btn1 = findViewById(R.id.signup);
        btn2 = findViewById(R.id.admin);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s1 = e1.getText().toString();
                s2 = e2.getText().toString(); s2 = s2.toUpperCase();
                s3 = e3.getText().toString();
                s4 = e4.getText().toString();
                s5 = e5.getText().toString();




                if( s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() ){
                    Toast.makeText(A_reg.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");

                    fdb.child("QAdmin").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(s2)){
                                Toast.makeText(A_reg.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if( s4.equals(s5) ){
                                    fdb.child("QAdmin").child(s2).child("Name").setValue(s1);
                                    fdb.child("QAdmin").child(s2).child("Regno").setValue(s2);
                                    fdb.child("QAdmin").child(s2).child("Email").setValue(s3);
                                    fdb.child("QAdmin").child(s2).child("Password").setValue(s4);


                                    Toast.makeText(A_reg.this, "details are stored", Toast.LENGTH_SHORT).show();

                                    e1.setText("");
                                    e2.setText("");
                                    e3.setText("");
                                    e4.setText("");
                                    e5.setText("");
                                }
                                else
                                    Toast.makeText(A_reg.this, "password is not matched", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(A_reg.this, Admin.class);
                i2.putExtra("userid", userid);
                startActivity(i2);
            }
        });


    }
}