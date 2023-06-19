package com.example.mq_2;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    View view;
    int fpage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_1, container, false);

        l1=(LinearLayout)v.findViewById((R.id.l1));
        l2=(LinearLayout)v.findViewById((R.id.l2));
        l3=(LinearLayout)v.findViewById((R.id.l3));
        l4=(LinearLayout)v.findViewById((R.id.l4));
        l5=(LinearLayout)v.findViewById((R.id.l5));
        l6=(LinearLayout)v.findViewById((R.id.l6));
        l7=(LinearLayout)v.findViewById((R.id.l7));
        l8=(LinearLayout)v.findViewById((R.id.l8));
        l9=(LinearLayout)v.findViewById((R.id.l9));
        l10=(LinearLayout)v.findViewById((R.id.l10));
        l11=(LinearLayout)v.findViewById((R.id.l11));
        l12=(LinearLayout)v.findViewById((R.id.l12));

        Bundle bundle = getArguments();
        String userid = bundle.getString("message");


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fragment2 f2 = new Fragment2();
                //FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                //ft1.replace(R.id.frame1, f2);
                //ft1.commit();
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }

        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }

        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }
        });

        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }

        });

        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }
        });

        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }

        });

        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }
        });

        l9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }

        });

        l10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }
        });

        l11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }

        });

        l12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getActivity(), MainActivity2.class);
                i.putExtra("key",fpage);
                i.putExtra("userid",userid);
                startActivity(i);
            }
        });



        return v;
    }
}