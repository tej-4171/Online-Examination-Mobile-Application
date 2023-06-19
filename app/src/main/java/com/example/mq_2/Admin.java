package com.example.mq_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity {

    LinearLayout l1, l2;
    ImageView register;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        register = findViewById(R.id.clock);


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Admin.this, D_student.class);
                startActivity(i1);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Admin.this, D_score.class);
                startActivity(i2);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Admin.this, A_reg.class);
                i3.putExtra("userid", userid);
                startActivity(i3);
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
                break;
            case R.id.user:
                Intent it2 =new Intent(Admin.this,A_account.class);
                it2.putExtra("userid", userid);
                startActivity(it2);
                break;
            case R.id.setting:
                //Toast.makeText(this, "you selected settings", Toast.LENGTH_SHORT).show();
                Intent it3 =new Intent(Admin.this,A_settings.class);
                it3.putExtra("userid", userid);
                startActivity(it3);
                break;
            case R.id.logout:
                Intent it4 =new Intent(Admin.this,A_log.class);
                finish();
                startActivity(it4);
                break;
        }
        return true;
    }
}