package com.example.siwar.fitwoman.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.siwar.fitwoman.R;
import com.example.siwar.fitwoman.adapter.ExerciceAdapter;
import com.example.siwar.fitwoman.model.Exercice;

import java.util.Arrays;


public class FitnessFragment extends ListFragment implements AdapterView.OnItemClickListener {
    public ExerciceAdapter exerciceAdapter;

    Exercice[] exercices = {
            new Exercice("Cupcake", "1.5", "m2.png"),
            new Exercice("Donut", "1.6", "m2.png"),
            new Exercice("Eclair", "2.0-2.1","m2.png"),

    };

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_fitness, container ,false);
    }

   @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       exerciceAdapter = new ExerciceAdapter(getActivityNonNull() , Arrays.asList(exercices));
       setListAdapter(exerciceAdapter);
        //getListView().setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    protected FragmentActivity getActivityNonNull() {
        if (super.getActivity() != null) {
            return super.getActivity();
        } else {
            throw new RuntimeException("null returned from getActivity()");
        }
    }
}
