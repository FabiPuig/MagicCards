package com.example.a20464654j.magiccards;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartes = (ListView) view.findViewById(R.id.lvCards);

       // String[] info = {"text1", "text2", "text3", "text4", "text5", "text6", "text7"};

        items = new ArrayList<>();
        for( int i = 0; i < 1000; i++){
            items.add("pene " + i);
        }
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_cards_row,
                R.id.tvLinea,
                items
        );

        lvCartes.setAdapter( adapter );

        return view;
    }
}
