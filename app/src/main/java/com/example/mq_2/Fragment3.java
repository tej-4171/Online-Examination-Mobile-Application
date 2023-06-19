package com.example.mq_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    LinearLayout l1, l2;
    View view;
    int fpage = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v = inflater.inflate(R.layout.fragment_3, container, false);

        l1=(LinearLayout)v.findViewById((R.id.l1));
        l2=(LinearLayout)v.findViewById((R.id.l2));

        Bundle bundle = getArguments();
        String userid = bundle.getString("message");


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


        return v;
    }
}