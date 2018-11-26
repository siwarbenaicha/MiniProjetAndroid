package com.example.siwar.fitwoman.adapter;

import android.app.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siwar.fitwoman.R;

import com.example.siwar.fitwoman.model.Exercice;

import java.util.List;



public class ExerciceAdapter extends ArrayAdapter<Exercice> {
    private static final String LOG_TAG = ExerciceAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param exercices A List of AndroidFlavor objects to display in a list
     */
    public ExerciceAdapter(Activity context, List<Exercice> exercices) {

        super(context, 0, exercices);
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        Exercice exercice = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fitness_row, parent, false);
        }

        ImageView Eimg = (ImageView) convertView.findViewById(R.id.imageView2);
        Eimg.setImageResource(R.drawable.m3);

        TextView Ename = (TextView) convertView.findViewById(R.id.textView2);
        Ename.setText(exercice.getName());

        TextView Edescription = (TextView) convertView.findViewById(R.id.textView3);
        Edescription.setText(exercice.getDescription());
        return convertView;
    }



}
