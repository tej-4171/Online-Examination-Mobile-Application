package com.example.mq_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class folder extends AppCompatActivity {

    ImageView clock;
    int fpage;

    String userid;
    Calendar calendar;
    int year, month, dayOfMonth;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        clock = findViewById(R.id.clock);

        Intent intent = getIntent();
        int number = intent.getExtras().getInt("key");
        userid = intent.getStringExtra("userid");
        fpage=number;



        if(fpage==1){
            replaceFragment(new Fragment1());
        }
        else if(fpage==2){
            replaceFragment(new Fragment2());
        }
        else if(fpage==3){
            replaceFragment(new Fragment3());
        }
        else{
            Intent i =new Intent(folder.this,home.class);
            startActivity(i);
        }


        clock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                //Toast.makeText(folder.this, "you selected timmer", Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(folder.this,
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
        m.inflate(R.menu.menu,menu);
    }
    public void showpop(View v)
    {
        PopupMenu p=new PopupMenu(this,v);
        MenuInflater m=new MenuInflater(this);
        m.inflate(R.menu.menu,p.getMenu());
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
                Intent i1 =new Intent(folder.this,home.class);
                finish();
                i1.putExtra("userid", userid);
                startActivity(i1);
                break;
            case R.id.bt1:
                fpage=1;
                replaceFragment(new Fragment1());
                break;
            case R.id.bt2:
                fpage=2;
                replaceFragment(new Fragment2());
                break;
            case R.id.bt3:
                fpage=3;
                replaceFragment(new Fragment3());
                break;
            case R.id.logout:
                finish();
                finish();
                Intent i2 =new Intent(folder.this,MainActivity.class);
                startActivity(i2);
                break;
        }
        return true;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle b = new Bundle();
        b.putString("message", userid);
        fragment.setArguments(b);
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}