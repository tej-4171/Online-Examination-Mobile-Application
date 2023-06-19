package com.example.mq_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class A_settings extends AppCompatActivity {

    // on below line we are creating a variable.
    private ToggleButton toggleBtn, toggleBtn2;
    private TextView pass;
    private ImageView account;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asettings);


        // on below line we are initializing our variables.
        toggleBtn = findViewById(R.id.idBtnToggle);
        toggleBtn2 = findViewById(R.id.idBtnToggle2);
        pass = findViewById(R.id.pass);
        account = findViewById(R.id.account);


        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(A_settings.this, A_EditPassword.class);
                i1.putExtra("userid",userid);
                startActivity(i1);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(A_settings.this, A_account.class);
                i2.putExtra("userid",userid);
                startActivity(i2);
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
                Intent i1 =new Intent(A_settings.this,Admin.class);
                i1.putExtra("userid", userid);
                finish();
                startActivity(i1);
                break;
            case R.id.user:
                Intent i2 =new Intent(A_settings.this,A_account.class);
                i2.putExtra("userid", userid);
                finish();
                startActivity(i2);
                break;
            case R.id.setting:
                //Toast.makeText(this, "you selected settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent i4 =new Intent(A_settings.this,A_log.class);
                finish();
                startActivity(i4);
                break;
        }
        return true;
    }
}
