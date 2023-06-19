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

public class settings extends AppCompatActivity {

    // on below line we are creating a variable.
    private ToggleButton toggleBtn, toggleBtn2;
    private TextView statusTV, pass;
    private ImageView edit;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // on below line we are initializing our variables.
        toggleBtn = findViewById(R.id.idBtnToggle);
        toggleBtn2 = findViewById(R.id.idBtnToggle2);
        edit = findViewById(R.id.edit);
        pass = findViewById(R.id.pass);


        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(settings.this, EditAccount.class);
                i.putExtra("userid", userid);
                startActivity(i);
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(settings.this, EditPassword.class);
                i.putExtra("userid", userid);
                startActivity(i);
            }
        });

        // on below line we are setting text view by
        // checking toggle button status.
       /* if (toggleBtn.isChecked()) {
            // if button is checked we are setting
            // text as Toggle Button is On
            statusTV.setText("Toggle Button is On");
        } else {
            // if button is unchecked we are setting
            // text as Toggle Button is Off
            statusTV.setText("Toggle Button is Off");
        }

        if (toggleBtn2.isChecked()) {
            // if button is checked we are setting
            // text as Toggle Button is On
            statusTV.setText("Toggle Button is On");
        } else {
            // if button is unchecked we are setting
            // text as Toggle Button is Off
            statusTV.setText("Toggle Button is Off");
        }

        // on below line we are adding click listener for our toggle button
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are checking if
                // toggle button is checked or not.
                if (toggleBtn.isChecked()) {
                    // on below line we are setting message
                    // for status text view if toggle button is checked.
                    statusTV.setText("Toggle Button is On");
                } else {
                    // on below line we are setting message for
                    // status text view if toggle button is un checked.
                    statusTV.setText("Toggle Button is Off");
                }
            }
        });

        toggleBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are checking if
                // toggle button is checked or not.
                if (toggleBtn.isChecked()) {
                    // on below line we are setting message
                    // for status text view if toggle button is checked.
                    statusTV.setText("Toggle Button is On");
                } else {
                    // on below line we are setting message for
                    // status text view if toggle button is un checked.
                    statusTV.setText("Toggle Button is Off");
                }
            }
        });
*/
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
                Intent i1 =new Intent(settings.this,home.class);
                i1.putExtra("userid", userid);
                finish();
                startActivity(i1);
                break;
            case R.id.user:
                Intent i2 =new Intent(settings.this,UserAccount.class);
                i2.putExtra("userid", userid);
                finish();
                startActivity(i2);
                break;
            case R.id.setting:
                //Toast.makeText(this, "you selected settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent i4 =new Intent(settings.this,MainActivity.class);
                finish();
                startActivity(i4);
                break;
        }
        return true;
    }
}
