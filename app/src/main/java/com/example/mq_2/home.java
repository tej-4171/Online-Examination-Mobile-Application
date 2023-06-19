package com.example.mq_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.events.Event;

import java.util.Calendar;

public class home extends AppCompatActivity {

    ImageView imv1, imv2, imv3, clock;
    TextView exam;
    Integer fpage;

    String userid;

    Calendar calendar;
    int year, month, dayOfMonth;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imv1 = findViewById(R.id.imv1);
        imv2 = findViewById(R.id.imv2);
        imv3 = findViewById(R.id.imv3);
        exam = findViewById(R.id.exam);
        clock = findViewById(R.id.clock);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        imv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                fpage=1;
                Intent i =new Intent(home.this,folder.class);
                i.putExtra("key",fpage);
                i.putExtra("userid", userid);
                startActivity(i);
            }
        });

        imv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                fpage=2;
                Intent i =new Intent(home.this,folder.class);
                i.putExtra("key",fpage);
                i.putExtra("userid", userid);
                startActivity(i);
            }
        });

        imv3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){
                    fpage=3;
                    Intent i =new Intent(home.this,folder.class);
                    i.putExtra("key",fpage);
                    i.putExtra("userid", userid);
                    startActivity(i);
                }
            }
        );

        exam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                fpage=0;
                Intent i =new Intent(home.this,ExamPage.class);
                i.putExtra("key",fpage);
                i.putExtra("userid", userid);
                startActivity(i);
            }
        });

        clock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                //Toast.makeText(home.this, "you selected timmer", Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(home.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
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
                Intent i2 =new Intent(home.this,UserAccount.class);
                i2.putExtra("userid", userid);
                startActivity(i2);
                break;
            case R.id.setting:
                //Toast.makeText(this, "you selected settings", Toast.LENGTH_SHORT).show();
                Intent i3 =new Intent(home.this,settings.class);
                i3.putExtra("userid", userid);
                startActivity(i3);
                break;
            case R.id.logout:
                Intent i4 =new Intent(home.this,MainActivity.class);
                finish();
                startActivity(i4);
                break;
        }
        return true;
    }

}