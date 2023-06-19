package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class D_student extends AppCompatActivity {

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstudent);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("QUser");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                // Clear the existing rows from the table layout
                TableLayout tableLayout = findViewById(R.id.table_layout);
                tableLayout.removeViews(1, tableLayout.getChildCount() - 1);

                // Iterate through the snapshot of the student records in the database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Convert the snapshot to a Student object
                    d_std student = snapshot.getValue(d_std.class);

                    // Create a new table row for the student record
                    TableRow row = new TableRow(D_student.this);

                    // Add the student record data to the table row as TextViews
                    TextView rollNoView = new TextView(D_student.this);
                    rollNoView.setText(student.getRegno());
                    rollNoView.setTextSize(20);
                    rollNoView.setPadding(5, 5, 5, 5);
                    rollNoView.setBackgroundColor(Color.DKGRAY);
                    row.addView(rollNoView);

                    TextView nameView = new TextView(D_student.this);
                    nameView.setText(student.getName());
                    nameView.setTextSize(20);
                    nameView.setPadding(5, 5, 5, 5);
                    nameView.setBackgroundColor(Color.DKGRAY);
                    row.addView(nameView);

                    TextView courseView = new TextView(D_student.this);
                    courseView.setText(student.getEmail());
                    courseView.setTextSize(20);
                    courseView.setPadding(5, 5, 5, 5);
                    courseView.setBackgroundColor(Color.DKGRAY);
                    row.addView(courseView);

                    TextView mobileView = new TextView(D_student.this);
                    mobileView.setText(student.getMobile());
                    mobileView.setTextSize(20);
                    mobileView.setPadding(5, 5, 5, 5);
                    mobileView.setBackgroundColor(Color.DKGRAY);
                    row.addView(mobileView);



                    // Add the table row to the table layout
                    tableLayout.addView(row);
                }
            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
                // Handle database error
            }
        });


    }
}