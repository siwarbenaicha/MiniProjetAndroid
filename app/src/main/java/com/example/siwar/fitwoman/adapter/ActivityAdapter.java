package com.example.siwar.fitwoman.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siwar.fitwoman.R;
import com.example.siwar.fitwoman.model.MActivity;

import java.util.List;

public class ActivityAdapter extends ArrayAdapter<MActivity> {

    Context context;
    int resource;

    public ActivityAdapter(@NonNull Context context, int resource, @NonNull List<MActivity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, parent, false);
        TextView txt_name = (TextView) v.findViewById(R.id.MtextView17);
        TextView txt_duration = (TextView) v.findViewById(R.id.MtextView18);
        TextView txt_burned = (TextView) v.findViewById(R.id.MtextView19);
        TextView txt_description = (TextView) v.findViewById(R.id.MtextView20);

        txt_name.setText(getItem(position).getName());
        txt_duration.setText(getItem(position).getDuration());
        txt_burned.setText(String.valueOf(getItem(position).getBurnedCalories()));
        txt_description.setText(getItem(position).getDescription());

        return v;
    }
}