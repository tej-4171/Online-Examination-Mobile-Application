package com.example.mq_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAccount extends AppCompatActivity {

    TextView e1, e2, e3;
    String userid, name, regno, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);


        DatabaseReference fdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rexp1-bb1aa-default-rtdb.firebaseio.com/");

        fdb.child("QUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    name = snapshot.child(userid).child("Name").getValue(String.class);
                    regno = snapshot.child(userid).child("Regno").getValue(String.class);
                    email = snapshot.child(userid).child("Email").getValue(String.class);

                    e1.setText(name);
                    e2.setText(regno);
                    e3.setText(email);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater m=new MenuInflater(this);
        m.inflate(R.menu.home_menu,menu);
    }
    public void showpop(View v)
    {
        PopupMenu p=new PopupMenu(this,v);
        MenuInflater m=new MenuInflater(this);
        m.inflate(R.menu.home_menu,p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onpopupmenuclick(menuItem);
            }
        });
        p.show();
    }
    public boolean onpopupmenuclick(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.home:
                Intent i1 =new Intent(UserAccount.this,home.class);
                i1.putExtra("userid", userid);
                finish();
                startActivity(i1);
                break;
            case R.id.user:
                break;
            case R.id.setting:
                //Toast.makeText(this, "you selected settings", Toast.LENGTH_SHORT).show();
                Intent i3 =new Intent(UserAccount.this,settings.class);
                i3.putExtra("userid", userid);
                finish();
                startActivity(i3);
                break;
            case R.id.logout:
                Intent i4 =new Intent(UserAccount.this,MainActivity.class);
                finish();
                startActivity(i4);
                break;
        }
        return true;
    }
}
