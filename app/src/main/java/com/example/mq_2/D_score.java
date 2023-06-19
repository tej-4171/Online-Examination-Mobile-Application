package com.example.mq_2;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.security.cert.PolicyNode;
        import java.util.ArrayList;

public class D_score extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dscore);

        listView = findViewById(R.id.list);

        ArrayList<String> li=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(this, R.layout.list_item, li);
        listView.setAdapter(adapter);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("QUser");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                li.clear();
                for ( DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    String txt1 = snapshot.child("Regno").getValue(String.class);
                    int txt2 = snapshot.child("Marks").getValue(Integer.class);


                    String txt = txt1 + " \t\t\t\t " + Integer.toString(txt2) ;
                    li.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}